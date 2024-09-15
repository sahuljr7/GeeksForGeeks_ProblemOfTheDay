# Binary Tree to DLL

To convert a binary tree to a doubly linked list (DLL) in Java, we'll need to follow an in-order traversal of the binary tree. This traversal will allow us to process nodes in ascending order based on the binary tree's structure. Here's a step-by-step breakdown of how we can implement this conversion:

### Steps to Implement the Solution

1. **In-Order Traversal:** Perform an in-order traversal (left-root-right) of the binary tree to visit the nodes in sorted order.
2. **Track Previous Node:** Keep track of the previous node while traversing the tree. This helps in linking the current node to the previous node in the DLL.
3. **Head of DLL:** Identify the head of the doubly linked list. This will be the leftmost node in the tree during the in-order traversal.
4. **Link Nodes:** While traversing, modify the pointers:
   - Set the `left` pointer of the current node to point to the previous node.
   - Set the `right` pointer of the previous node to point to the current node.
5. **Edge Case:** Handle the edge case where the tree is empty (i.e., the root is `null`).

### Code Implementation in Java

Here’s the complete implementation:

```java
class Solution {
    // Head of the DLL
    private Node head = null;
    // Previous pointer to keep track of the previously processed node
    private Node prev = null;

    // Function to convert binary tree to doubly linked list and return it.
    Node bToDLL(Node root) {
        // Edge case: If the root is null, return null
        if (root == null) {
            return null;
        }

        // Perform in-order traversal
        convertToDLL(root);

        // Return the head of the constructed DLL
        return head;
    }

    // Helper function to convert binary tree to doubly linked list using in-order traversal
    private void convertToDLL(Node current) {
        // Base case: If the current node is null, return
        if (current == null) {
            return;
        }

        // Recur to the left subtree
        convertToDLL(current.left);

        // Process the current node
        if (prev == null) {
            // If this is the leftmost node, it becomes the head of the DLL
            head = current;
        } else {
            // Modify pointers to link the previous node with the current node
            prev.right = current;
            current.left = prev;
        }

        // Update prev to the current node
        prev = current;

        // Recur to the right subtree
        convertToDLL(current.right);
    }
}
```

### Explanation of the Code

1. **Class Members:** The `Solution` class contains two member variables:
   - `head`: Tracks the head of the DLL (leftmost node).
   - `prev`: Tracks the previous node during the traversal, used to link nodes in the DLL.

2. **`bToDLL` Method:** 
   - This method takes the root of the binary tree as input.
   - It calls the helper method `convertToDLL` to perform the in-order traversal and modify the node pointers.
   - Finally, it returns the head of the newly constructed DLL.

3. **`convertToDLL` Method:** 
   - It performs an in-order traversal on the binary tree.
   - When processing each node:
     - If `prev` is `null`, it means we're at the leftmost node, which becomes the head of the DLL.
     - Otherwise, it links the current node with the `prev` node to form the DLL connections.
   - After linking, it updates `prev` to the current node and recurses on the right subtree.

4. **In-Order Traversal:** The use of in-order traversal ensures that nodes are processed in ascending order, which is essential for a correctly ordered doubly linked list.

### Complexity Analysis

- **Time Complexity:** The code performs an in-order traversal of the binary tree, which visits each node exactly once. Therefore, the time complexity is O(N), where N is the number of nodes in the binary tree.
- **Space Complexity:** The space complexity is O(H) due to the recursion stack, where H is the height of the binary tree. In the worst case (for a completely unbalanced tree), the height could be N, making the space complexity O(N). For a balanced tree, it would be O(log N).

### Testing the Code

To ensure the code functions correctly, you should test it with various binary trees:
1. **Balanced Binary Tree**
2. **Left-Skewed Binary Tree** (all nodes have only left children)
3. **Right-Skewed Binary Tree** (all nodes have only right children)
4. **Empty Tree** (edge case where the root is `null`)
5. **Single Node Tree**

Here is an example of testing the code with a simple tree structure:

### Example Test Case

**Binary Tree Structure:**
```
    10
   /  \
  5   20
 / \    \
3   7    30
```
**Expected Doubly Linked List:** `3 <-> 5 <-> 7 <-> 10 <-> 20 <-> 30`

**Output Validation:** Traverse the doubly linked list from the head and verify the order of nodes.
