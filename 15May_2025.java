// https://www.geeksforgeeks.org/problems/substrings-with-similar-first-and-last-characters3644/1

class Solution {
    public int countSubstring(String s) {
        int[] freq = new int[26];
        int n = s.length();

        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int count = 0;
        for (int f : freq) {
            count += f + (f * (f - 1)) / 2;
        }

        return count;
    }
}
