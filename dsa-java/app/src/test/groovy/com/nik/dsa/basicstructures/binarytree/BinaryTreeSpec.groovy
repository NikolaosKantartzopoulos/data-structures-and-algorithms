package com.nik.dsa.basicstructures.binarytree

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * Comprehensive test suite for fundamental binary tree algorithms.
 *
 * Each algorithm that supports both styles has {@code *Recursive} and
 * {@code *Iterative} variants — implement both until all tests pass.
 *
 * <pre>
 *        1
 *       / \
 *      2   3
 *     / \
 *    4   5
 * </pre>
 */
class BinaryTreeSpec extends Specification {

    @Subject
    BinaryTree solution = new BinaryTree()

    private static final List<String> BOTH = ['Recursive', 'Iterative']

    // ── Helpers ─────────────────────────────────────────────────────────────

    private static TreeNode n(int val, TreeNode left = null, TreeNode right = null) {
        new TreeNode(val, left, right)
    }

    private static TreeNode sampleTreeRoot() {
        n(1, n(2, n(4), n(5)), n(3))
    }

    private static TreeNode bstTreeRoot() {
        n(4, n(2, n(1), n(3)), n(6, n(5), n(7)))
    }

    private static TreeNode lcaTreeRoot() {
        n(3, n(5, n(6), n(2, n(7), n(4))), n(1, n(0), n(8)))
    }

    private static TreeNode symmetricTreeRoot() {
        n(1, n(2, n(3), n(4)), n(2, n(4), n(3)))
    }

    private static TreeNode invertTargetRoot() {
        n(4, n(2, n(1), n(3)), n(7, n(6), n(9)))
    }

    /** Duplicate each data row once per approach (Recursive, then Iterative). */
    private static List duplicateForBoth(List rows) {
        rows.collectMany { row -> BOTH.collect { approach -> [approach] + row } }
    }

    // ── isEmpty ─────────────────────────────────────────────────────────────

    def "isEmpty returns true for a newly constructed empty tree"() {
        expect:
            solution.isEmpty(null)
    }

    def "isEmpty returns false when the tree has at least one node"() {
        expect:
            !solution.isEmpty(n(1))
            !solution.isEmpty(sampleTreeRoot())
    }

    // ── size ────────────────────────────────────────────────────────────────

