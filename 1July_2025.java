import java.util.*;

class Solution {
    public int substrCount(String s, int k) {
        if (s.length() < k) return 0;

        int count = 0;
        Map<Character, Integer> freq = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            // Add new character
            char ch = s.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            // Remove character outside window
            if (i >= k) {
                char out = s.charAt(i - k);
                freq.put(out, freq.get(out) - 1);
                if (freq.get(out) == 0) freq.remove(out);
            }

            // Check condition only when window size is k
            if (i >= k - 1 && freq.size() == k - 1) {
                for (int val : freq.values()) {
                    if (val == 2) {
                        count++;
                        break;
                    }
                }
            }
        }

        return count;
    }
}
