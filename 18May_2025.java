// https://www.geeksforgeeks.org/problems/level-order-traversal-in-spiral-form/1
import java.util.*;

class Solution {
    public ArrayList<Integer> findSpiral(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> s1 = new Stack<>(); // right to left
        Stack<Node> s2 = new Stack<>(); // left to right

        s1.push(root);

        while (!s1.isEmpty() || !s2.isEmpty()) {
            while (!s1.isEmpty()) {
                Node node = s1.pop();
                result.add(node.data);

                // Push children in reverse order for next level
                if (node.right != null) s2.push(node.right);
                if (node.left != null) s2.push(node.left);
            }

            while (!s2.isEmpty()) {
                Node node = s2.pop();
                result.add(node.data);

                // Push children in normal order
                if (node.left != null) s1.push(node.left);
                if (node.right != null) s1.push(node.right);
            }
        }

        return result;
    }
}
