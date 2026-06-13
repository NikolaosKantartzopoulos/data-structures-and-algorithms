package com.nik.dsa.basicstructures.binarytree;

/** AVL tree node — same shape as {@link TreeNode} plus a cached subtree height. */
public class AvlTreeNode {
    public int val;
    public AvlTreeNode left;
    public AvlTreeNode right;
    public int height;

    public AvlTreeNode(int val) {
        this.val = val;
        this.height = 1;
    }
}
