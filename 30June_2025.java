import java.util.*;

class Solution {
    public int maxMinHeight(int[] arr, int k, int w) {
        int n = arr.length;
        int low = Arrays.stream(arr).min().getAsInt();
        int high = low + k; // Max increase possible

        int result = low;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canAchieve(arr, mid, k, w)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    private boolean canAchieve(int[] arr, int target, int k, int w) {
        int n = arr.length;
        int[] water = new int[n];
        int used = 0;
        int added = 0;

        for (int i = 0; i < n; i++) {
            if (i >= w) added -= water[i - w];

            int currHeight = arr[i] + added;
            if (currHeight < target) {
                int diff = target - currHeight;
                used += diff;
                if (used > k) return false;
                water[i] = diff;
                added += diff;
            }
        }

        return true;
    }
}
