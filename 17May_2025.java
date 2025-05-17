// https://www.geeksforgeeks.org/problems/sort-the-given-array-after-applying-the-given-equation0304/1

import java.util.*;

class Solution {
    public ArrayList<Integer> sortArray(int[] arr, int A, int B, int C) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));

        int left = 0, right = n - 1;
        int index = (A >= 0) ? n - 1 : 0;

        while (left <= right) {
            int valLeft = apply(arr[left], A, B, C);
            int valRight = apply(arr[right], A, B, C);

            if (A >= 0) {
                if (valLeft > valRight) {
                    result.set(index--, valLeft);
                    left++;
                } else {
                    result.set(index--, valRight);
                    right--;
                }
            } else {
                if (valLeft < valRight) {
                    result.set(index++, valLeft);
                    left++;
                } else {
                    result.set(index++, valRight);
                    right--;
                }
            }
        }

        return result;
    }

    private int apply(int x, int A, int B, int C) {
        return A * x * x + B * x + C;
    }
}
