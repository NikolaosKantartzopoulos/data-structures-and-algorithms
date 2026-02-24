package com.nik.dsa.neetcode250.arrays;

public class DesignHashSet {

    private static final int ARRAY_LENGTH = 10_000;
    private final ListNode[] set;

    public DesignHashSet() {
        set = new ListNode[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            set[i] = new ListNode(0);
        }
    }

    public void add(int key) {
        ListNode cur = getFirstNode(key); // Find index of array to place the new node
        while (cur.next != null) { // Find the last entry of the Linked List
            if (cur.next.value == key) { // If key exists in set return
                return;
            }
            cur = cur.next; // Go through the list
        }
        cur.next = new ListNode(key);
    }

    public void remove(int key) {
        ListNode cur = getFirstNode(key);
        while (cur.next != null) {
            if (cur.next.value == key) {
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
    }

    public boolean contains(int key) {
        ListNode cur = getFirstNode(key).next;
        while (cur != null) {
            if (cur.value == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    private int calculateHash(int value) {
        return value % ARRAY_LENGTH;
    }

    private ListNode getFirstNode(int value) {
        return set[calculateHash((value))];
    }


    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }
}
