# Alternate positive and negative numbers

### Pseudocode
1. **Separate Positives and Negatives**: Iterate over the array to divide elements into two lists: one for positive numbers and one for negative numbers.
2. **Merge in Alternate Order**: Use the two lists to alternately pick elements. If one list runs out of elements, append the remaining elements of the other list to the end.
3. **Edge Cases**: If the array has all positive or all negative numbers, simply return the array as-is.

### Code Implementation

Here is the Java code that follows the above logic:

```java
// File Path: /src/Solution.java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    void rearrange(ArrayList<Integer> arr) {
        // Queues to maintain relative order of positive and negative numbers
        Queue<Integer> positiveQueue = new LinkedList<>();
        Queue<Integer> negativeQueue = new LinkedList<>();
        
        // Separate positive and negative numbers into their respective queues
        for (int num : arr) {
            if (num >= 0) {
                positiveQueue.add(num);
            } else {
                negativeQueue.add(num);
            }
        }
        
        // Clear the original array to refill it in alternate order
        arr.clear();
        
        // Alternate addition of positive and negative numbers to the array
        while (!positiveQueue.isEmpty() || !negativeQueue.isEmpty()) {
            if (!positiveQueue.isEmpty()) {
                arr.add(positiveQueue.poll());
            }
            if (!negativeQueue.isEmpty()) {
                arr.add(negativeQueue.poll());
            }
        }
    }
}
```

### Explanation
1. **Separation**: Uses two queues (`positiveQueue` and `negativeQueue`) to maintain the order of positive and negative numbers.
2. **Clearing**: Clears the original array before refilling it to ensure the result is placed back into the same list.
3. **Merging**: Alternates between adding positive and negative numbers to the original array using the `poll` method of queues. This maintains the relative order of the elements.
4. **Edge Case Handling**: The loops handle cases where one of the queues runs out of elements, ensuring that the remaining elements are simply appended to the array.

### Complexity Analysis
- **Time Complexity**: O(n), where `n` is the number of elements in the array. We traverse the array once to separate positive and negative numbers and then traverse the elements again while alternating.
- **Space Complexity**: O(n) due to the use of additional queues to store the positive and negative numbers.

### Example Test Case
```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Integer> arr = new ArrayList<>(List.of(-5, 3, -2, 4, -1, 6));
        
        solution.rearrange(arr);
        
        System.out.println(arr); // Expected output: [3, -5, 4, -2, 6, -1]
    }
}
```
