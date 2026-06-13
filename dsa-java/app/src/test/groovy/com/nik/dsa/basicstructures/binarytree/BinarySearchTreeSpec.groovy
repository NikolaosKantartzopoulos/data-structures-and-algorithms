package com.nik.dsa.basicstructures.binarytree

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * Stage 2 — Binary Search Tree tests.
 * Implement {@link BinarySearchTree} until all tests pass.
 */
class BinarySearchTreeSpec extends Specification {

    @Subject
    BinarySearchTree bst = new BinarySearchTree()

    private static final List<String> BOTH = ['Recursive', 'Iterative']

    private static TreeNode n(int val, TreeNode left = null, TreeNode right = null) {
        new TreeNode(val, left, right)
    }

    private static TreeNode validBstRoot() {
        n(4, n(2, n(1), n(3)), n(6, n(5), n(7)))
    }

    private static TreeNode insertAll(BinarySearchTree bst, String approach, List values) {
        def root = null
        values.each { root = bst."insert$approach"(root, it) }
        return root
    }

    private static List duplicateForBoth(List rows) {
        rows.collectMany { row -> BOTH.collect { approach -> [approach] + row } }
    }

    // ── isEmpty ─────────────────────────────────────────────────────────────

    def "isEmpty returns true for a new tree"() {
        expect:
            bst.isEmpty(null)
    }

    def "isEmpty returns false when built from a valid BST root"() {
        expect:
            !bst.isEmpty(validBstRoot())
    }

    // ── insert ────────────────────────────────────────────────────────────────

    @Unroll
    def "#approach insert builds a valid BST from an empty tree"() {
        when:
            def root = insertAll(bst, approach, [4, 2, 6, 1, 3, 5, 7])

        then:
            bst."inorderTraversal$approach"(root) == [1, 2, 3, 4, 5, 6, 7]
            bst."isValid$approach"(root)
            bst."size$approach"(root) == 7

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert does not add duplicate values"() {
        when:
            def root = insertAll(bst, approach, [5, 3, 7, 3, 5])

        then:
            bst."inorderTraversal$approach"(root) == [3, 5, 7]
            bst."size$approach"(root) == 3

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert handles negative values"() {
        when:
            def root = insertAll(bst, approach, [0, -3, 5, -1, 2])

        then:
            bst."inorderTraversal$approach"(root) == [-3, -1, 0, 2, 5]
            bst."isValid$approach"(root)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach sorted ascending insert creates a right-skewed BST"() {
        when:
            def root = insertAll(bst, approach, [1, 2, 3, 4, 5])

        then:
            bst."inorderTraversal$approach"(root) == [1, 2, 3, 4, 5]
            bst."isValid$approach"(root)
            bst."height$approach"(root) == 5

        where:
            approach << BOTH
    }

    // ── contains ────────────────────────────────────────────────────────────

    @Unroll
    def "#approach contains(#value) on valid BST is #expected"() {
        expect:
            bst."contains$approach"(validBstRoot(), value) == expected

        where:
            [approach, value, expected] << duplicateForBoth([
                [4, true], [1, true], [7, true], [99, false], [0, false]
            ])
    }

    @Unroll
    def "#approach contains returns false on empty tree"() {
        expect:
            !bst."contains$approach"(null, 5)

        where:
            approach << BOTH
    }

    // ── min / max ───────────────────────────────────────────────────────────

    @Unroll
    def "#approach minValue of valid BST is 1"() {
        expect:
            bst."minValue$approach"(validBstRoot()) == 1

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach maxValue of valid BST is 7"() {
        expect:
            bst."maxValue$approach"(validBstRoot()) == 7

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach minValue throws on empty tree"() {
        when:
            bst."minValue$approach"(null)

        then:
            thrown(IllegalStateException)

        where:
            approach << BOTH
    }

    // ── delete ──────────────────────────────────────────────────────────────

    @Unroll
    def "#approach delete removes a leaf node"() {
        given:
            def root = insertAll(bst, approach, [4, 2, 6, 1, 3, 5, 7])

        when:
            assert bst."contains$approach"(root, 1)
            root = bst."delete$approach"(root, 1)

        then:
            bst."inorderTraversal$approach"(root) == [2, 3, 4, 5, 6, 7]
            bst."isValid$approach"(root)
            !bst."contains$approach"(root, 1)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach delete removes a node with one child"() {
        given:
            def root = insertAll(bst, approach, [5, 2, 8, 1, 3])
            root = bst."delete$approach"(root, 1)

        when:
            assert bst."contains$approach"(root, 2)
            root = bst."delete$approach"(root, 2)

        then:
            bst."inorderTraversal$approach"(root) == [3, 5, 8]
            bst."isValid$approach"(root)
            !bst."contains$approach"(root, 2)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach delete removes a node with two children"() {
        given:
            def root = insertAll(bst, approach, [4, 2, 6, 1, 3, 5, 7])

        when:
            assert bst."contains$approach"(root, 4)
            root = bst."delete$approach"(root, 4)

        then:
            bst."inorderTraversal$approach"(root) == [1, 2, 3, 5, 6, 7]
            bst."isValid$approach"(root)
            !bst."contains$approach"(root, 4)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach delete leaves tree unchanged when value is absent"() {
        given:
            def root = validBstRoot()

        when:
            def result = bst."delete$approach"(root, 99)

        then:
            bst."inorderTraversal$approach"(result) == [1, 2, 3, 4, 5, 6, 7]
            bst."isValid$approach"(result)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach delete on empty tree returns null"() {
        expect:
            bst."delete$approach"(null, 1) == null

        where:
            approach << BOTH
    }

    // ── isValid ─────────────────────────────────────────────────────────────

    @Unroll
    def "#approach isValid for #description is #expected"() {
        expect:
            bst."isValid$approach"(root) == expected

        where:
            [approach, description, root, expected] << duplicateForBoth([
                ['empty',                   null,                                           true],
                ['valid BST',               validBstRoot(),                                 true],
                ['invalid: right < root',   n(5, n(1), n(4)),                               false],
                ['invalid: deep violation', n(5, n(1), n(4, n(3), n(6))),                  false],
                ['invalid: equal left',     n(5, n(5), n(7)),                               false],
                ['valid with negatives',    n(0, n(-1), n(1)),                              true],
            ])
    }

    // ── size / height ───────────────────────────────────────────────────────

    @Unroll
    def "#approach size of valid BST is 7"() {
        expect:
            bst."size$approach"(validBstRoot()) == 7

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach height of valid BST is 3"() {
        expect:
            bst."height$approach"(validBstRoot()) == 3

        where:
            approach << BOTH
    }

    // ── cross-approach ──────────────────────────────────────────────────────

    @Unroll
    def "recursive and iterative inorder agree after inserts: #values"() {
        given:
            def recRoot = null
            def iterRoot = null
            values.each {
                recRoot = bst.insertRecursive(recRoot, it)
                iterRoot = bst.insertIterative(iterRoot, it)
            }

        expect:
            bst.inorderTraversalRecursive(recRoot) == bst.inorderTraversalIterative(iterRoot)

        where:
            values << [[4, 2, 6, 1, 3, 5, 7], [1, 2, 3, 4, 5], [5, 3, 7, 1, 9]]
    }

}
