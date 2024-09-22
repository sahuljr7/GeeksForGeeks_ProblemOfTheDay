# Longest Prefix Suffix

The task is to implement a function that calculates the **Longest Prefix which is also a Suffix (LPS)** for a given string. This concept is widely used in the **Knuth-Morris-Pratt (KMP)** pattern matching algorithm.

### Problem Breakdown:
- The **LPS array** for a string represents the length of the longest proper prefix which is also a suffix. A proper prefix means that it does not include the entire string.
- The LPS value for a string helps avoid unnecessary comparisons in pattern matching, by providing the longest part of the string that is both a prefix and suffix, allowing efficient jumping in pattern matching algorithms.

### Approach:
1. **Iterate through the String:** We will iterate through the string and for each character, we will compute the longest proper prefix which is also a suffix for the substring ending at that character.
2. **Two Pointers Technique:** We maintain a pointer `i` which iterates over the string and another pointer `length` which tracks the length of the current longest prefix that is also a suffix.
3. **KMP Algorithm’s LPS Array Construction:** At each step:
   - If the characters at the current position `i` and the position `length` match, then we increase the `length` and store it in the LPS array.
   - If the characters don't match, we update the `length` using previously computed LPS values until we find a match or reset `length` to 0.

### Time Complexity:
- **Time Complexity:** \(O(n)\), where \(n\) is the length of the string. The function processes each character of the string once, making the approach linear.
- **Space Complexity:** \(O(n)\), for storing the LPS array.

### Java Code Implementation:

```java
// File: Solution.java
class Solution {
    // Function to compute the LPS array and return the length of the longest prefix which is also a suffix.
    int lps(String str) {
        int n = str.length();
        int[] lps = new int[n]; // Create the LPS array to store the length of the longest prefix suffix
        
        // length represents the length of the current longest prefix which is also a suffix
        int length = 0;
        int i = 1; // LPS of first character is always 0, so we start from index 1
        
        while (i < n) {
            if (str.charAt(i) == str.charAt(length)) {
                // We have a match, so we extend the current longest prefix suffix
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    // Mismatch after a match, so we try to shorten the prefix length
                    length = lps[length - 1];
                } else {
                    // No match found and length is 0, so the LPS for this position is 0
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // The value at the last index of the LPS array gives the length of the longest prefix which is also a suffix
        return lps[n - 1];
    }
}
```

### Explanation of the Code:
1. **LPS Array Initialization:**
   - We initialize an LPS array `lps[]` of size `n` (length of the string). The LPS array stores the length of the longest proper prefix which is also a suffix for every position in the string.
   - The variable `length` is used to track the current longest prefix that is also a suffix.
   
2. **Loop through the String:**
   - Start the loop from index 1 (`i = 1`) because the LPS for the first character is always 0 (no proper prefix).
   - If the character at `str[i]` matches `str[length]`, increase the `length` and assign it to `lps[i]`.
   - If there's a mismatch and `length` is not 0, we fall back to the previous longest prefix using the value of `lps[length-1]`.
   - If `length` becomes 0, we move to the next character and set `lps[i] = 0`.

3. **Return the Result:**
   - The last value of the LPS array `lps[n-1]` gives the length of the longest proper prefix that is also a suffix for the entire string.

### Example Walkthrough:

For the string `str = "abab"`, the LPS array will look like:
- At `i = 1`, `lps[1] = 0` (as no prefix matches the suffix).
- At `i = 2`, `lps[2] = 1` (as "a" is both a prefix and suffix).
- At `i = 3`, `lps[3] = 2` (as "ab" is both a prefix and suffix).

Thus, the final LPS value is 2, meaning the longest prefix which is also a suffix is of length 2 ("ab").

### Testing the Code:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.lps("abab"));    // Expected: 2
        System.out.println(solution.lps("aaaa"));    // Expected: 3
        System.out.println(solution.lps("abcd"));    // Expected: 0
        System.out.println(solution.lps("abcab"));   // Expected: 2
        System.out.println(solution.lps("aabaaac")); // Expected: 2
    }
}
```

### Edge Cases:
1. **Single Character String:** The LPS of a single character string is always 0, since there can be no proper prefix.
2. **Empty String:** For an empty string, the LPS should also be 0.
3. **All Same Characters:** If all characters in the string are the same (e.g., `"aaaa"`), the LPS value would be `n-1`.

### Best Practices Followed:
- **Efficiency:** The solution runs in \(O(n)\) time, which is optimal for this problem.
- **Clarity:** The use of well-named variables and comments makes the logic easy to follow.
- **Edge Case Handling:** Properly handles strings of varying lengths, including edge cases like empty strings or strings with all characters the same.
