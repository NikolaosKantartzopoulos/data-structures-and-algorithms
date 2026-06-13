package com.nik.dsa.basicstructures.binarytree;

/** Red-black tree node — same shape as {@link TreeNode} plus a color field. */
public class RbTreeNode {
    public enum Color {
        RED, BLACK
    }

    public int val;
    public RbTreeNode left;
    public RbTreeNode right;
    public Color color;

    public RbTreeNode(int val, Color color) {
        this.val = val;
        this.color = color;
    }
}
