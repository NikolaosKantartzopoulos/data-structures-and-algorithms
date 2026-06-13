package com.nik.dsa.basicstructures.binarytree

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * Stage 3 — AVL Tree tests.
 * Implement {@link AvlTree} until all tests pass.
 */
class AvlTreeSpec extends Specification {

    @Subject
    AvlTree avl = new AvlTree()

    private static final List<String> BOTH = ['Recursive', 'Iterative']

    private static AvlTreeNode insertAll(AvlTree avl, String approach, List values) {
        def root = null
        values.each { root = avl."insert$approach"(root, it) }
        return root
    }

    private static List duplicateForBoth(List rows) {
        rows.collectMany { row -> BOTH.collect { approach -> [approach] + row } }
    }

    // ── isEmpty ─────────────────────────────────────────────────────────────

    def "isEmpty returns true for a new tree"() {
        expect:
            avl.isEmpty(null)
    }

    // ── insert ──────────────────────────────────────────────────────────────

    @Unroll
    def "#approach insert keeps inorder sorted"() {
        when:
            def root = insertAll(avl, approach, [4, 2, 6, 1, 3, 5, 7])

        then:
            avl."inorderTraversal$approach"(root) == [1, 2, 3, 4, 5, 6, 7]
            avl."isAvlBalanced$approach"(root)
            avl."size$approach"(root) == 7

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert of sorted sequence stays balanced (not skewed)"() {
        when:
            def root = insertAll(avl, approach, [1, 2, 3, 4, 5])

        then:
            avl."inorderTraversal$approach"(root) == [1, 2, 3, 4, 5]
            avl."height$approach"(root) == 3
            avl."isAvlBalanced$approach"(root)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert of reverse-sorted sequence stays balanced"() {
        when:
            def root = insertAll(avl, approach, [5, 4, 3, 2, 1])

        then:
            avl."inorderTraversal$approach"(root) == [1, 2, 3, 4, 5]
            avl."height$approach"(root) == 3
            avl."isAvlBalanced$approach"(root)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert triggers left rotation on right-skew insert"() {
        when:
            def root = insertAll(avl, approach, [10, 20, 30])

        then:
            avl."inorderTraversal$approach"(root) == [10, 20, 30]
            avl."height$approach"(root) == 2
            avl."isAvlBalanced$approach"(root)
            root.val == 20

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert triggers right rotation on left-skew insert"() {
        when:
            def root = insertAll(avl, approach, [30, 20, 10])

        then:
            avl."inorderTraversal$approach"(root) == [10, 20, 30]
            avl."height$approach"(root) == 2
            avl."isAvlBalanced$approach"(root)
            root.val == 20

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert triggers left-right double rotation"() {
        when:
            def root = insertAll(avl, approach, [30, 10, 20])

        then:
            avl."inorderTraversal$approach"(root) == [10, 20, 30]
            avl."isAvlBalanced$approach"(root)
            root.val == 20

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert triggers right-left double rotation"() {
        when:
            def root = insertAll(avl, approach, [10, 30, 20])

        then:
            avl."inorderTraversal$approach"(root) == [10, 20, 30]
            avl."isAvlBalanced$approach"(root)
            root.val == 20

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach insert does not add duplicates"() {
        when:
            def root = insertAll(avl, approach, [5, 3, 7, 3, 5])

        then:
            avl."inorderTraversal$approach"(root) == [3, 5, 7]
            avl."size$approach"(root) == 3

        where:
            approach << BOTH
    }

    // ── contains ────────────────────────────────────────────────────────────

    @Unroll
    def "#approach contains finds inserted values"() {
        given:
            def root = insertAll(avl, approach, [4, 2, 6, 1, 3, 5, 7])

        expect:
            avl."contains$approach"(root, 4)
            avl."contains$approach"(root, 1)
            avl."contains$approach"(root, 7)
            !avl."contains$approach"(root, 99)

        where:
            approach << BOTH
    }

    // ── delete ──────────────────────────────────────────────────────────────

    @Unroll
    def "#approach delete leaf and rebalances"() {
        given:
            def root = insertAll(avl, approach, [4, 2, 6, 1, 3, 5, 7])

        when:
            assert avl."contains$approach"(root, 1)
            root = avl."delete$approach"(root, 1)

        then:
            avl."inorderTraversal$approach"(root) == [2, 3, 4, 5, 6, 7]
            avl."isAvlBalanced$approach"(root)
            !avl."contains$approach"(root, 1)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach delete node with two children and rebalances"() {
        given:
            def root = insertAll(avl, approach, [4, 2, 6, 1, 3, 5, 7])

        when:
            assert avl."contains$approach"(root, 4)
            root = avl."delete$approach"(root, 4)

        then:
            avl."inorderTraversal$approach"(root) == [1, 2, 3, 5, 6, 7]
            avl."isAvlBalanced$approach"(root)
            !avl."contains$approach"(root, 4)

        where:
            approach << BOTH
    }

    @Unroll
    def "#approach delete absent value leaves tree unchanged"() {
        given:
            def root = insertAll(avl, approach, [1, 2, 3])

        when:
            def result = avl."delete$approach"(root, 99)

        then:
            avl."inorderTraversal$approach"(result) == [1, 2, 3]
            avl."isAvlBalanced$approach"(result)

        where:
            approach << BOTH
    }

    // ── isAvlBalanced ───────────────────────────────────────────────────────

    @Unroll
    def "#approach isAvlBalanced stays true after large insert sequence"() {
        when:
            def root = insertAll(avl, approach, (1..31).toList())

        then:
            avl."size$approach"(root) == 31
            avl."isAvlBalanced$approach"(root)
            avl."height$approach"(root) <= 6

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
                recRoot = avl.insertRecursive(recRoot, it)
                iterRoot = avl.insertIterative(iterRoot, it)
            }

        expect:
            avl.inorderTraversalRecursive(recRoot) == avl.inorderTraversalIterative(iterRoot)
            avl.isAvlBalancedRecursive(recRoot)
            avl.isAvlBalancedIterative(iterRoot)

        where:
            values << [[1, 2, 3, 4, 5], [5, 4, 3, 2, 1], [10, 20, 30, 5, 15]]
    }

}
