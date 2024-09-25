# Palindrome Linked List

To solve the problem of checking whether a singly linked list is a palindrome, we need an efficient approach. A palindrome means that the elements of the list read the same forward and backward.

### Approach:
The optimal solution uses a **two-pointer technique** combined with reversing part of the linked list. The goal is to:
1. Find the middle of the list.
2. Reverse the second half of the list.
3. Compare the first half with the reversed second half.
4. Restore the list (optional but recommended for good practice).

### Steps:
1. **Find the Middle of the List**:
   - We use the **slow and fast pointer technique**. The slow pointer moves one step at a time, and the fast pointer moves two steps. When the fast pointer reaches the end, the slow pointer will be at the middle of the list.
   
2. **Reverse the Second Half**:
   - Once the middle is identified, reverse the second half of the list starting from the slow pointer's position.
   
3. **Compare the Two Halves**:
   - Compare the first half and the reversed second half. If they match, the list is a palindrome.
   
4. **Restore the List** (Optional):
   - For good practice, you can restore the original list by reversing the second half back to its original form.

### Time and Space Complexity:
- **Time Complexity**: \(O(n)\), where \(n\) is the number of nodes in the linked list. We traverse the list to find the middle, reverse half the list, and compare both halves.
- **Space Complexity**: \(O(1)\), because we reverse the list in-place without using any extra data structures.

### Java Code Implementation:

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Solution {
    // Function to check whether the list is a palindrome.
    boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true; // An empty or single-node list is always a palindrome.
        }

        // Step 1: Find the middle of the linked list
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the list
        Node secondHalfStart = reverseList(slow);

        // Step 3: Compare the first half and the reversed second half
        Node firstHalfStart = head;
        Node secondHalfCurrent = secondHalfStart;
        boolean isPalindrome = true;
        while (secondHalfCurrent != null) {
            if (firstHalfStart.data != secondHalfCurrent.data) {
                isPalindrome = false;
                break;
            }
            firstHalfStart = firstHalfStart.next;
            secondHalfCurrent = secondHalfCurrent.next;
        }

        // Step 4: Restore the second half (optional)
        reverseList(secondHalfStart);

        return isPalindrome;
    }

    // Helper function to reverse a linked list
    private Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
```

### Explanation:
1. **Find the Middle of the List**:
   - We use the slow and fast pointer technique to find the middle. The slow pointer moves one node at a time, while the fast pointer moves two nodes at a time. When the fast pointer reaches the end of the list, the slow pointer is at the middle.

2. **Reverse the Second Half**:
   - Once the middle of the list is identified, the list from that point onward is reversed. This allows us to easily compare the two halves of the list.

3. **Compare the Halves**:
   - We compare the first half of the list with the reversed second half node by node. If all the nodes match, the list is a palindrome.

4. **Restore the List** (Optional):
   - After comparison, we reverse the second half of the list back to its original form. This is optional but a good practice if you want to avoid modifying the original list structure.

### Example:

```java
public class Main {
    public static void main(String[] args) {
        // Creating a linked list: 1 -> 2 -> 3 -> 2 -> 1
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(head)); // Expected: true

        // Creating a non-palindromic linked list: 1 -> 2 -> 3 -> 4
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);

        System.out.println(solution.isPalindrome(head2)); // Expected: false
    }
}
```

### Edge Cases:
1. **Empty List:** An empty list is trivially a palindrome.
2. **Single Element List:** A single-node list is also a palindrome.
3. **Odd Length and Even Length Palindromes:** The solution handles both cases where the list has an odd number of nodes and an even number of nodes.

### Best Practices:
- **Efficiency:** The solution runs in linear time and uses constant space by modifying the list in-place.
- **Good Practice (Restore the List):** The second half of the list is restored to its original order after checking, ensuring that the original list structure is maintained.
