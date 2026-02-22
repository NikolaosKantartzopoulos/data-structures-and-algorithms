package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class RemoveElementSpec extends Specification {

    @Subject
    RemoveElement solution = new RemoveElement()

    @Unroll
    def "removeElement: when nums is #nums and val is #val, then k should be #expectedK"() {
        given: "a primitive array"
            int[] inputArray = nums as int[]

        when: "the method is executed"
            int k = solution.removeElement(inputArray, val)

        then: "the count of remaining elements is correct"
            k == expectedK

        and: "the first k elements match the expected elements (ignoring order)"
            if (k > 0) {
                inputArray[0..k-1].toList().sort() == expectedElements.sort()
            }

        where:
            nums                     | val | expectedK | expectedElements
            [3, 2, 2, 3]             | 3   | 2         | [2, 2]
            [0, 1, 2, 2, 3, 0, 4, 2] | 2   | 5         | [0, 1, 3, 0, 4]
            [1]                      | 1   | 0         | []
            [1]                      | 2   | 1         | [1]
            [3, 3, 3]                | 3   | 0         | []
            []                       | 5   | 0         | []
    }
}
