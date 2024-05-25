package edu.phystech.hw4;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.phystech.hw4.stepper.Stepper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kzlv4natoly
 */
public class StepperTest {

    @Test
    void justWorks() {
        Stepper stepper = new Stepper();
        IntStream.range(0, 100_000).forEach(i -> {
            try {
                if (i % 2 == 0) {
                    stepper.leftStep();
                } else {
                    stepper.rightStep();
                }
            } catch (Exception e) {}
        });
        Assertions.assertEquals(100_000, stepper.getHistory().size());
        for (int i = 0; i < 100_000; ++i) {
            Stepper.Side expected =  (i % 2 == 0) ? Stepper.Side.LEFT : Stepper.Side.RIGHT;
            Assertions.assertEquals(expected, stepper.getHistory().get(i));
        }
    }

    @Test
    void onlyRightStepsShouldNotWork() throws InterruptedException {
        Stepper stepper = new Stepper();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = IntStream.range(0, 600).mapToObj(i ->
            executorService.submit(() -> {
                try {stepper.rightStep();
                } catch (Exception e) {}
            })).collect(Collectors.toList());
        boolean isFinished = executorService.awaitTermination(5, TimeUnit.SECONDS);
        Assertions.assertFalse(isFinished);
        futures.forEach(f -> Assertions.assertFalse(f.isDone()));
        Assertions.assertTrue(stepper.getHistory().isEmpty());
    }

    @Test
    void onlyLeftStepsShouldNotWork() throws InterruptedException {
        Stepper stepper = new Stepper();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = IntStream.range(0, 600).mapToObj(i ->
                executorService.submit(() -> {
                    try {stepper.leftStep();
                    } catch (Exception e) {}
                })).collect(Collectors.toList());
        boolean isFinished = executorService.awaitTermination(5, TimeUnit.SECONDS);
        Assertions.assertFalse(isFinished);
        for (int i = 0; i < 600; ++i) {
            if (i == 0) {
                Assertions.assertTrue(futures.get(i).isDone());
            } else {
                Assertions.assertFalse(futures.get(i).isDone());
            }
        }
        Assertions.assertEquals(1, stepper.getHistory().size());
        Assertions.assertEquals(Stepper.Side.LEFT, stepper.getHistory().get(0));
    }


    @Test
    void worksWithConcurrent() throws InterruptedException, ExecutionException {
        Stepper stepper = new Stepper();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<?>> futures = IntStream.range(0, 600).mapToObj(i ->
                executorService.submit(() -> {
                    try {
                        if (i % 2 == 0) {
                            stepper.leftStep();
                        } else stepper.rightStep();
                    } catch (Exception e) {}
                })).collect(Collectors.toList());

        // wait completion of all futures
        for (var future : futures) {
            future.get();
        }

        Assertions.assertTrue(futures.stream().allMatch(Future::isDone));
        Assertions.assertEquals(600, stepper.getHistory().size());
        for (int i = 0; i < 600; ++i) {
            Stepper.Side expected =  (i % 2 == 0) ? Stepper.Side.LEFT : Stepper.Side.RIGHT;
            Assertions.assertEquals(expected, stepper.getHistory().get(i));
        }
    }
}
