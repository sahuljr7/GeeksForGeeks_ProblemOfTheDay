# Longest valid Parentheses

To solve the problem of finding the length of the longest valid parentheses substring in Java, we need to break down the logic and select the best approach for optimal performance.

## Steps to Achieve the Solution:
1. **Use a Stack**: One common approach to solving this problem is to use a stack to keep track of indices of the parentheses. This will help in matching parentheses and calculating the maximum length of valid substrings efficiently.
2. **Initialize a Stack**: Push `-1` to the stack initially to handle the edge case for the first valid substring.
3. **Iterate Through the String**: For each character in the string:
   - If it is an opening bracket `'('`, push its index onto the stack.
   - If it is a closing bracket `')'`, pop the top element from the stack.
     - If the stack is empty after popping, push the current index as a new base.
     - If the stack is not empty, calculate the length of the current valid substring using the current index minus the top index of the stack.
4. **Track Maximum Length**: During each valid substring calculation, update the maximum length.
5. **Return the Result**: At the end, the maximum length found during the iteration is the result.

This approach has a time complexity of \(O(n)\) and a space complexity of \(O(n)\) because of the stack.

## Java Code Implementation:
Here's the complete code for the solution:

```java
// File: Solution.java
class Solution {
    static int maxLength(String S) {
        // Stack to store the indices of parentheses
        Stack<Integer> stack = new Stack<>();
        // Push -1 to handle the base case
        stack.push(-1);
        int maxLength = 0;

        // Iterate through each character in the string
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);

            if (ch == '(') {
                // Push the index of '(' to the stack
                stack.push(i);
            } else {
                // Pop the top element (matching '(' index)
                stack.pop();

                if (stack.isEmpty()) {
                    // If stack is empty, push the current index as a new base
                    stack.push(i);
                } else {
                    // Calculate the length of the valid substring
                    int length = i - stack.peek();
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}
```

### Explanation:
1. **Stack Initialization**: The stack is used to keep track of indices. `-1` is pushed initially to handle edge cases where the entire string is valid.
2. **Iteration**: The loop goes through each character:
   - If it's `'('`, its index is pushed onto the stack.
   - If it's `')'`, the top index is popped:
     - If the stack is empty after popping, the current index is pushed to serve as the new base.
     - If the stack is not empty, the length of the valid substring is calculated and the maximum length is updated.
3. **Return**: The maximum length of valid parentheses is returned.

### Testing:
To test the function, you can use the following sample cases:

```java
public class Main {
    public static void main(String[] args) {
        // Test cases
        System.out.println(Solution.maxLength("(()") == 2); // Expected: 2
        System.out.println(Solution.maxLength(")()())") == 4); // Expected: 4
        System.out.println(Solution.maxLength("") == 0); // Expected: 0
        System.out.println(Solution.maxLength("()(()))") == 6); // Expected: 6
        System.out.println(Solution.maxLength("()()") == 4); // Expected: 4
    }
}
```

## Best Practices Followed:
- **Edge Case Handling**: The stack is initialized with `-1` to handle cases where the first valid substring starts at the beginning.
- **Efficient Processing**: This solution processes the input in linear time \(O(n)\), making it efficient for larger strings.
- **Clear Variable Naming**: Variables like `maxLength` and `stack` are named to reflect their purposes clearly.
