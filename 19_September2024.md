# Reverse words

To solve the problem of reversing the words in a given string where words are separated by dots (e.g., `"i.like.this.program.very.much"` becomes `"much.very.program.this.like.i"`), we need to break down the string and rearrange the words.

## Steps to Implement the Solution
1. **Split the String:** Use the `split` method to divide the string into an array of words using the delimiter `"."`.
2. **Reverse the Words:** Reverse the order of the words in the array.
3. **Join the Words:** Concatenate the reversed words back into a single string with `"."` as the separator.
4. **Edge Cases:** Handle cases where the string is empty or contains no dots.

## Complexity Analysis
- **Time Complexity:** `O(n)`, where `n` is the length of the string. This is because splitting the string and reversing the array both take linear time.
- **Space Complexity:** `O(n)` due to storing the split words in an array and constructing the final reversed string.

## Java Code Implementation
Here’s the implementation of the `reverseWords` function in Java:

```java
// File: Solution.java
class Solution {
    // Function to reverse words in a given string.
    String reverseWords(String str) {
        // Split the string into words using '.' as a delimiter
        String[] words = str.split("\\.");

        // Use StringBuilder to efficiently build the reversed string
        StringBuilder reversed = new StringBuilder();

        // Iterate over the words in reverse order
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            // Append '.' between words, except after the last word
            if (i > 0) {
                reversed.append(".");
            }
        }

        // Convert StringBuilder to String and return
        return reversed.toString();
    }
}
```

### Explanation
1. **Split the String:** The `split("\\.")` method splits the input string into an array of words using the dot (`"."`) as a delimiter. The double backslash is used to escape the dot since it's a special character in regular expressions.
2. **Reverse the Array:** Iterate through the array of words in reverse order, appending each word to a `StringBuilder`.
3. **Join with Dots:** Append a `"."` between words, avoiding an extra dot at the end.
4. **Return Result:** Convert the `StringBuilder` to a string and return it.

### Edge Cases
- **Empty String:** If the input string is empty, the `split` method will return an array with a single empty element. The code handles this naturally by returning an empty string.
- **No Dots:** If the string contains no dots, the `split` method returns an array with a single element, so reversing it doesn't change the result.

### Testing the Code
Here are some test cases to validate the implementation:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        System.out.println(solution.reverseWords("i.like.this.program.very.much")); // Expected: "much.very.program.this.like.i"
        System.out.println(solution.reverseWords("hello.world"));                  // Expected: "world.hello"
        System.out.println(solution.reverseWords("one.two.three"));               // Expected: "three.two.one"
        System.out.println(solution.reverseWords("a.b.c.d"));                    // Expected: "d.c.b.a"
        System.out.println(solution.reverseWords("singleword"));                 // Expected: "singleword"
        System.out.println(solution.reverseWords(""));                           // Expected: ""
    }
}
```

### Best Practices Followed
- **Efficiency:** Uses `StringBuilder` to construct the final reversed string, which is more efficient than string concatenation.
- **Clear Logic:** Splitting the string into words, reversing the array, and joining them back together is a straightforward and easy-to-understand approach.
- **Edge Case Handling:** Properly handles cases with no dots and empty input strings.
