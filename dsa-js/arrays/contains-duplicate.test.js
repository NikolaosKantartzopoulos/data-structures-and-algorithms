const solutions = require('./contains-duplicate');

describe('Contains Duplicate Multi-Implementation Test', () => {

    Object.keys(solutions).forEach((methodName) => {
        const solve = solutions[methodName];

        describe(`Implementation: ${methodName}`, () => {
            test.each([
                [[1, 2, 3, 1], true],
                [[1, 2, 3, 4], false],
                [[1, 1, 1, 3, 3, 4, 3, 2, 4, 2], true],
                [[], false],
                [[1], false],
                [[1, 2], false],
                [[1, 1], true]
            ])('when input is %p, returns %p', (nums, expected) => {
                // We pass a copy (...nums) to hasDuplicateSort 
                // because .sort() modifies the original array in-place.
                expect(solve([...nums])).toBe(expected);
            });
        });
    });
});
