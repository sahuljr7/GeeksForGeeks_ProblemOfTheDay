// https://www.geeksforgeeks.org/problems/longest-subarray-with-majority-greater-than-k/1
import java.util.*;

class Solution {
    static int longestSubarray(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;
        int cnt = 0;

        // Java's HashMap doesn't allow default values, so use putIfAbsent
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        for (int i = 0; i < n; i++) {
            // +1 if arr[i] > k, else -1
            cnt += (arr[i] > k) ? 1 : -1;

            // Save first occurrence of this prefix count
            map.putIfAbsent(cnt, i + 1);

            if (cnt > 0) {
                ans = i + 1;
            } else if (map.containsKey(cnt - 1)) {
                ans = Math.max(ans, i + 1 - map.get(cnt - 1));
            }
        }

        return ans;
    }
}
