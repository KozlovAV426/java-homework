package edu.phystech.hw4;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import edu.phystech.hw4.lock.CASTicketLock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kzlv4natoly
 */
class Counter {
    private int value = 0;

    public void increment() {
        value += 1;
    }
    public int getValue() {
        return value;
    }
}
public class CASTicketLockTest {

    @Test
    void justWorks() {
        Counter counter = new Counter();
        CASTicketLock lock = new CASTicketLock();
        IntStream.range(0, 100_000).forEach(i -> {
            lock.lock();
            counter.increment();
            lock.unlock();
        });
        Assertions.assertEquals(100_000, counter.getValue());
    }


    @Test
    void worksWithConcurrentIncrement() throws InterruptedException {
        Counter counter = new Counter();
        CASTicketLock lock = new CASTicketLock();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callableList = IntStream.range(0, 100_000).mapToObj(i -> (Callable<Integer>) () -> {
            lock.lock();
            counter.increment();
            lock.unlock();
            return 0;
        }).toList();
        executorService.invokeAll(callableList).forEach(f -> {
            try {
                f.get();
            } catch (Exception e) {}
        });
        Assertions.assertEquals(100_000, counter.getValue());
    }
}
