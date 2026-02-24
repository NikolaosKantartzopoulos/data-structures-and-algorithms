package com.nik.dsa.neetcode250.arrays;

import java.util.ArrayList;

// https://neetcode.io/problems/design-hashmap/
public class DesignHashMap {

    private static final int ARRAY_LENGTH = 10_000;
    private final ListNode[] set;

    public DesignHashMap(){
        set = new ListNode[ARRAY_LENGTH]; // Initialize array with that many elements
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            set[i] = new ListNode(); // Fill each element with a dummy node
        }
    }

    public void put(int key, int value) {
        ListNode cur = getDummyNode(key);
        while (cur.next != null) {
            if (cur.next.key == key) {
                cur.next.val = value;
                return;
            }
            cur = cur.next;
        }
        cur.next = new ListNode(key, value, null);
    }

    public int get(int key) {
        ListNode cur = getDummyNode(key).next;
        while (cur != null) {
            if (cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }

    public void remove(int key) {
        ListNode cur = getDummyNode(key);
        while (cur.next != null) {
            if (cur.next.key == key) {
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
    }

    private int getHashValue(int key) {
        return key % ARRAY_LENGTH;
    }

    private ListNode getDummyNode(int key) {
        return set[getHashValue(key)];
    }

    private class ListNode {
        int key;
        int val;
        ListNode next;

        public ListNode(int key, int val, ListNode next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public ListNode() {
            this(-1, -1, null);
        }
    }
}
