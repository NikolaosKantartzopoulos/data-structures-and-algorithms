const solutions = require('./two-sum');

// Get all exported function names (twoSumMap, twoSumBruteForce)
const methodNames = Object.keys(solutions);

describe('Two Sum Multi-Implementation Test', () => {

    methodNames.forEach((methodName) => {
        const solve = solutions[methodName];

        describe(`Implementation: ${methodName}`, () => {
            test.each([
                [[2, 7, 11, 15], 9, [0, 1]],
                [[3, 2, 4], 6, [1, 2]],
                [[3, 3], 6, [0, 1]],
            ])('when nums is %p and target is %i, returns %p', (nums, target, expected) => {
                expect(solve(nums, target)).toEqual(expected);
            });
        });
    });
});
