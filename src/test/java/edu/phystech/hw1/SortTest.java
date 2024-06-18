package edu.phystech.hw1;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SortTest {
    private static int[] sort(int[] nums, int l, int r) {
        if (r - l == 1) {
            return new int[] { nums[l] };
        }
        var s1 = sort(nums, l, (l + r) / 2);
        var s2 = sort(nums,(l + r) / 2, r);

        int[] result = new int[r - l];
        int s1Index = 0;
        int s2Index = 0;

        while (s1Index + s2Index < r - l) {
            if (s2Index == s2.length) {
                result[s1Index + s2Index] = s1[s1Index++];
            } else if (s1Index == s1.length) {
                result[s1Index + s2Index] = s2[s2Index++];
            } else if (s1[s1Index] < s2[s2Index]) {
                result[s1Index + s2Index] = s1[s1Index++];
            } else {
                result[s1Index + s2Index] = s2[s2Index++];
            }
        }

        return result;
    }

    private static int[] sort(int[] nums) {
        return sort(nums, 0, nums.length);
    }

    @Test
    public void sortWorks() {
        Assertions.assertArrayEquals(new int[]{1}, sort(new int[]{1}));
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void sortReturnsNewArray() {
        int[] input = {9, 1, 3, 11, 45, 499};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] sorted = sort(input);

        Assertions.assertArrayEquals(new int[]{1, 3, 9, 11, 45, 499}, sorted);
        Assertions.assertArrayEquals(copy, input);
    }
}
