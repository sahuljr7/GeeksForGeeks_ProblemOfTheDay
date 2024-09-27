# K Sized Subarray Maximum

To solve the problem of finding the maximum element in each subarray of size `k` for a given array `arr[]`, we can use an **efficient sliding window approach** with a **deque**. The goal is to find the maximum value for every subarray of size `k` in linear time.

### Problem Breakdown:
1. **Sliding Window Maximum**: 
   - We are required to slide a window of size `k` over the array, and for each position of the window, find the maximum element.
   - A **naive approach** would involve calculating the maximum for each window independently, which would take \(O(n \times k)\) time. However, this can be optimized using a deque.

2. **Optimized Approach**:
   - We can maintain a **deque** (double-ended queue) to store indices of useful elements for each window. The useful elements are those that can potentially be the maximum in the current or future windows.
   - For each element in the array, we ensure that the deque contains only indices of elements within the current window of size `k` and that these elements are in descending order.
   
3. **Deque Operations**:
   - **Remove elements out of the current window**: If an element at the front of the deque is outside the current window, remove it.
   - **Maintain descending order**: While adding a new element, remove elements from the back of the deque that are smaller than the current element (since they will never be the maximum for the current or future windows).

4. **Time Complexity**:
   - The time complexity of this approach is \(O(n)\), where \(n\) is the number of elements in the array. Each element is added to and removed from the deque at most once, resulting in linear time.

### Java Code Implementation:

```java
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    // Function to find the maximum of each subarray of size k
    public ArrayList<Integer> max_of_subarrays(int k, int arr[]) {
        ArrayList<Integer> result = new ArrayList<>();  // To store the maximums of each window
        Deque<Integer> deque = new LinkedList<>();      // To store indices of array elements
        
        // Iterate over the array
        for (int i = 0; i < arr.length; i++) {
            // Remove elements from the front of the deque if they are out of the current window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove elements from the back of the deque if they are smaller than the current element
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }

            // Add the current element's index to the deque
            deque.offerLast(i);

            // If we have processed at least 'k' elements, the front of the deque contains the maximum
            // element for the current window
            if (i >= k - 1) {
                result.add(arr[deque.peekFirst()]);
            }
        }
        
        return result;
    }
}
```

### Explanation:
1. **Deque Initialization**: We use a deque to store the indices of elements in the array. The deque is used to maintain the indices of the maximum elements for each sliding window of size `k`.

2. **Sliding the Window**:
   - As we iterate through the array, for each element `arr[i]`, we first remove indices from the front of the deque if they are no longer within the window of size `k` (i.e., their indices are less than `i - k + 1`).
   - Then, we remove elements from the back of the deque if they are smaller than `arr[i]`, as they can’t be the maximum for the current or any future window.
   - After processing the current element, we add its index to the deque.
   
3. **Storing the Result**:
   - Once we have processed at least `k` elements (i.e., when `i >= k - 1`), the element at the front of the deque is the maximum for the current window. We add this value to the result list.

4. **Return the Result**:
   - After processing all elements, the result list will contain the maximum values for all windows of size `k`.

### Example Usage:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] arr1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println(solution.max_of_subarrays(k1, arr1)); // Expected: [3, 3, 5, 5, 6, 7]

        int[] arr2 = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int k2 = 4;
        System.out.println(solution.max_of_subarrays(k2, arr2)); // Expected: [10, 10, 10, 15, 15, 90, 90]
    }
}
```

### Example Walkthrough:
For the array `arr1 = [1, 3, -1, -3, 5, 3, 6, 7]` and `k = 3`:
- The maximum of the first window `[1, 3, -1]` is `3`.
- The maximum of the second window `[3, -1, -3]` is `3`.
- The maximum of the third window `[-3, 5, 3]` is `5`.
- The maximum of the fourth window `[5, 3, 6]` is `6`.
- The maximum of the last window `[3, 6, 7]` is `7`.
Thus, the output is `[3, 3, 5, 5, 6, 7]`.

### Time Complexity:
- **Time Complexity:** \(O(n)\), where \(n\) is the number of elements in the array. Each element is added and removed from the deque at most once, resulting in linear time complexity.
- **Space Complexity:** \(O(k)\), where \(k\) is the size of the window, as we store at most `k` indices in the deque.

### Edge Cases:
1. **When `k = 1`:** Each window will contain exactly one element, so the result will be the array itself.
2. **When `k` is equal to the size of the array:** The result will contain only one element, which is the maximum of the entire array.
3. **When the array is empty:** The result will be an empty list.

### Best Practices:
- **Efficiency:** The sliding window approach with a deque ensures that the solution is optimal and runs in linear time.
- **Clarity:** The code is modular, with clear logic to handle the sliding window and maintain the deque.
- **Edge Case Handling:** Properly handles cases like single-element windows, large windows, and empty arrays.

### Conclusion:
The solution efficiently finds the maximum element in each subarray of size `k` in linear time using a deque to maintain the indices of potential maximum elements. The algorithm ensures optimal performance and handles various edge cases gracefully.
