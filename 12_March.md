## **Optimized Approach: Dynamic Programming with Space Optimization (O(N) Time, O(1) Space)**
The **Min Cost Climbing Stairs** problem follows a **Dynamic Programming (DP) approach** similar to the **Fibonacci sequence**.

---

### **Key Observations**
1. **You can start from either step `0` or `1`**.  
2. **Recurrence Relation**:
   - To reach step `i`, you can either come from `i-1` or `i-2`, paying the respective cost.
   - \[
   dp[i] = \min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
   \]
3. **Space Optimization**:
   - Instead of `O(N)`, use **two variables** (`prev2` and `prev1`) to store only the last two computed values.

---

## **Optimized Java Code**
```java
class Solution {
    static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n == 2) return Math.min(cost[0], cost[1]);

        int prev2 = cost[0], prev1 = cost[1];

        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }

        return Math.min(prev1, prev2);
    }
}
```

---

## **Time & Space Complexity**
| **Operation**  | **Time Complexity** | **Space Complexity** |
|---------------|------------------|------------------|
| **Iterative DP Computation** | **O(N)** | **O(1)** |
