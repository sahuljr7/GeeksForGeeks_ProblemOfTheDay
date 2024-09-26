# Roof Top

To solve the problem of finding the maximum number of consecutive steps where each step results in an increase in altitude, we need to traverse through the array and count how many consecutive steps have increasing values. 

### Problem Breakdown:
- **Consecutive Steps:** We are given an array of integers, where each element represents the altitude at a certain step. We need to find the longest sequence of consecutive steps where each step has a greater altitude than the previous one.
- If `arr[i] < arr[i + 1]`, it means we have an increase in altitude, and we can count that as part of the consecutive steps.
- If `arr[i] >= arr[i + 1]`, the sequence breaks, and we reset our count.

### Plan:
1. **Iterate through the array** and compare each element with the next one.
2. **Keep track of the current sequence** of increasing steps.
3. **Update the maximum sequence** whenever a new longer sequence is found.
4. **Reset the current sequence** count when the altitude decreases or stays the same.

### Time Complexity:
- **Time Complexity:** \(O(n)\), where \(n\) is the length of the array, since we are making a single pass through the array.
- **Space Complexity:** \(O(1)\), as we are using a few variables for counting and storing the result.

### Java Code Implementation:

```java
class Solution {
    // Function to find maximum number of consecutive steps
    // to gain an increase in altitude with each step.
    public int maxStep(int arr[]) {
        int maxSteps = 0;      // To store the maximum number of consecutive increasing steps
        int currentSteps = 0;  // To track the current number of increasing steps

        // Traverse through the array
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                // If the altitude increases, increment the current step count
                currentSteps++;
                // Update the maximum if current sequence is the longest so far
                maxSteps = Math.max(maxSteps, currentSteps);
            } else {
                // If altitude decreases or remains the same, reset the current step count
                currentSteps = 0;
            }
        }

        return maxSteps;
    }
}
```

### Explanation:
1. **Initialize Variables:**
   - `maxSteps`: Tracks the maximum number of consecutive increasing steps encountered so far.
   - `currentSteps`: Tracks the current streak of consecutive increasing steps.

2. **Iterate Through the Array:**
   - We loop from the start of the array to the second-last element (`i < arr.length - 1`) since we compare each element with its next one (`arr[i] < arr[i + 1]`).
   
3. **Check if the Current Step Increases:**
   - If the altitude at index `i` is less than at `i + 1`, we increment the `currentSteps` counter.
   - We also update `maxSteps` to ensure we are tracking the maximum sequence of increasing steps.

4. **Reset the Count:**
   - If the altitude does not increase (i.e., `arr[i] >= arr[i + 1]`), we reset `currentSteps` to zero and start counting again from the next comparison.

5. **Return the Maximum:**
   - After looping through the array, the result is stored in `maxSteps`, which holds the longest sequence of consecutive increasing steps.

### Example:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] arr1 = {1, 2, 2, 4, 3, 5}; // Expected: 1 (2 -> 4)
        System.out.println(solution.maxStep(arr1));  // Output: 1

        int[] arr2 = {1, 2, 3, 4};  // Expected: 3 (1 -> 2 -> 3 -> 4)
        System.out.println(solution.maxStep(arr2));  // Output: 3

        int[] arr3 = {10, 20, 30, 25, 40, 50};  // Expected: 2 (25 -> 40 -> 50)
        System.out.println(solution.maxStep(arr3));  // Output: 2
    }
}
```

### Edge Cases:
1. **Array with No Steps:** If the array length is less than 2, there are no steps to compare, so the result is `0`.
2. **No Increasing Steps:** If all elements in the array are the same or strictly decreasing, the result will be `0`.
3. **Array with All Increasing Steps:** The result will be the total number of steps minus one (`arr.length - 1`).

### Best Practices:
- **Efficiency:** The algorithm runs in \(O(n)\) time, making it optimal for this kind of problem.
- **Clear Logic:** The use of two counters (`maxSteps` and `currentSteps`) keeps the logic simple and easy to follow.
- **Edge Case Handling:** Properly handles edge cases like empty arrays or arrays without any increasing steps.

## Conclusion:
The solution efficiently finds the maximum number of consecutive steps with increasing altitude in a single pass through the array. It is well-structured, easy to understand, and optimized for performance.
