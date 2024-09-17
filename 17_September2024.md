# Minimize the Heights II

To minimize the difference between the heights in an array after increasing or decreasing each element by a maximum of \( k \), we need to identify the best way to adjust the heights while ensuring the smallest difference between the maximum and minimum elements of the array.

## Approach
1. **Sort the Array:** By sorting, we can more easily manage the heights and explore different ways to minimize the maximum difference.
2. **Initial Difference:** Calculate the initial difference between the highest and lowest values in the sorted array (i.e., `arr[n-1] - arr[0]`).
3. **Adjust Heights:** After sorting, the smallest possible difference can be achieved by either increasing the smallest element or decreasing the largest element.
4. **Iterate and Calculate Minimum Difference:** Iterate through the array, treating each element as a potential boundary for the new minimum and maximum values:
   - Calculate the new minimum as the smaller value between `arr[0] + k` and `arr[i+1] - k`.
   - Calculate the new maximum as the larger value between `arr[i] + k` and `arr[n-1] - k`.
   - Update the minimum difference accordingly.
5. **Edge Case:** If the array contains only one element, the difference is always `0`.

## Complexity
- **Time Complexity:** \( O(n \log n) \) due to sorting.
- **Space Complexity:** \( O(1) \) as no additional data structures are used apart from variables for tracking min and max values.

## Java Code Implementation
Here is the implementation of the solution:

```java
// File: Solution.java
import java.util.Arrays;

class Solution {
    int getMinDiff(int[] arr, int k) {
        // Handle edge case when there is only one element
        if (arr.length == 1) {
            return 0;
        }

        // Sort the array to arrange heights in increasing order
        Arrays.sort(arr);
        
        // Calculate the initial difference between the max and min
        int n = arr.length;
        int initialDiff = arr[n - 1] - arr[0];

        // Variables to track the new minimum difference
        int minHeight, maxHeight;
        int minDiff = initialDiff;

        // Iterate through the array
        for (int i = 0; i < n - 1; i++) {
            // Highest element after decreasing by k or the previous maximum
            maxHeight = Math.max(arr[i] + k, arr[n - 1] - k);
            
            // Smallest element after increasing by k or the next minimum
            minHeight = Math.min(arr[0] + k, arr[i + 1] - k);

            // Ignore if the height becomes negative
            if (minHeight < 0) {
                continue;
            }

            // Update the minimum difference
            minDiff = Math.min(minDiff, maxHeight - minHeight);
        }

        return minDiff;
    }
}
```

### Explanation
1. **Edge Case:** If the array has only one element, the difference is `0` because there is nothing to adjust.
2. **Sorting:** The array is sorted to help manage the heights in an orderly manner.
3. **Initial Difference:** Calculates the difference between the maximum and minimum elements in the sorted array.
4. **Iterate and Adjust:** The loop adjusts the heights and calculates the potential new minimum and maximum for each element in the sorted list, updating the minimum difference.
5. **Handle Negatives:** Ensures that height adjustments don't result in negative values.
6. **Update Minimum Difference:** Continuously tracks the smallest difference found during iterations.

### Testing the Code
Here’s how you can test the function with various inputs:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        int[] arr1 = {1, 5, 8, 10};
        int k1 = 2;
        System.out.println(solution.getMinDiff(arr1, k1)); // Expected: 5

        int[] arr2 = {3, 9, 12, 16, 20};
        int k2 = 3;
        System.out.println(solution.getMinDiff(arr2, k2)); // Expected: 11

        int[] arr3 = {2, 6, 3, 4, 7, 2, 10, 3, 2, 1};
        int k3 = 5;
        System.out.println(solution.getMinDiff(arr3, k3)); // Expected: 7

        int[] arr4 = {1, 10, 14, 14, 14, 15};
        int k4 = 6;
        System.out.println(solution.getMinDiff(arr4, k4)); // Expected: 5

        int[] arr5 = {1};
        int k5 = 10;
        System.out.println(solution.getMinDiff(arr5, k5)); // Expected: 0 (only one element)
    }
}
```

### Best Practices Followed
- **Edge Case Handling:** The code correctly handles the case where the array contains only one element.
- **Optimal Approach:** The sorting step simplifies the problem, and the greedy approach ensures an efficient solution.
- **Modular and Readable:** The code is organized, with variable names like `minHeight`, `maxHeight`, and `minDiff` providing clear context.
