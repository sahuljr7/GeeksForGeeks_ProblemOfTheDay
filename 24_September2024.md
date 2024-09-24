# Smallest window in a string containing all the characters of another string

To solve the problem of finding the smallest window in string `s` that contains all the characters of string `p`, we can use the **sliding window** technique along with hash maps to keep track of character frequencies.

## Problem Breakdown:
- We are given two strings `s` (the source string) and `p` (the target string), and we need to find the smallest contiguous substring in `s` that contains all the characters of `p`, including their frequencies.
- If no such window exists, return `"-1"`.

## Approach:
1. **Sliding Window Technique:** 
   - We will use two pointers (`left` and `right`) to form a sliding window that contains all characters from `p`.
   - We expand the window by moving the `right` pointer and contract the window by moving the `left` pointer when all characters are found.
   
2. **Character Frequency Maps:**
   - We need two maps:
     1. A map `pFreq` to store the frequency of characters in `p`.
     2. A map `windowFreq` to track the frequency of characters in the current window of `s`.
   - As we expand the window, we update the `windowFreq` and check if the current window contains all characters from `p` (i.e., `windowFreq` covers `pFreq`).
   
3. **Optimizing the Window:**
   - Whenever the window contains all characters of `p`, we try to minimize the window by moving the `left` pointer while ensuring that the window still contains all characters from `p`.

4. **Time Complexity:** 
   - The time complexity is \(O(n + m)\), where `n` is the length of string `s` and `m` is the length of string `p`. We process each character at most twice (once when expanding and once when contracting the window).
   
5. **Edge Cases:** 
   - If `p` is larger than `s`, it's impossible to find a valid window, so we return `"-1"`.
   - If no such window exists, return `"-1"`.

## Java Code Implementation:

```java
import java.util.HashMap;

class Solution {
    // Function to find the smallest window in string s that contains all characters of string p
    public static String smallestWindow(String s, String p) {
        // Base case: If p is longer than s, no valid window exists
        if (p.length() > s.length()) {
            return "-1";
        }

        // Frequency map for characters in p
        HashMap<Character, Integer> pFreq = new HashMap<>();
        for (char ch : p.toCharArray()) {
            pFreq.put(ch, pFreq.getOrDefault(ch, 0) + 1);
        }

        // Sliding window variables
        int left = 0, right = 0;
        int minLength = Integer.MAX_VALUE;  // To store the minimum window length
        int minLeft = 0;                    // To store the starting index of the minimum window
        int requiredMatches = pFreq.size(); // The number of unique characters in p we need to match
        int formedMatches = 0;              // To track how many unique characters we have matched

        // Frequency map for the sliding window
        HashMap<Character, Integer> windowFreq = new HashMap<>();

        while (right < s.length()) {
            char ch = s.charAt(right);
            // Add the current character to the window frequency map
            windowFreq.put(ch, windowFreq.getOrDefault(ch, 0) + 1);

            // If the current character matches the required frequency in p, we increment the formedMatches
            if (pFreq.containsKey(ch) && windowFreq.get(ch).intValue() == pFreq.get(ch).intValue()) {
                formedMatches++;
            }

            // Try to contract the window until it ceases to be valid (i.e., not all characters are matched)
            while (left <= right && formedMatches == requiredMatches) {
                // Update the minimum window length if the current window is smaller
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                // Now, try to shrink the window from the left
                char leftChar = s.charAt(left);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);

                // If the removed character reduces the match for p, decrease formedMatches
                if (pFreq.containsKey(leftChar) && windowFreq.get(leftChar) < pFreq.get(leftChar)) {
                    formedMatches--;
                }

                left++; // Shrink the window
            }

            right++; // Expand the window
        }

        // If no valid window is found, return "-1"
        return minLength == Integer.MAX_VALUE ? "-1" : s.substring(minLeft, minLeft + minLength);
    }
}
```

### Explanation:
1. **Base Case Check:**
   - If `p` is longer than `s`, we return `"-1"` immediately since it's impossible to find a valid window.
   
2. **Frequency Maps (`pFreq` and `windowFreq`):**
   - `pFreq` stores the frequency of characters in `p`. This helps us know what we need to match in `s`.
   - `windowFreq` tracks the frequency of characters in the current window of `s`. We update this map as we move the `right` and `left` pointers.
   
3. **Sliding Window Logic:**
   - Expand the window by moving the `right` pointer.
   - Check if the current window contains all required characters.
   - Once all characters are matched, try to shrink the window from the left to minimize the window size while ensuring it still contains all characters of `p`.
   
4. **Updating the Result:**
   - Whenever a valid window is found (i.e., all characters from `p` are present in the window), we check if its length is smaller than the current minimum window length. If so, we update the result.
   
5. **Returning the Result:**
   - If no valid window is found, return `"-1"`. Otherwise, return the smallest window found.

### Time Complexity:
- **Time Complexity:** \(O(n + m)\), where `n` is the length of string `s` and `m` is the length of string `p`.
  - We iterate over the string `s` once with two pointers (`left` and `right`), and for each character, we update the frequency map in constant time.
  
- **Space Complexity:** \(O(m + n)\), due to the space used by the two frequency maps (`pFreq` and `windowFreq`).

### Testing the Code:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(Solution.smallestWindow("timetopractice", "toc"));  // Expected: "topract"
        System.out.println(Solution.smallestWindow("zoomlazapzo", "oza"));    // Expected: "zoa"
        System.out.println(Solution.smallestWindow("a", "aa"));               // Expected: "-1"
        System.out.println(Solution.smallestWindow("thisisateststring", "tist")); // Expected: "tstri"
    }
}
```

### Edge Cases:
1. **No Valid Window:** If there is no valid window, such as when `p` contains characters not in `s`, the function should return `"-1"`.
2. **Exact Match:** If `p` is exactly the same as `s`, the function should return the entire string.
3. **String Lengths:** The function should handle strings of varying lengths, including very short and very long strings.

### Best Practices:
- **Efficiency:** The sliding window technique ensures that the solution runs in linear time, which is optimal for this type of problem.
- **Clarity:** The code is well-structured, with clear comments and meaningful variable names to make the logic easy to follow.
- **Edge Case Handling:** The function handles various edge cases, such as no valid window, small strings, and large strings.
