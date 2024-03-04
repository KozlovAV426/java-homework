package edu.phystech.hw1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FindNumberTest {
    private static int findNumber(int[] input, int element) {
        return -1;
    }


    @Test
    public void justWorks() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        for (int i = 0; i < 5; ++i) {
            Assertions.assertEquals(i, findNumber(array, i + 1));
        }
        Assertions.assertEquals(-1, findNumber(array, 10));
    }


    @Test
    public void worksCornerCases() {
        Assertions.assertEquals(-1, findNumber(new int[] {}, 10));
        Assertions.assertEquals(0, findNumber(new int[] {1}, 1));
    }

    @Test
    public void performanceCheck() {
        int[] bigInput = IntStream.iterate(1, i -> i <= 1_000_000, i -> i + 1).toArray();
        List<Integer> asList = IntStream.iterate(1, i -> i <= 1_000_000, i -> i + 1)
                .boxed().collect(Collectors.toList());

        int elementToFind = 989_912;

        double naiveTime = 0;
        double findNumberTime = 0;

        for (int i = 0; i < 1_000; ++i) {
            long startTime = System.nanoTime();
            int result = findNumber(bigInput, elementToFind);
            long endTime =  System.nanoTime();
            Assertions.assertEquals(elementToFind, bigInput[result]);
            findNumberTime += (endTime - startTime);
        }

        for (int i = 0; i < 1_000; ++i) {
            long startTime = System.nanoTime();
            int result = asList.indexOf(elementToFind);
            long endTime =  System.nanoTime();
            Assertions.assertEquals(elementToFind, asList.get(result));
            naiveTime += (endTime - startTime);
        }

        Assertions.assertTrue(findNumberTime < naiveTime,
                """
                        Если тест валится, попробуйте реализовать более оптимальный алгоритм,
                        не зря же числа в массиве упорядочены (спойлер: реализуйте 0️⃣1️⃣ 🔎)
                        """);

    }
}
