const solutions = require('./valid-anagram');

describe('Valid Anagram Multi-Implementation Test', () => {

    Object.keys(solutions).forEach((methodName) => {
        const solve = solutions[methodName];

        describe(`Implementation: ${methodName}`, () => {
            test.each([
                ["anagram", "nagaram", true],
                ["rat", "car", false],
                ["a", "ab", false],
                ["", "", true],
                ["racecar", "carrace", true],
                ["jar", "jam", false]
            ])('given s="%s" and t="%s", returns %p', (s, t, expected) => {
                expect(solve(s, t)).toBe(expected);
            });
        });
    });
});
