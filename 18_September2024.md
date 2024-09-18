# Parenthesis Checker

To solve the problem of checking if parentheses in a given string are balanced, we can use a **stack**. The idea is to push opening brackets onto the stack and pop them when we encounter closing brackets. If at any point we encounter an unbalanced situation, we return `false`.

### Steps to Implement the Solution
1. **Use a Stack:** Use a stack to keep track of opening brackets (`(`, `{`, `[`). Whenever we encounter a closing bracket (`)`, `}`, `]`), we check if the stack is non-empty and the top of the stack has the matching opening bracket.
2. **Push and Pop:** 
   - If we encounter an opening bracket, push it onto the stack.
   - If we encounter a closing bracket, check the top of the stack:
     - If the stack is empty, the brackets are unbalanced.
     - If the top of the stack matches the closing bracket, pop the top element.
     - If there is a mismatch, return `false`.
3. **Final Check:** After processing all characters in the string, the stack should be empty for the brackets to be balanced. If the stack is not empty, return `false`.
4. **Edge Cases:** Handle cases where the string is empty or contains only opening/closing brackets.

### Complexity Analysis
- **Time Complexity:** `O(n)`, where `n` is the length of the string, as each character is processed once.
- **Space Complexity:** `O(n)` in the worst case, where the stack holds all opening brackets if they don't have a corresponding closing bracket.

### Java Code Implementation
Here’s the complete implementation of the `ispar` function:

```java
// File: Solution.java
import java.util.Stack;

class Solution {
    // Function to check if brackets are balanced or not.
    static boolean ispar(String x) {
        // Stack to store opening brackets
        Stack<Character> stack = new Stack<>();

        // Iterate through each character in the input string
        for (int i = 0; i < x.length(); i++) {
            char ch = x.charAt(i);

            // If the character is an opening bracket, push it to the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // If it's a closing bracket, check for the corresponding opening bracket
            else if (ch == ')' || ch == '}' || ch == ']') {
                // If stack is empty or top does not match the corresponding opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') || 
                    (ch == '}' && top != '{') || 
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If stack is empty, brackets are balanced
        return stack.isEmpty();
    }
}
```

### Explanation
1. **Stack Initialization:** We use a `Stack<Character>` to store opening brackets.
2. **Loop Through String:** We iterate through each character in the string:
   - If the character is an opening bracket (`(`, `{`, `[`), push it onto the stack.
   - If it is a closing bracket (`)`, `}`, `]`), check the stack:
     - If the stack is empty, it means there is no matching opening bracket, so return `false`.
     - Otherwise, pop the top element and check if it matches the current closing bracket. If it does not match, return `false`.
3. **Final Check:** After the loop, if the stack is empty, it means all brackets were properly balanced and closed. If not, return `false`.

### Testing the Code
Here are some test cases to validate the implementation:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(Solution.ispar("()"));            // Expected: true
        System.out.println(Solution.ispar("()[]{}"));        // Expected: true
        System.out.println(Solution.ispar("(]"));            // Expected: false
        System.out.println(Solution.ispar("([{}])"));        // Expected: true
        System.out.println(Solution.ispar("{[(])}"));        // Expected: false
        System.out.println(Solution.ispar(""));              // Expected: true (empty string)
        System.out.println(Solution.ispar("((((("));         // Expected: false
    }
}
```

### Best Practices Followed
- **Edge Case Handling:** Properly handles empty strings and cases where brackets are unmatched.
- **Optimal Data Structure:** Uses a stack to efficiently manage the bracket matching process.
- **Clear and Modular Code:** The code is straightforward, with clear logic for handling different types of brackets.
