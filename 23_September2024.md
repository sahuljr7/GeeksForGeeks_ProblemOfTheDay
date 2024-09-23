# Missing And Repeating

To solve the problem of finding the missing and repeating elements in an array, we can break it down systematically. The array contains integers from 1 to \( n \), but one element is missing, and one is repeated. We need to identify both efficiently.

## Approach:
We aim to find both the missing and repeating elements in an array with \( n \) elements using minimal space and time. Here, we will focus on the mathematical properties of the array and leverage the XOR operation for an efficient solution.

### Plan:
1. **XOR-based Approach:**
   - XOR can be very useful in finding two different elements in an array because the XOR of two identical numbers results in 0, and the XOR of a number with 0 results in the number itself.
   - We can XOR all elements of the array and the numbers from 1 to \( n \). This will leave us with the XOR of the missing number and the repeating number.
   - Once we have the XOR of the missing and repeating numbers, we can separate the two numbers based on the properties of XOR.

### Steps:
1. **Step 1: Compute XOR of all elements in the array and from 1 to \( n \).**
   - Let `xor = arr[0] ^ arr[1] ^ ... ^ arr[n-1] ^ 1 ^ 2 ^ ... ^ n`.
   - The result of this XOR operation will be `xor = missing ^ repeating` because all other elements will cancel out.
   
2. **Step 2: Use the rightmost set bit to separate the missing and repeating numbers.**
   - The XOR result will be a non-zero number, and the rightmost set bit will help us partition the numbers into two groups.
   - XOR the numbers again (both the array and 1 to \( n \)) based on this bit to find the missing and repeating numbers.

### Time Complexity:
- **Time Complexity:** \( O(n) \) — We iterate over the array multiple times, but in linear time.
- **Space Complexity:** \( O(1) \) — We use a constant amount of extra space.

### Java Code Implementation:

```java
class Solve {
    // Function to find the missing and repeating elements
    int[] findTwoElement(int arr[]) {
        int n = arr.length;
        int xor = 0;

        // Step 1: XOR all elements of the array and numbers from 1 to n
        for (int i = 0; i < n; i++) {
            xor ^= arr[i];    // XOR with elements of the array
            xor ^= (i + 1);   // XOR with 1 to n (i + 1)
        }

        // Step 2: Find the rightmost set bit in the xor result
        int setBit = xor & ~(xor - 1);

        // Initialize two buckets to distinguish missing and repeating numbers
        int x = 0, y = 0;

        // Step 3: Partition numbers into two buckets based on the set bit
        for (int i = 0; i < n; i++) {
            if ((arr[i] & setBit) != 0) {
                x ^= arr[i];  // XOR the elements in the first bucket
            } else {
                y ^= arr[i];  // XOR the elements in the second bucket
            }

            if (((i + 1) & setBit) != 0) {
                x ^= (i + 1);  // XOR the numbers 1 to n in the first bucket
            } else {
                y ^= (i + 1);  // XOR the numbers 1 to n in the second bucket
            }
        }

        // Step 4: Determine which is the missing number and which is the repeating number
        int[] result = new int[2];
        // Check the array to find which one of x or y is the repeating number
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                result[0] = x;  // Repeating number
                result[1] = y;  // Missing number
                break;
            } else if (arr[i] == y) {
                result[0] = y;  // Repeating number
                result[1] = x;  // Missing number
                break;
            }
        }

        return result;
    }
}
```

### Explanation of the Code:

1. **XOR Computation:**
   - We start by XORing all elements in the array and all integers from 1 to \( n \). This results in `xor = missing ^ repeating`, since the repeated number cancels itself, and the numbers from 1 to \( n \) will cancel out except for the missing number.

2. **Partitioning the Numbers:**
   - The XOR result gives us information about the two different bits of the missing and repeating numbers. The rightmost set bit in `xor` is used to partition the numbers into two groups. 
   - This bit separates the numbers into two different categories: one set where this bit is set (`1`) and another set where this bit is not set (`0`).

3. **Final Identification:**
   - By XORing the numbers in each group, we isolate the missing and repeating numbers.
   - Finally, we check the original array to determine which one is the repeating number.

### Testing the Code:

Here are some test cases to validate the implementation:

```java
public class Main {
    public static void main(String[] args) {
        Solve solve = new Solve();
        
        // Test case 1
        int[] arr1 = {1, 3, 3};
        int[] result1 = solve.findTwoElement(arr1);
        System.out.println("Repeating: " + result1[0] + ", Missing: " + result1[1]); // Expected: Repeating: 3, Missing: 2

        // Test case 2
        int[] arr2 = {4, 3, 6, 2, 1, 1};
        int[] result2 = solve.findTwoElement(arr2);
        System.out.println("Repeating: " + result2[0] + ", Missing: " + result2[1]); // Expected: Repeating: 1, Missing: 5

        // Test case 3
        int[] arr3 = {7, 3, 4, 5, 5, 6, 2};
        int[] result3 = solve.findTwoElement(arr3);
        System.out.println("Repeating: " + result3[0] + ", Missing: " + result3[1]); // Expected: Repeating: 5, Missing: 1
    }
}
```

### Edge Cases:
1. **Array of Length 2:** Handle cases with the smallest valid input size.
2. **All Numbers Present Except One:** Ensure that the logic correctly identifies the missing number.
3. **Large Arrays:** Handle cases with a large number of elements efficiently.

### Best Practices Followed:
- **Efficiency:** The solution runs in \( O(n) \) time and uses \( O(1) \) extra space.
- **Edge Case Handling:** The solution checks for different edge cases, such as the smallest input size and numbers close to each other.
- **Readability:** The code is modular, with clear variable names and step-by-step logic.
