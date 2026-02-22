/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
function validAnagramSort(s, t) {
    if (s.length !== t.length) {
        return false;
    }

    let sSort = s.split('').sort().join('');
    let tSort = t.split('').sort().join('');
    return sSort == tSort;
}

/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
function validAnagramHashMap(s, t) {
    if (s.length !== t.length) {
        return false;
    }

    const map = new Map();

    for (let i = 0; i < s.length; i++) {
        let sChar = s[i];
        let tChar = t[i];

        map.set(sChar, (map.get(sChar) || 0) + 1);
        map.set(tChar, (map.get(tChar) || 0) - 1);
    }

    for (let i of map.values()) {
        if (i !== 0) return false;
    }
    return true;
}

module.exports = {
    validAnagramSort,
    validAnagramHashMap
};
