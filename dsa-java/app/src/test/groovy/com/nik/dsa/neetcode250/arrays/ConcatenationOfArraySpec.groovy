package com.nik.dsa.neetcode250.arrays

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ConcatenationOfArraySpec extends Specification {

    @Subject
    ConcatenationOfArray solution = new ConcatenationOfArray()

    @Unroll
    def "Implementation #methodName should correctly concatenate #nums"() {
        given:
            int[] input = nums as int[]

        when:
            int[] result = solution."$methodName"(input)

        then:
            result == expected as int[]

        where:
            methodName                   | nums      | expected
            "getConcatenationTwoPass"    | [1, 2, 1] | [1, 2, 1, 1, 2, 1]
            "getConcatenationOnePass"    | [1, 2, 1] | [1, 2, 1, 1, 2, 1]
            "getConcatenationSystemCopy" | [1, 2, 1] | [1, 2, 1, 1, 2, 1]
            "getConcatenationOnePass"    | [4, 5]    | [4, 5, 4, 5]
            "getConcatenationTwoPass"    | [4, 5]    | [4, 5, 4, 5]
    }
}
