package com.nik.dsa.basicstructures.binarytree

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * Stage 4 — Red-Black Tree tests.
 * Implement {@link RedBlackTree} until all tests pass.
 */
class RedBlackTreeSpec extends Specification {

    @Subject
    RedBlackTree rb = new RedBlackTree()

    private static final List<String> BOTH = ['Recursive', 'Iterative']

    private static RbTreeNode insertAll(RedBlackTree rb, String approach, List values) {
        def root = null
        values.each { root = rb."insert$approach"(root, it) }
        return root
    }

    private static List duplicateForBoth(List rows) {
        rows.collectMany { row -> BOTH.collect { approach -> [approach] + row } }
    }

    // ── isEmpty ─────────────────────────────────────────────────────────────

    def "isEmpty returns true for a new tree"() {
        expect:
            rb.isEmpty(null)
    }

    // ── insert ──────────────────────────────────────────────────────────────

    @Unroll
    def "#approach insert keeps inorder sorted"() {
        when:
            def root = insertAll(rb, approach, [4, 2, 6, 1, 3, 5, 7])

        then:
            rb."inorderTraversal$approach"(root) == [1, 2, 3, 4, 5, 6, 7]
            rb."isValidRedBlack$approach"(root)
            rb.sizeRecursive(root) == 7

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert of sorted sequence stays valid red-black tree"() {
        when:
            def root = insertAll(rb, approach, [1, 2, 3, 4, 5])

        then:
            rb."inorderTraversal$approach"(root) == [1, 2, 3, 4, 5]
            rb."isValidRedBlack$approach"(root)
            rb.heightRecursive(root) <= 4

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert of reverse-sorted sequence stays valid red-black tree"() {
        when:
            def root = insertAll(rb, approach, [5, 4, 3, 2, 1])

        then:
            rb."inorderTraversal$approach"(root) == [1, 2, 3, 4, 5]
            rb."isValidRedBlack$approach"(root)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach root is always black after insert"() {
        when:
            def root = insertAll(rb, approach, [10, 5, 15, 3, 7])

        then:
            root.color == RbTreeNode.Color.BLACK
            rb."isValidRedBlack$approach"(root)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert does not create consecutive red nodes"() {
        when:
            def root = insertAll(rb, approach, [10, 5, 15, 3, 7, 12, 18])

        then:
            rb."isValidRedBlack$approach"(root)
            !hasConsecutiveRedNodes(root)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert does not add duplicates"() {
        when:
            def root = insertAll(rb, approach, [5, 3, 7, 3, 5])

        then:
            rb."inorderTraversal$approach"(root) == [3, 5, 7]
            rb.sizeRecursive(root) == 3

        where:
            approach << BOTH
    }

    // ── contains ────────────────────────────────────────────────────────────

    @Unroll
    def "#approach contains finds inserted values"() {
        given:
            def root = insertAll(rb, approach, [4, 2, 6, 1, 3, 5, 7])

        expect:
            rb."contains$approach"(root, 4)
            rb."contains$approach"(root, 1)
            rb."contains$approach"(root, 7)
            !rb."contains$approach"(root, 99)

        where:
            approach << BOTH
    }

    // ── delete ──────────────────────────────────────────────────────────────

    @Unroll
    def "#approach delete leaf and maintains red-black properties"() {
        given:
            def root = insertAll(rb, approach, [4, 2, 6, 1, 3, 5, 7])

        when:
            assert rb."contains$approach"(root, 1)
            root = rb."delete$approach"(root, 1)

        then:
            rb."inorderTraversal$approach"(root) == [2, 3, 4, 5, 6, 7]
            rb."isValidRedBlack$approach"(root)
            !rb."contains$approach"(root, 1)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach delete node with two children and maintains properties"() {
        given:
            def root = insertAll(rb, approach, [4, 2, 6, 1, 3, 5, 7])

        when:
            assert rb."contains$approach"(root, 4)
            root = rb."delete$approach"(root, 4)

        then:
            rb."inorderTraversal$approach"(root) == [1, 2, 3, 5, 6, 7]
            rb."isValidRedBlack$approach"(root)
            !rb."contains$approach"(root, 4)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach delete absent value leaves tree unchanged"() {
        given:
            def root = insertAll(rb, approach, [1, 2, 3])

        when:
            def result = rb."delete$approach"(root, 99)

        then:
            rb."inorderTraversal$approach"(result) == [1, 2, 3]
            rb."isValidRedBlack$approach"(result)

        where:
            approach << BOTH
    }

    // ── isValidRedBlack ─────────────────────────────────────────────────────

    @Unroll
    def "#approach isValidRedBlack stays true after large insert sequence"() {
        when:
            def root = insertAll(rb, approach, (1..31).toList())

        then:
            rb.sizeRecursive(root) == 31
            rb."isValidRedBlack$approach"(root)
            root.color == RbTreeNode.Color.BLACK

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
                recRoot = rb.insertRecursive(recRoot, it)
                iterRoot = rb.insertIterative(iterRoot, it)
            }

        expect:
            rb.inorderTraversalRecursive(recRoot) == rb.inorderTraversalIterative(iterRoot)
            rb.isValidRedBlackRecursive(recRoot)
            rb.isValidRedBlackIterative(iterRoot)

        where:
            values << [[1, 2, 3, 4, 5], [5, 4, 3, 2, 1], [10, 5, 15, 3, 7, 12]]
    }

    // ── helpers ─────────────────────────────────────────────────────────────

    private static boolean hasConsecutiveRedNodes(RbTreeNode node) {
        if (node == null) return false
        if (node.color == RbTreeNode.Color.RED) {
            if ((node.left?.color == RbTreeNode.Color.RED) ||
                (node.right?.color == RbTreeNode.Color.RED)) {
                return true
            }
        }
        return hasConsecutiveRedNodes(node.left) || hasConsecutiveRedNodes(node.right)
    }

}
