// https://www.geeksforgeeks.org/problems/decode-the-pattern1138/1

class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String curr = "1";

        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int len = curr.length();
            int count = 1;

            for (int j = 1; j < len; j++) {
                if (curr.charAt(j) == curr.charAt(j - 1)) {
                    count++;
                } else {
                    next.append(count).append(curr.charAt(j - 1));
                    count = 1;
                }
            }
            // Append the last group
            next.append(count).append(curr.charAt(len - 1));
            curr = next.toString();
        }

        return curr;
    }
}
