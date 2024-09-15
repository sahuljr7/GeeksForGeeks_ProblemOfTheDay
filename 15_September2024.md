// Binary Tree to DLL

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
