/**
 * <h2>Longest Common Prefix</h2>
 * @see {@link https://neetcode.io/solutions/longest-common-prefix}
 */

/**
 * <h3>Vertical Scanning Approach</h3>
 * <p>Iterates through characters of the first string and compares them
 * with the same index in all other strings.</p>
 * <ul>
 * <li><b>Time Complexity:</b> O(S) where S is the sum of all characters</li>
 * <li><b>Space Complexity:</b> O(1)</li>
 * </ul>
 */
function longestCommonPrefix(strs) {
    if (!strs || strs.length === 0) return "";

    for (let i = 0; i < strs[0].length; i++) {
        const char = strs[0][i];
        for (let str of strs) {
            // If the current string is shorter than i or characters don't match
            if (i === str.length || str[i] !== char) {
                return strs[0].slice(0, i);
            }
        }
    }
    return strs[0];
}

module.exports = {
    longestCommonPrefix
};
