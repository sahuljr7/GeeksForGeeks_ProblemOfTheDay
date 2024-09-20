# Facing the sun

To solve the problem of counting the number of buildings that can see sunlight, we need to iterate over the array of building heights from left to right. A building can see the sunlight if all the buildings to its left are shorter than it.

## Approach:
1. **Initial Setup:** The first building will always see the sunlight, so we can start by counting it.
2. **Track the Maximum Height:** As we iterate through the buildings, we will keep track of the maximum height encountered so far. If the current building's height is greater than this maximum, it can see the sunlight.
3. **Update Count and Maximum Height:** Each time we find a building taller than the current maximum height, we increment the count and update the maximum height.

This approach ensures we only pass through the array once, making it efficient.

## Time Complexity:
- **Time Complexity:** \(O(n)\), where \(n\) is the number of buildings. We only iterate through the array once.
- **Space Complexity:** \(O(1)\), as we are using only a few variables to store the count and the current maximum height.

## Java Code Implementation:

```java
// File: Solution.java
class Solution {
    // Function to count the number of buildings that can see sunlight
    public int countBuildings(int[] height) {
        // If no buildings, return 0
        if (height == null || height.length == 0) {
            return 0;
        }

        // The first building always sees the sunlight
        int count = 1;
        int maxHeight = height[0];

        // Iterate over the rest of the buildings
        for (int i = 1; i < height.length; i++) {
            // If the current building is taller than all previous ones, it can see the sunlight
            if (height[i] > maxHeight) {
                count++;
                maxHeight = height[i];  // Update the maximum height
            }
        }

        return count;
    }
}
```

### Explanation:
1. **Base Case:** If the array is empty, we immediately return 0 since no buildings are present.
2. **Initialize Count and Maximum Height:** The first building always sees the sunlight, so we start by setting `count = 1` and `maxHeight = height[0]`.
3. **Iterate Over Buildings:**
   - For each building, check if it is taller than the current `maxHeight`. If it is, increment the count and update `maxHeight`.
4. **Return Result:** After the loop, return the total count of buildings that can see sunlight.

### Testing the Code:

Here are some test cases to validate the implementation:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        int[] buildings1 = {7, 4, 8, 2, 9};
        System.out.println(solution.countBuildings(buildings1)); // Expected: 4

        int[] buildings2 = {1, 2, 3, 4, 5};
        System.out.println(solution.countBuildings(buildings2)); // Expected: 5

        int[] buildings3 = {5, 4, 3, 2, 1};
        System.out.println(solution.countBuildings(buildings3)); // Expected: 1

        int[] buildings4 = {10, 12, 11, 13, 9};
        System.out.println(solution.countBuildings(buildings4)); // Expected: 3

        int[] buildings5 = {};
        System.out.println(solution.countBuildings(buildings5)); // Expected: 0
    }
}
```

### Edge Cases:
1. **No Buildings:** An empty array returns 0 since no buildings are present.
2. **Increasing Heights:** All buildings can see the sunlight if the heights are in increasing order.
3. **Decreasing Heights:** Only the first building sees the sunlight if the heights are in strictly decreasing order.

### Best Practices Followed:
- **Efficiency:** The solution runs in linear time, \(O(n)\), and uses constant space, \(O(1)\).
- **Clear Variable Names:** `count` tracks the number of buildings that can see the sunlight, and `maxHeight` tracks the tallest building encountered so far.
- **Edge Case Handling:** Empty input arrays and arrays with varying height orders are properly handled.
