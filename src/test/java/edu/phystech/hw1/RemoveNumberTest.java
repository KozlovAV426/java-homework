package edu.phystech.hw1;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class RemoveNumberTest {

    private static int[] removeElement(int[] input, int element) {
        return input;
    }

    @Test
    public void removeElement() {
        Assertions.assertTrue(Arrays.equals(new int[] {1}, removeElement(new int[] {1, 2}, 2)));
        Assertions.assertTrue(Arrays.equals(new int[] {1}, removeElement(new int[] {1}, 1)));
    }

    @Test
    public void reverseReturnsNewArray() {
        int[] input = {9, 1, 3, 11, 3, 45, 499};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] removed = removeElement(input, 3);

        Assertions.assertTrue(Arrays.equals(new int[] {9, 1, 11, 45, 499}, removed));
        Assertions.assertTrue(Arrays.equals(copy, input));
    }
}