    @Unroll
    def "#approach size of #description is #expected"() {
        expect:
            solution."size$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty tree',       null,                                          0],
                ['single node',      n(42),                                         1],
                ['sample 5-node',    sampleTreeRoot(),                              5],
                ['left-skewed 3',    n(1, null, n(2, null, n(3))),                  3],
                ['full BST 7-node',  bstTreeRoot(),                                 7],
            ])
    }

    // ── height ─────────────────────────────────────────────────────────────

    @Unroll
    def "#approach height of #description is #expected"() {
        expect:
            solution."height$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty tree',      null,                                          0],
                ['single node',     n(1),                                          1],
                ['sample 5-node',   sampleTreeRoot(),                              3],
                ['left-skewed 4',   n(1, n(2, n(3, n(4), null), null), null),     4],
                ['balanced BST',    bstTreeRoot(),                                 3],
            ])
    }

    // ── inorderTraversal ────────────────────────────────────────────────────

    @Unroll
    def "#approach inorder of #description is #expected"() {
        expect:
            solution."inorderTraversal$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty',         null,                                          []],
                ['single node',   n(7),                                          [7]],
                ['sample tree',   sampleTreeRoot(),                              [4, 2, 5, 1, 3]],
                ['left-skewed',   n(3, n(2, n(1), null), null),                 [1, 2, 3]],
                ['right-skewed',  n(1, null, n(2, null, n(3))),                 [1, 2, 3]],
                ['BST',           bstTreeRoot(),                                 [1, 2, 3, 4, 5, 6, 7]],
            ])
    }

    // ── preorderTraversal ───────────────────────────────────────────────────

    @Unroll
    def "#approach preorder of #description is #expected"() {
        expect:
            solution."preorderTraversal$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty',         null,                                          []],
                ['single node',   n(7),                                          [7]],
                ['sample tree',   sampleTreeRoot(),                              [1, 2, 4, 5, 3]],
                ['left-skewed',   n(3, n(2, n(1), null), null),                 [3, 2, 1]],
                ['BST',           bstTreeRoot(),                                 [4, 2, 1, 3, 6, 5, 7]],
            ])
    }

    // ── postorderTraversal ──────────────────────────────────────────────────

    @Unroll
    def "#approach postorder of #description is #expected"() {
        expect:
            solution."postorderTraversal$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty',         null,                                          []],
                ['single node',   n(7),                                          [7]],
                ['sample tree',   sampleTreeRoot(),                              [4, 5, 2, 3, 1]],
                ['left-skewed',   n(3, n(2, n(1), null), null),                 [1, 2, 3]],
                ['BST',           bstTreeRoot(),                                 [1, 3, 2, 5, 7, 6, 4]],
            ])
    }

    // ── levelOrderTraversal ─────────────────────────────────────────────────

    @Unroll
    def "#approach level-order of #description is #expected"() {
        expect:
            solution."levelOrderTraversal$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty',         null,                                          []],
                ['single node',   n(7),                                          [7]],
                ['sample tree',   sampleTreeRoot(),                              [1, 2, 3, 4, 5]],
                ['left-skewed',   n(3, n(2, n(1), null), null),                 [3, 2, 1]],
                ['BST',           bstTreeRoot(),                                 [4, 2, 6, 1, 3, 5, 7]],
            ])
    }

    @Unroll
    def "#approach level-order visits nodes level by level, not depth-first"() {
        given:
            def root = n(10, n(20, n(40), n(50)), n(30, n(60), n(70)))

        expect:
            solution."levelOrderTraversal$approach"(root) == [10, 20, 30, 40, 50, 60, 70]
            solution."inorderTraversal$approach"(root) == [40, 20, 50, 10, 60, 30, 70]

        where:
            approach << BOTH
    }

    // ── contains ────────────────────────────────────────────────────────────

    @Unroll
    def "#approach contains(#value) on sample tree is #expected"() {
        expect:
            solution."contains$approach"(sampleTreeRoot(), value) == expected

        where:
            [approach, value, expected] << duplicateForBoth([
                [1,   true],
                [4,   true],
                [5,   true],
                [99,  false],
                [0,   false],
                [-1,  false],
            ])
    }

    @Unroll
    def "#approach contains returns false on an empty tree for any value"() {
        expect:
            !solution."contains$approach"(null, 1)
            !solution."contains$approach"(null, 0)
            !solution."contains$approach"(null, -100)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach contains finds the root value in a single-node tree"() {
        expect:
            solution."contains$approach"(n(42), 42)
            !solution."contains$approach"(n(42), 41)

        where:
            approach << BOTH
    }

    // ── minValue / maxValue ─────────────────────────────────────────────────

    @Unroll
    def "#approach minValue of #description is #expected"() {
        expect:
            solution."minValue$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['single node',   n(5),                    5],
                ['sample tree',   sampleTreeRoot(),          1],
                ['BST',           bstTreeRoot(),             1],
                ['negative vals', n(0, n(-5), n(10)),        -5],
            ])
    }

    @Unroll
    def "#approach maxValue of #description is #expected"() {
        expect:
            solution."maxValue$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['single node',   n(5),                    5],
                ['sample tree',   sampleTreeRoot(),          5],
                ['BST',           bstTreeRoot(),             7],
                ['negative vals', n(0, n(-5), n(10)),        10],
            ])
    }

    @Unroll
    def "#approach minValue throws on an empty tree"() {
        when:
            solution."minValue$approach"(null)

        then:
            thrown(IllegalStateException)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach maxValue throws on an empty tree"() {
        when:
            solution."maxValue$approach"(null)

        then:
            thrown(IllegalStateException)

        where:
            approach << BOTH
    }

    // ── invert ──────────────────────────────────────────────────────────────

    @Unroll
    def "#approach invert swaps left and right children at every node"() {
        given:
            def root = invertTargetRoot()

        when:
            root = solution."invert$approach"(root)

        then:
            solution.inorderTraversalRecursive(root) == [9, 7, 6, 4, 3, 2, 1]
            solution.preorderTraversalRecursive(root) == [4, 7, 9, 6, 2, 3, 1]
            solution.levelOrderTraversalIterative(root) == [4, 7, 2, 9, 6, 3, 1]

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach invert on an empty tree is a no-op"() {
        given:
            def root = null

        when:
            root = solution."invert$approach"(root)

        then:
            solution.isEmpty(root)
            solution.inorderTraversalRecursive(root) == []

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach invert on a single-node tree is a no-op"() {
        given:
            def root = n(42)

        when:
            root = solution."invert$approach"(root)

        then:
            solution.inorderTraversalRecursive(root) == [42]
            solution.preorderTraversalRecursive(root) == [42]

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach double invert restores the original tree"() {
        given:
            def root = sampleTreeRoot()
            def originalInorder = solution.inorderTraversalRecursive(root)
            def originalPreorder = solution.preorderTraversalRecursive(root)

        when:
            root = solution."invert$approach"(root)
            root = solution."invert$approach"(root)

        then:
            solution.inorderTraversalRecursive(root) == originalInorder
            solution.preorderTraversalRecursive(root) == originalPreorder

        where:
            approach << BOTH
    }

    // ── isSymmetric ─────────────────────────────────────────────────────────

    @Unroll
    def "#approach isSymmetric for #description is #expected"() {
        expect:
            solution."isSymmetric$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty',                  null,                                              true],
                ['single node',            n(1),                                              true],
                ['perfectly symmetric',    symmetricTreeRoot(),                               true],
                ['asymmetric sample',      sampleTreeRoot(),                                  false],
                ['left-heavy',             n(1, n(2), null),                                  false],
                ['same shape diff values', n(1, n(2, n(3), n(4)), n(2, n(4), n(5))),          false],
                ['mirror shape',           n(1, n(2, null, n(3)), n(2, n(3), null)),         false],
            ])
    }

    // ── isSameTree ──────────────────────────────────────────────────────────

    @Unroll
    def "#approach isSameTree returns true for identical trees"() {
        given:
            def a = sampleTreeRoot()
            def b = sampleTreeRoot()

        expect:
            solution."isSameTree$approach"(a, b)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach isSameTree returns true for two empty trees"() {
        expect:
            solution."isSameTree$approach"(null, null)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach isSameTree returns false when values differ"() {
        given:
            def a = n(1, n(2), n(3))
            def b = n(1, n(2), n(4))

        expect:
            !solution."isSameTree$approach"(a, b)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach isSameTree returns false when structure differs"() {
        given:
            def a = n(1, n(2), null)
            def b = n(1, null, n(2))

        expect:
            !solution."isSameTree$approach"(a, b)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach isSameTree returns false when sizes differ"() {
        given:
            def a = n(1)
            def b = sampleTreeRoot()

        expect:
            !solution."isSameTree$approach"(a, b)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach isSameTree is not reflexive with null"() {
        expect:
            !solution."isSameTree$approach"(sampleTreeRoot(), null)

        where:
            approach << BOTH
    }

    // ── isBalanced ──────────────────────────────────────────────────────────

    @Unroll
    def "#approach isBalanced for #description is #expected"() {
        expect:
            solution."isBalanced$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty',              null,                                              true],
                ['single node',        n(1),                                              true],
                ['balanced sample',    sampleTreeRoot(),                                  true],
                ['balanced BST',       bstTreeRoot(),                                     true],
                ['left-skewed',        n(1, n(2, n(3, n(4), null), null), null),           false],
                ['right-skewed',       n(1, null, n(2, null, n(3, null, n(4)))),           false],
                ['imbalanced at root', n(1, n(2, n(3, n(4), null), null), n(5)),          false],
            ])
    }

    // ── diameter ────────────────────────────────────────────────────────────

    @Unroll
    def "#approach diameter of #description is #expected edges"() {
        expect:
            solution."diameter$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty',             null,                                              0],
                ['single node',       n(1),                                              0],
                ['two nodes',         n(1, n(2), null),                                  1],
                ['sample tree',       sampleTreeRoot(),                                  3],
                ['long left arm',     n(1, n(2, n(3, n(4), null), null), n(5)),          3],
                ['path through root', n(1, n(2, n(4), n(5)), n(3)),                     3],
            ])
    }

    @Unroll
    def "#approach diameter may not pass through the root"() {
        given:
            def root = n(1, n(2, n(3, n(4), n(5)), null), n(6))

        expect:
            solution."diameter$approach"(root) == 2

        where:
            approach << BOTH
    }

    // ── lowestCommonAncestor ────────────────────────────────────────────────

    @Unroll
    def "#approach LCA(#p, #q) in LCA tree is node #expectedValue"() {
        expect:
            solution."lowestCommonAncestor$approach"(lcaTreeRoot(), p, q)?.val == expectedValue

        where:
            [approach, p, q, expectedValue] << duplicateForBoth([
                [5,  1,  3],
                [5,  4,  5],
                [6,  4,  5],
                [7,  4,  2],
                [0,  8,  1],
                [3,  3,  3],
            ])
    }

    @Unroll
    def "#approach LCA returns null when a value is not in the tree"() {
        expect:
            solution."lowestCommonAncestor$approach"(lcaTreeRoot(), 5, 99) == null
            solution."lowestCommonAncestor$approach"(lcaTreeRoot(), 99, 5) == null

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach LCA on a single-node tree returns the root when both values match"() {
        expect:
            solution."lowestCommonAncestor$approach"(n(42), 42, 42)?.val == 42

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach LCA returns null on an empty tree"() {
        expect:
            solution."lowestCommonAncestor$approach"(null, 1, 2) == null

        where:
            approach << BOTH
    }

    // ── maxRootToLeafPathSum ────────────────────────────────────────────────

    @Unroll
    def "#approach maxRootToLeafPathSum for #description is #expected"() {
        expect:
            solution."maxRootToLeafPathSum$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['single node',  n(5),                                                    5],
                ['sample tree',  sampleTreeRoot(),                                        8],
                ['all negative', n(-1, n(-2, n(-5), null), n(-3)),                        -3],
                ['mixed signs',  n(1, n(2, n(4), n(5)), n(3, n(6), n(7))),                11],
            ])
    }

    @Unroll
    def "#approach maxRootToLeafPathSum throws on an empty tree"() {
        when:
            solution."maxRootToLeafPathSum$approach"(null)

        then:
            thrown(IllegalStateException)

        where:
            approach << BOTH
    }

    // ── Cross-approach consistency ──────────────────────────────────────────

    @Unroll
    def "recursive and iterative inorder agree on #description"() {
        given:
            def root = treeFactory()

        expect:
            solution.inorderTraversalRecursive(root) == solution.inorderTraversalIterative(root)

        where:
            description     | treeFactory
            'empty'           | { -> null }
            'sample tree'     | { -> sampleTreeRoot() }
            'BST'             | { -> bstTreeRoot() }
            'LCA tree'        | { -> lcaTreeRoot() }
    }

    @Unroll
    def "recursive and iterative preorder agree on #description"() {
        given:
            def root = treeFactory()

        expect:
            solution.preorderTraversalRecursive(root) == solution.preorderTraversalIterative(root)

        where:
            description     | treeFactory
            'empty'           | { -> null }
            'sample tree'     | { -> sampleTreeRoot() }
            'BST'             | { -> bstTreeRoot() }
    }

    @Unroll
    def "recursive and iterative postorder agree on #description"() {
        given:
            def root = treeFactory()

        expect:
            solution.postorderTraversalRecursive(root) == solution.postorderTraversalIterative(root)

        where:
            description     | treeFactory
            'empty'           | { -> null }
            'sample tree'     | { -> sampleTreeRoot() }
            'BST'             | { -> bstTreeRoot() }
    }

    @Unroll
    def "recursive and iterative level-order agree on #description"() {
        given:
            def root = treeFactory()

        expect:
            solution.levelOrderTraversalRecursive(root) == solution.levelOrderTraversalIterative(root)

        where:
            description     | treeFactory
            'empty'           | { -> null }
            'sample tree'     | { -> sampleTreeRoot() }
            'BST'             | { -> bstTreeRoot() }
    }

    def "all traversals visit every node exactly once (both approaches)"() {
        given:
            def root = bstTreeRoot()

        expect:
            BOTH.each { approach ->
                assert solution."inorderTraversal$approach"(root).size() == 7
                assert solution."preorderTraversal$approach"(root).size() == 7
                assert solution."postorderTraversal$approach"(root).size() == 7
                assert solution."levelOrderTraversal$approach"(root).size() == 7
            }

        and:
            def sorted = solution.inorderTraversalRecursive(root).sort()
            BOTH.each { approach ->
                assert solution."preorderTraversal$approach"(root).sort() == sorted
                assert solution."postorderTraversal$approach"(root).sort() == sorted
                assert solution."levelOrderTraversal$approach"(root).sort() == sorted
            }
    }

}
