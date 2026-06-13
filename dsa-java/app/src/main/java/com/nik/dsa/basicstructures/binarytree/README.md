# Binary Trees — Study Path

Practice these four structures **in order**. Each builds on the previous one.

```
Binary Tree  →  Binary Search Tree  →  AVL Tree  →  Red-Black Tree
  (shape)         (ordering)           (strict balance)  (practical balance)
```

**LeetCode-style API:** Each class is like a LeetCode `Solution` — methods take a `root` node and return results (or an updated root for insert/delete/invert). No hidden state.

```java
BinaryTree solution = new BinaryTree();
List<Integer> result = solution.inorderTraversalRecursive(root);
```

Run tests for one class at a time:

```bash
./gradlew test --tests "com.nik.dsa.basicstructures.binarytree.BinaryTreeSpec"
./gradlew test --tests "com.nik.dsa.basicstructures.binarytree.BinarySearchTreeSpec"
./gradlew test --tests "com.nik.dsa.basicstructures.binarytree.AvlTreeSpec"
./gradlew test --tests "com.nik.dsa.basicstructures.binarytree.RedBlackTreeSpec"
```

Every algorithm that supports both styles has `*Recursive` and `*Iterative` methods — implement both.

---

## 1. Binary Tree (`BinaryTree.java`)

**Node:** `TreeNode` — `val`, `left`, `right` (identical to LeetCode)

**What you practice:**
- DFS traversals (inorder, preorder, postorder)
- BFS level-order traversal
- Size, height, search, min/max
- Structural problems (invert, symmetric, same tree, balanced check, diameter)
- Lowest common ancestor, root-to-leaf path sum

**Example:**

```java
TreeNode root = new TreeNode(1,
    new TreeNode(2, new TreeNode(4), new TreeNode(5)),
    new TreeNode(3));

BinaryTree solution = new BinaryTree();
solution.inorderTraversalRecursive(root);  // [4, 2, 5, 1, 3]
```

---

## 2. Binary Search Tree (`BinarySearchTree.java`)

**Node:** `TreeNode` (same as stage 1 — ordering is enforced by your logic)

**What you practice:**
- Insert and delete — methods **return the updated root**
- BST search, min/max, sorted inorder
- Validate BST (LeetCode 98)

**Example:**

```java
BinarySearchTree bst = new BinarySearchTree();
TreeNode root = null;
root = bst.insertRecursive(root, 4);
root = bst.insertRecursive(root, 2);
bst.inorderTraversalRecursive(root);  // [2, 4]
```

---

## 3. AVL Tree (`AvlTree.java`)

**Node:** `AvlTreeNode` — `val`, `left`, `right`, `height`

**What you practice:**
- Rotations (single + double)
- Balance factor and height tracking
- Insert/delete that rebalance automatically

---

## 4. Red-Black Tree (`RedBlackTree.java`)

**Node:** `RbTreeNode` — `val`, `left`, `right`, `color`

**What you practice:**
- Insert with recoloring and rotations
- Red-black property validation
- Fewer rotations than AVL on average

---

## Comparison

| | Binary Tree | BST | AVL | Red-Black |
|---|-------------|-----|-----|-----------|
| Node class | `TreeNode` | `TreeNode` | `AvlTreeNode` | `RbTreeNode` |
| Ordering | no | yes | yes | yes |
| Self-balancing | no | no | yes | yes |
| Extra per node | — | — | height | color |

---

## File map

| File | Purpose |
|------|---------|
| `TreeNode.java` | LeetCode node — stages 1 & 2 |
| `BinaryTree.java` | Stage 1 — general algorithms |
| `BinarySearchTree.java` | Stage 2 — ordered tree operations |
| `AvlTreeNode.java` / `AvlTree.java` | Stage 3 — height-balanced BST |
| `RbTreeNode.java` / `RedBlackTree.java` | Stage 4 — color-balanced BST |

---

## Implementation pattern

Public method receives `root`; private helper does the recursion:

```java
public List<Integer> inorderTraversalRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    inorderHelper(root, result);
    return result;
}

private void inorderHelper(TreeNode node, List<Integer> result) {
    if (node == null) return;
    inorderHelper(node.left, result);
    result.add(node.val);
    inorderHelper(node.right, result);
}
```

Insert/delete on BST/AVL/RB return the (possibly new) root — always reassign:

```java
root = bst.insertRecursive(root, val);
root = bst.deleteRecursive(root, val);
```
