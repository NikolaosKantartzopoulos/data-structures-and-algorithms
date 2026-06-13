package com.nik.dsa.basicstructures.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Stage 1 — General binary tree algorithms (LeetCode Solution style).
 * Each method receives a {@link TreeNode} root, just like on LeetCode.
 */
public class BinaryTree {

    /**
     * Visit nodes in left → root → right order using recursion.
     * LeetCode 94 — Binary Tree Inorder Traversal.
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecursiveHelper(root, result);
        return result;
    }

    private void inorderTraversalRecursiveHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderTraversalRecursiveHelper(node.left, result);
        result.add(node.val);
        inorderTraversalRecursiveHelper(node.right, result);
    }

    /**
     * Visit nodes in left → root → right order using an explicit stack.
     * Same result as the recursive version.
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // 1. Travel down to the leftmost node of the current subtree
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // 2. Current is now null, so we pop from the stack
            current = stack.pop();
            result.add(current.val); // Add the node value

            // 3. We have visited the left and the root, now move to the right child
            current = current.right;
        }

        return result;
    }

    /**
     * Visit nodes in root → left → right order using recursion.
     * LeetCode 144 — Binary Tree Preorder Traversal.
     */
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement preorderTraversalRecursive");
    }

    /**
     * Visit nodes in root → left → right order using an explicit stack.
     */
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement preorderTraversalIterative");
    }

    /**
     * Visit nodes in left → right → root order using recursion.
     * LeetCode 145 — Binary Tree Postorder Traversal.
     */
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement postorderTraversalRecursive");
    }

    /**
     * Visit nodes in left → right → root order using an explicit stack.
     */
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement postorderTraversalIterative");
    }

    /**
     * Visit nodes level by level, left to right, using a queue (BFS).
     * LeetCode 102 — Binary Tree Level Order Traversal.
     */
    public List<Integer> levelOrderTraversalIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement levelOrderTraversalIterative");
    }

    /**
     * Visit nodes level by level using recursion that groups nodes by depth.
     */
    public List<Integer> levelOrderTraversalRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement levelOrderTraversalRecursive");
    }

    /** Returns true when root is null. */
    public boolean isEmpty(TreeNode root) {
        return root == null;
    }

    /**
     * Count all nodes in the tree using recursion.
     */
    public int sizeRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement sizeRecursive");
    }

    /** Count all nodes using a stack or queue instead of recursion. */
    public int sizeIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement sizeIterative");
    }

    /**
     * Find the longest root-to-leaf path length using recursion.
     * LeetCode 104 — Maximum Depth. Empty tree = 0, single node = 1.
     */
    public int heightRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement heightRecursive");
    }

    /** Find tree height using an iterative approach. */
    public int heightIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement heightIterative");
    }

    /** Check whether a value exists anywhere in the tree using recursion. */
    public boolean containsRecursive(TreeNode root, int val) {
        throw new UnsupportedOperationException("Implement containsRecursive");
    }

    /** Check whether a value exists using an explicit stack or queue. */
    public boolean containsIterative(TreeNode root, int val) {
        throw new UnsupportedOperationException("Implement containsIterative");
    }

    /**
     * Find the smallest value in the tree using recursion.
     * Throws IllegalStateException if root is null.
     */
    public int minValueRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement minValueRecursive");
    }

    /** Find the smallest value using an iterative walk. */
    public int minValueIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement minValueIterative");
    }

    /**
     * Find the largest value in the tree using recursion.
     * Throws IllegalStateException if root is null.
     */
    public int maxValueRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement maxValueRecursive");
    }

    /** Find the largest value using an iterative walk. */
    public int maxValueIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement maxValueIterative");
    }

    /**
     * Mirror the tree by swapping left and right at every node, using recursion.
     * LeetCode 226 — Invert Binary Tree. Returns the (possibly new) root.
     */
    public TreeNode invertRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement invertRecursive");
    }

    /**
     * Mirror the tree using a stack or queue. Returns the (possibly new) root.
     */
    public TreeNode invertIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement invertIterative");
    }

    /**
     * Check whether the tree is a mirror image of itself around the root.
     * LeetCode 101 — Symmetric Tree.
     */
    public boolean isSymmetricRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement isSymmetricRecursive");
    }

    /** Check symmetry using a queue or stack of paired nodes. */
    public boolean isSymmetricIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement isSymmetricIterative");
    }

    /**
     * Check whether two trees have identical structure and values.
     * LeetCode 100 — Same Tree.
     */
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        throw new UnsupportedOperationException("Implement isSameTreeRecursive");
    }

    /** Check structural equality using a queue or stack of paired nodes. */
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        throw new UnsupportedOperationException("Implement isSameTreeIterative");
    }

    /**
     * Check whether the tree is height-balanced.
     * LeetCode 110 — Balanced Binary Tree.
     */
    public boolean isBalancedRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement isBalancedRecursive");
    }

    /** Check height-balance using an iterative approach. */
    public boolean isBalancedIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement isBalancedIterative");
    }

    /**
     * Find the longest path between any two nodes, counted in edges.
     * LeetCode 543 — Diameter of Binary Tree.
     */
    public int diameterRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement diameterRecursive");
    }

    /** Find the diameter using an iterative approach. */
    public int diameterIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement diameterIterative");
    }

    /**
     * Find the lowest ancestor of nodes with values p and q.
     * LeetCode 236 — Lowest Common Ancestor of a Binary Tree.
     */
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, int p, int q) {
        throw new UnsupportedOperationException("Implement lowestCommonAncestorRecursive");
    }

    /** Find the LCA using an iterative search or parent-path approach. */
    public TreeNode lowestCommonAncestorIterative(TreeNode root, int p, int q) {
        throw new UnsupportedOperationException("Implement lowestCommonAncestorIterative");
    }

    /**
     * Find the maximum sum of any path from root down to a leaf.
     * Throws IllegalStateException if root is null.
     */
    public int maxRootToLeafPathSumRecursive(TreeNode root) {
        throw new UnsupportedOperationException("Implement maxRootToLeafPathSumRecursive");
    }

    /** Find the maximum root-to-leaf path sum using a stack-based walk. */
    public int maxRootToLeafPathSumIterative(TreeNode root) {
        throw new UnsupportedOperationException("Implement maxRootToLeafPathSumIterative");
    }
}
