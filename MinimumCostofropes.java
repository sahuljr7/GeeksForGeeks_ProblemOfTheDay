# Minimum Cost of ropes

To solve the problem of finding the minimum cost of connecting ropes, we need to use a greedy algorithm. The optimal strategy is to always combine the two smallest ropes first, as this minimizes the cost of subsequent combinations.

## Approach and Explanation
1. **Use a Min-Heap (Priority Queue):** A min-heap allows us to efficiently extract the two smallest elements in logarithmic time.
2. **Algorithm:**
   - Insert all the rope lengths into a min-heap.
   - While there is more than one rope in the heap:
     - Extract the two smallest ropes.
     - Combine these two ropes, and calculate the cost.
     - Insert the resulting new rope back into the heap.
     - Add the cost to a total cost accumulator.
   - Return the total accumulated cost.
3. **Complexity:** Inserting all elements into the heap takes \(O(n \log n)\). Combining elements involves repeated heap operations, resulting in an overall time complexity of \(O(n \log n)\).

## Java Code Implementation
Here's the implementation of the solution using a priority queue (min-heap):

```java
// File: Solution.java
import java.util.PriorityQueue;

class Solution {
    // Function to return the minimum cost of connecting the ropes.
    public long minCost(long[] arr) {
        // Edge case: If there is only one rope, no cost is needed.
        if (arr.length == 1) {
            return 0;
        }

        // Min-heap (priority queue) to hold rope lengths.
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        // Insert all rope lengths into the heap.
        for (long rope : arr) {
            minHeap.offer(rope);
        }

        long totalCost = 0;

        // Continue until there is only one rope left in the heap.
        while (minHeap.size() > 1) {
            // Extract the two smallest ropes.
            long first = minHeap.poll();
            long second = minHeap.poll();

            // Combine the two ropes.
            long cost = first + second;
            
            // Add the cost to the total cost.
            totalCost += cost;

            // Insert the combined rope back into the heap.
            minHeap.offer(cost);
        }

        return totalCost;
    }
}
```

### Explanation
1. **Edge Case:** If the array contains only one rope, the cost is `0`, as there is nothing to combine.
2. **Priority Queue:** The priority queue (`minHeap`) is used to store the lengths of the ropes. This ensures that the smallest two ropes can always be extracted efficiently.
3. **Combining Ropes:** The two smallest ropes are extracted, combined, and their sum is added to the total cost. The new rope (sum) is then pushed back into the priority queue.
4. **Final Cost:** The total cost accumulated during the combination process is returned.

### Testing the Code
Here's how you can test the implementation:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        long[] ropes1 = {4, 3, 2, 6};
        System.out.println(solution.minCost(ropes1)); // Expected: 29

        long[] ropes2 = {1, 2, 3, 4, 5};
        System.out.println(solution.minCost(ropes2)); // Expected: 33

        long[] ropes3 = {5, 4, 2, 8};
        System.out.println(solution.minCost(ropes3)); // Expected: 36

        long[] ropes4 = {10};
        System.out.println(solution.minCost(ropes4)); // Expected: 0 (Only one rope)

        long[] ropes5 = {1, 2};
        System.out.println(solution.minCost(ropes5)); // Expected: 3
    }
}
```

### Best Practices Followed
- **Edge Case Handling:** The code properly handles the case when there is only one rope.
- **Efficient Data Structure:** A priority queue (min-heap) is used to ensure efficient retrieval of the smallest elements, resulting in an optimal solution.
- **Modularity:** The code is organized into a single function that performs a specific task—finding the minimum cost to combine ropes.
- **Variable Naming:** Descriptive variable names like `minHeap`, `totalCost`, `first`, and `second` are used to make the code easy to understand.
