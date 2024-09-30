# Merge two BST 's

To merge two Binary Search Trees (BSTs) into a single sorted list, we need an efficient approach that utilizes the properties of BSTs. A Binary Search Tree allows an in-order traversal to produce elements in sorted order. Therefore, we can use in-order traversal of both trees and merge the two sorted lists.

### Approach:
1. **In-Order Traversal**:
   - Perform an in-order traversal on both BSTs to extract their elements in sorted order.
   
2. **Merge Two Sorted Lists**:
   - After obtaining two sorted lists from the two BSTs, we can merge them efficiently using the two-pointer technique.

3. **Time Complexity**:
   - In-order traversal of a BST takes \(O(n)\), where \(n\) is the number of nodes. Since we're traversing both trees, the time complexity is \(O(n1 + n2)\), where \(n1\) and \(n2\) are the number of nodes in the first and second trees, respectively.
   - Merging two sorted lists also takes \(O(n1 + n2)\), so the overall time complexity is \(O(n1 + n2)\).

### Plan:
1. Perform an in-order traversal on both trees to collect their elements in two lists.
2. Merge the two sorted lists into one final sorted list.

### Steps:
1. **In-Order Traversal**: Implement a helper function to perform an in-order traversal on the BST and return a sorted list of the node values.
2. **Merge Function**: Implement a helper function to merge two sorted lists using the two-pointer technique.
3. **Main Function**: Use the in-order traversal results from both BSTs and merge the two sorted lists.

### Java Code Implementation:

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class Solution {
    // Function to return a list of integers denoting the node values of both BSTs in sorted order.
    public List<Integer> merge(Node root1, Node root2) {
        // Step 1: Perform in-order traversal to get sorted lists from both BSTs
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        inOrderTraversal(root1, list1);
        inOrderTraversal(root2, list2);
        
        // Step 2: Merge the two sorted lists
        return mergeSortedLists(list1, list2);
    }

    // Helper function to perform in-order traversal on the BST
    private void inOrderTraversal(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        
        // Traverse the left subtree
        inOrderTraversal(root.left, result);
        
        // Visit the current node
        result.add(root.data);
        
        // Traverse the right subtree
        inOrderTraversal(root.right, result);
    }

    // Helper function to merge two sorted lists
    private List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> mergedList = new ArrayList<>();
        int i = 0, j = 0;

        // Two-pointer technique to merge sorted lists
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                mergedList.add(list1.get(i));
                i++;
            } else {
                mergedList.add(list2.get(j));
                j++;
            }
        }

        // Add remaining elements from list1
        while (i < list1.size()) {
            mergedList.add(list1.get(i));
            i++;
        }

        // Add remaining elements from list2
        while (j < list2.size()) {
            mergedList.add(list2.get(j));
            j++;
        }

        return mergedList;
    }
}
```

### Explanation:

1. **In-Order Traversal**:
   - The `inOrderTraversal` method recursively traverses the left subtree, processes the current node, and then traverses the right subtree. This results in a sorted list of node values for a BST.
   
2. **Merging Two Sorted Lists**:
   - The `mergeSortedLists` method takes two sorted lists and merges them using a two-pointer technique. It compares the elements of both lists and adds the smaller element to the result list. Once one of the lists is exhausted, the remaining elements from the other list are added to the result list.
   
3. **Merge Function**:
   - The `merge` method is the main function that first collects the elements from both trees using in-order traversal and then merges them into a single sorted list using `mergeSortedLists`.

### Example:

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Creating first BST
        Node root1 = new Node(3);
        root1.left = new Node(1);
        root1.right = new Node(5);

        // Creating second BST
        Node root2 = new Node(4);
        root2.left = new Node(2);
        root2.right = new Node(6);

        // Merging both BSTs
        List<Integer> result = solution.merge(root1, root2);
        System.out.println(result);  // Expected Output: [1, 2, 3, 4, 5, 6]
    }
}
```

### Walkthrough of Example:
For two BSTs:
- **Tree 1**: 3, 1, 5
- **Tree 2**: 4, 2, 6
The in-order traversal for the first tree is `[1, 3, 5]` and for the second tree is `[2, 4, 6]`. The merged result is `[1, 2, 3, 4, 5, 6]`.

### Time Complexity:
- **In-Order Traversal**: Each BST is traversed once, so the time complexity is \(O(n1 + n2)\), where \(n1\) and \(n2\) are the number of nodes in the first and second BST, respectively.
- **Merging the Sorted Lists**: The merging process also takes \(O(n1 + n2)\), as we are simply merging two sorted lists.
- Thus, the overall time complexity is **\(O(n1 + n2)\)**.

### Edge Cases:
1. **Empty Trees**: If one or both trees are empty, the function should handle these cases and return the non-empty tree’s sorted values or an empty list if both trees are empty.
2. **Single Node Trees**: The function should work efficiently when the trees have only one node.

### Conclusion:
The solution efficiently merges two BSTs by leveraging in-order traversal and a two-pointer technique to merge two sorted lists. This approach ensures the merging is done in linear time, making it optimal for the problem at hand.
