# Multiply two linked lists

To solve the problem of multiplying two numbers represented by two linked lists, where each node contains a single digit of the number, we need to convert the two linked lists into integers, multiply them, and return the result.

### Problem Breakdown:
- Each linked list represents a number where the head node is the most significant digit.
- We need to multiply the two numbers formed by the linked lists and return the result.

### Approach:
1. **Convert the Linked Lists to Numbers**:
   - We traverse both linked lists, starting from the head, and accumulate the value of the number. Since the list is structured with the most significant digit first, we can treat this as forming the number by repeatedly multiplying by 10 and adding the current node’s value.
   
2. **Multiply the Two Numbers**:
   - Once we have the two numbers, we simply multiply them to get the result.
   
3. **Handle Large Numbers**:
   - Since the numbers can be very large, we use `long` (64-bit) to store the intermediate and final results.

### Plan:
1. **Helper Function**: Create a helper function to convert a linked list into an integer.
2. **Main Function**: Use the helper function to convert both linked lists, multiply the results, and return the final product modulo \(10^9 + 7\) to handle large numbers as required by the problem constraints.

### Constraints:
- The result should be returned modulo \(10^9 + 7\) (a common requirement to prevent overflow in competitive programming).

### Time Complexity:
- **Time Complexity**: \(O(n + m)\), where `n` is the length of the first linked list and `m` is the length of the second linked list. Each list is traversed once to form the numbers.
- **Space Complexity**: \(O(1)\), as we are using a few variables for storing the intermediate results.

### Java Code Implementation:

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

class Solution {
    private static final long MOD = 1000000007;

    // Function to multiply two numbers represented by linked lists
    public long multiplyTwoLists(Node first, Node second) {
        // Convert both linked lists to numbers
        long num1 = listToNumber(first);
        long num2 = listToNumber(second);
        
        // Multiply the two numbers and return the result modulo 10^9 + 7
        return (num1 * num2) % MOD;
    }

    // Helper function to convert a linked list to a number
    private long listToNumber(Node head) {
        long number = 0;
        Node current = head;
        
        while (current != null) {
            number = (number * 10 + current.data) % MOD;  // Handle large numbers
            current = current.next;
        }
        
        return number;
    }
}
```

### Explanation:

1. **Helper Function (`listToNumber`)**:
   - This function converts the linked list into an integer by iterating over the list and accumulating the value.
   - Each node’s value is treated as a digit. The number is formed by multiplying the current result by 10 and adding the node’s value.
   - The modulo operation is applied at each step to ensure that the intermediate results don’t overflow and stay within bounds.

2. **Main Function (`multiplyTwoLists`)**:
   - The main function calls `listToNumber` for both the linked lists, multiplies the two results, and returns the product modulo \(10^9 + 7\).

### Example Walkthrough:

Let's say the two linked lists represent the numbers 123 and 456.

#### Linked List 1: 
- Nodes: `1 -> 2 -> 3`
- The number formed: \(1 \times 10^2 + 2 \times 10^1 + 3 \times 10^0 = 123\)

#### Linked List 2:
- Nodes: `4 -> 5 -> 6`
- The number formed: \(4 \times 10^2 + 5 \times 10^1 + 6 \times 10^0 = 456\)

#### Result:
- The product of 123 and 456 is \(123 \times 456 = 56088\).
- Since \(56088\) is less than \(10^9 + 7\), the result remains \(56088\).

### Example Usage:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Create first linked list: 1 -> 2 -> 3 (representing 123)
        Node first = new Node(1);
        first.next = new Node(2);
        first.next.next = new Node(3);
        
        // Create second linked list: 4 -> 5 -> 6 (representing 456)
        Node second = new Node(4);
        second.next = new Node(5);
        second.next.next = new Node(6);
        
        // Multiply the two linked lists
        long result = solution.multiplyTwoLists(first, second);
        System.out.println("Product: " + result);  // Expected output: 56088
    }
}
```

### Edge Cases:

1. **Empty Lists**: If either of the linked lists is empty, the result should be `0` because multiplying by `0` results in `0`.
2. **Single Node Lists**: If both lists contain a single node, the solution should still work, returning the product of the two digits.
3. **Very Large Numbers**: The modulo operation ensures that very large numbers are handled without overflow.

### Conclusion:
The solution efficiently multiplies two numbers represented by linked lists by converting them to integers and using a modulo operation to handle large numbers. The time complexity is linear with respect to the lengths of the two lists, and the space complexity is constant, making it optimal for this problem.
