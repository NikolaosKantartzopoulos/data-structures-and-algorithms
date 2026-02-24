package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject

class DesignHashSetSpec extends Specification {

    @Subject
    DesignHashSet hashSet = new DesignHashSet()

    def "should correctly add and check for elements"() {
        expect: "Initially empty"
            !hashSet.contains(1)

        when: "Adding elements"
            hashSet.add(1)
            hashSet.add(2)

        then: "Elements are present"
            hashSet.contains(1)
            hashSet.contains(2)
            !hashSet.contains(3)
    }

    def "should handle duplicates by doing nothing"() {
        when: "Adding the same key multiple times"
            hashSet.add(5)
            hashSet.add(5)
            hashSet.add(5)

        then: "It still contains the key"
            hashSet.contains(5)

        when: "Removing the key once"
            hashSet.remove(5)

        then: "The key is completely gone (standard Set behavior)"
            !hashSet.contains(5)
    }

    def "should correctly remove existing elements"() {
        given: "A set with an element"
            hashSet.add(10)
            assert hashSet.contains(10)

        when: "The element is removed"
            hashSet.remove(10)

        then: "It no longer exists"
            !hashSet.contains(10)
    }

    def "should not crash when removing non-existent elements"() {
        when:
            hashSet.remove(99)

        then:
            noExceptionThrown()
            !hashSet.contains(99)
    }

    def "should handle collisions (keys with same hash)"() {
        given: "Two keys that result in the same index (mod 10000)"
            int key1 = 1
            int key2 = 10001 // 10001 % 10000 = 1

        when: "Both are added"
            hashSet.add(key1)
            hashSet.add(key2)

        then: "Both exist despite being in the same bucket"
            hashSet.contains(key1)
            hashSet.contains(key2)

        when: "Removing one of the colliding keys"
            hashSet.remove(key1)

        then: "The other key remains intact"
            !hashSet.contains(key1)
            hashSet.contains(key2)
    }

    def "extensive state transitions sequence"() {
        expect:
            operations.eachWithIndex { op, i ->
                switch (op) {
                    case "add":      hashSet.add(values[i]); break
                    case "remove":   hashSet.remove(values[i]); break
                    case "contains": assert hashSet.contains(values[i]) == expected[i]; break
                }
            }

        where:
            operations | values | expected
            ["add", "add", "contains", "contains", "add", "contains", "remove", "contains"] | [1, 2, 1, 3, 2, 2, 2, 2] | [null, null, true, false, null, true, null, false]
    }
}
