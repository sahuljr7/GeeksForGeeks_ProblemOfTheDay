# Not a subset sum

The problem you're referring to is about finding the smallest positive number that cannot be represented as the sum of any subset of a given array. This problem can be solved using a greedy approach, and the optimal time complexity is \( O(n \log n) \), where `n` is the number of elements in the array.

### Problem Breakdown:

Given an array of positive integers `arr[]`, the task is to find the smallest positive number that cannot be represented as the sum of any subset of the array.

### Approach:

1. **Sort the Array**:
   - Start by sorting the array to process elements in increasing order. Sorting helps in ensuring that we are checking the smallest possible subset sum at each step.

2. **Greedy Algorithm**:
   - Start with a variable `res = 1`, which represents the smallest sum we cannot create at any given point.
   - Traverse the sorted array, and for each element `arr[i]`, check if it's greater than `res`. 
   - If `arr[i] > res`, it means that `res` is the smallest number that cannot be formed as a sum of any subset of the array up to that point.
   - Otherwise, add `arr[i]` to `res`, because we can now form all sums from 1 to `res + arr[i]` inclusive.
   
3. **Return the Result**:
   - Once the loop is completed, `res` will hold the smallest positive number that cannot be represented as a sum of any subset of the array.

### Algorithm:

1. Sort the array.
2. Initialize `res = 1`.
3. For each element in the array:
   - If `arr[i] > res`, return `res`.
   - Otherwise, update `res = res + arr[i]`.
4. Return `res`.

### Time Complexity:
- Sorting the array takes **O(n log n)**.
- Traversing the array to compute the result takes **O(n)**.
- Thus, the total time complexity is **O(n log n)**.

### Java Code Implementation:

```java
import java.util.Arrays;

class Solution {
    /**
     * Function to find the smallest positive number that cannot be represented
     * as the sum of any subset of a given array.
     *
     * @param arr The input array of positive integers.
     * @return The smallest positive number that cannot be represented as the sum
     *         of any subset of the array.
     */
    public long findSmallest(int[] arr) {
        // Step 1: Sort the array
        Arrays.sort(arr);

        // Step 2: Initialize the result variable to 1
        long res = 1;

        // Step 3: Traverse the sorted array and update res
        for (int num : arr) {
            // If the current number is greater than the smallest sum we can't form,
            // then return the current res value
            if (num > res) {
                break;
            }
            // Otherwise, we can form sums up to res + num, so update res
            res += num;
        }

        // Step 4: Return the smallest number that can't be represented
        return res;
    }
}
```

### Explanation of the Code:

1. **Sorting**:
   - The array is first sorted using `Arrays.sort(arr)`. This step ensures that we process the elements in increasing order, which is crucial for the greedy approach to work.

2. **Initialize `res`**:
   - `res` is initialized to `1`, meaning that we are first looking for the smallest sum that cannot be formed (starting from `1`).

3. **Traversing the Array**:
   - As we traverse the sorted array, we check if the current element `arr[i]` is greater than `res`.
   - If it is, we return `res` because `res` is the smallest number that cannot be represented as a subset sum of the elements seen so far.
   - Otherwise, we add `arr[i]` to `res` because now we can represent all sums from `1` to `res + arr[i]`.

4. **Final Result**:
   - Once the loop finishes, `res` contains the smallest number that cannot be represented as the sum of any subset of the array.

### Example Walkthrough:

#### Example 1:
**Input**: `arr = [1, 2, 3, 10]`

- **Step 1**: Sort the array → `[1, 2, 3, 10]`.
- **Step 2**: Initialize `res = 1`.
- **Step 3**: Traverse the array:
  - `arr[0] = 1`: Since `1 <= res`, update `res = res + 1 = 2`.
  - `arr[1] = 2`: Since `2 <= res`, update `res = res + 2 = 4`.
  - `arr[2] = 3`: Since `3 <= res`, update `res = res + 3 = 7`.
  - `arr[3] = 10`: Since `10 > res`, return `res = 7`.
  
**Output**: `7`

#### Example 2:
**Input**: `arr = [1, 1, 1, 1]`

- **Step 1**: Sort the array → `[1, 1, 1, 1]`.
- **Step 2**: Initialize `res = 1`.
- **Step 3**: Traverse the array:
  - `arr[0] = 1`: Since `1 <= res`, update `res = res + 1 = 2`.
  - `arr[1] = 1`: Since `1 <= res`, update `res = res + 1 = 3`.
  - `arr[2] = 1`: Since `1 <= res`, update `res = res + 1 = 4`.
  - `arr[3] = 1`: Since `1 <= res`, update `res = res + 1 = 5`.
  
**Output**: `5`

### Time Complexity:
- **Sorting** takes **O(n log n)**, where `n` is the length of the array.
- **Traversing** the sorted array takes **O(n)**.
- Therefore, the overall time complexity is **O(n log n)**.

### Space Complexity:
- The space complexity is **O(1)**, as we are using a constant amount of extra space apart from the input array.

### Conclusion:
This solution efficiently computes the smallest number that cannot be represented as a sum of any subset of a given array using a greedy algorithm. The combination of sorting and linear traversal ensures that the solution runs in \( O(n \log n) \) time, which is optimal for this problem.
