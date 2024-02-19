package edu.phystech.hw1;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FactorialTest {


    private static long factorial(int n) {
        return 0;
    }

    @Test
    public void smallNumbersTest() {
        var factorials = List.of(1L, 2L, 6L, 24L, 120L, 720L, 5040L);

        for (int i = 0; i < factorials.size(); ++i) {
            Assertions.assertEquals(factorials.get(i), factorial(i + 1));
        }
    }


    @Test
    public void cornerCaseTest() {
        Assertions.assertEquals(1, factorial(0));
        Assertions.assertEquals(1, factorial(1));
    }
}
