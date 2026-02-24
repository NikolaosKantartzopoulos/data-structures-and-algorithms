package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject

class DesignHashMapSpec extends Specification {

    @Subject
    DesignHashMap map = new DesignHashMap()

    def "should correctly put and get elements"() {
        when: "Adding several key-value pairs"
            map.put(1, 100)
            map.put(2, 200)

        then: "Values are correctly retrieved"
            map.get(1) == 100
            map.get(2) == 200

        and: "Non-existent keys return -1"
            map.get(3) == -1
    }

    def "should update value if key already exists"() {
        given: "A map with an existing key"
            map.put(1, 100)

        when: "The same key is put with a new value"
            map.put(1, 150)

        then: "The value is updated, not duplicated"
            map.get(1) == 150
    }

    def "should correctly remove elements"() {
        given: "A map with elements"
            map.put(1, 100)
            map.put(2, 200)

        when: "A key is removed"
            map.remove(1)

        then: "The removed key returns -1"
            map.get(1) == -1

        and: "Other keys remain unaffected"
            map.get(2) == 200
    }

    def "should handle collisions correctly"() {
        given: "Two keys that result in the same hash index (key % 10000)"
            int key1 = 5
            int key2 = 10005 // 10005 % 10000 = 5

        when: "Both are added to the map"
            map.put(key1, 500)
            map.put(key2, 1000)

        then: "Both can be retrieved correctly from the same bucket"
            map.get(key1) == 500
            map.get(key2) == 1000

        when: "One colliding key is removed"
            map.remove(key1)

        then: "The first key is gone, but the second remains"
            map.get(key1) == -1
            map.get(key2) == 1000
    }

    def "should handle removal of non-existent keys gracefully"() {
        when: "Removing a key that was never added"
            map.remove(99)

        then: "No exception is thrown"
            noExceptionThrown()
            map.get(99) == -1
    }

    def "sequence of state transitions"() {
        expect: "The map follows the expected behavior of a sequence of operations"
            ops.eachWithIndex { op, i ->
                switch (op) {
                    case "put":    map.put(keys[i], vals[i]); break
                    case "remove": map.remove(keys[i]); break
                    case "get":    assert map.get(keys[i]) == expected[i]; break
                }
            }

        where:
            ops | keys | vals | expected
            ["put", "put", "get", "get", "put", "get", "remove", "get"] | [1, 2, 1, 3, 1, 1, 1, 1] | [1, 2, null, null, 10, null, null, null] | [null, null, 1, -1, null, 10, null, -1]
    }
}
