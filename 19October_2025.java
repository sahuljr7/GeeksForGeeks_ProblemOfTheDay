// https://www.geeksforgeeks.org/problems/k-closest-values/1

/*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {

    public ArrayList<Integer> getKClosest(Node root, int target, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null || k <= 0) return result;

        Stack<Node> predecessors = new Stack<>();
        Stack<Node> successors = new Stack<>();

        initializePredecessor(root, target, predecessors);
        initializeSuccessor(root, target, successors);

        while (k-- > 0) {
            if (predecessors.isEmpty() && successors.isEmpty()) break;
            else if (predecessors.isEmpty()) result.add(getNextSuccessor(successors));
            else if (successors.isEmpty()) result.add(getNextPredecessor(predecessors));
            else {
                double predDiff = Math.abs(predecessors.peek().data - target);
                double succDiff = Math.abs(successors.peek().data - target);
                if (predDiff <= succDiff)
                    result.add(getNextPredecessor(predecessors));
                else
                    result.add(getNextSuccessor(successors));
            }
        }

        return result;
    }

    private void initializePredecessor(Node root, int target, Stack<Node> stack) {
        while (root != null) {
            if (root.data <= target) {
                stack.push(root);
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }

    private void initializeSuccessor(Node root, int target, Stack<Node> stack) {
        while (root != null) {
            if (root.data > target) {
                stack.push(root);
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    private int getNextPredecessor(Stack<Node> stack) {
        Node node = stack.pop();
        int value = node.data;
        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
        return value;
    }

    private int getNextSuccessor(Stack<Node> stack) {
        Node node = stack.pop();
        int value = node.data;
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return value;
    }
}
