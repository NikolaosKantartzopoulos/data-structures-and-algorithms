const solutions = require('./longest-common-prefix');

describe('Longest Common Prefix Multi-Implementation Test', () => {

    Object.keys(solutions).forEach((methodName) => {
        const solve = solutions[methodName];

        describe(`Implementation: ${methodName}`, () => {
            test.each([
                [["flower", "flow", "flight"], "fl"],
                [["dog", "racecar", "car"], ""],
                [["bat", "ba"], "ba"],
                [["a"], "a"],
                [["", "b"], ""],
                [[], ""]
            ])('given strs=%p, returns "%s"', (strs, expected) => {
                expect(solve(strs)).toBe(expected);
            });
        });
    });
});
