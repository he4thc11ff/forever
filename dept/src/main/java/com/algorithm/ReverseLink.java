package com.algorithm;

public class ReverseLink {
    public static void main(String[] args) {
        class Node {
            int value;
            Node next = null;

            public Node(int value) {
                this.value = value;
            }
        }

        Node head = new Node(-1);
        Node temp = head;
        for (int i = 4; i <= 7; i++) {
            Node node = new Node(i);
            temp.next = node;
            temp = temp.next;
        }

        Node last = head;
        Node cur = last.next;
        while (cur != null) {
            last.next = cur.next;
            cur.next = head;
            head = cur;
            cur = last.next;
        }

        temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }
}
