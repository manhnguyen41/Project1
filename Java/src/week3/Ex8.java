package week3;

import java.util.Scanner;

public class Ex8 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    public static Node addLast(Node head, int value) {
        if (head == null) {
            return new Node(value, null);
        }

        Node node = head;

        while (true) {
            if (node == null) {
                break;
            }
            if (node.getValue() == value) {
                return head;
            }
            node = node.getNext();
        }

        head.setNext(addLast(head.getNext(), value));

        return head;
    }

    public static Node addFirst(Node head, int value) {
        Node node = head;

        while (true) {
            if (node == null) {
                break;
            }
            if (node.getValue() == value) {
                return head;
            }
            node = node.getNext();
        }
        return new Node(value, head);
    }

    public static Node addAfter(Node head, int u, int v) {
        Node node = head;

        if (head == null) {
            return null;
        }

        while (true) {
            if (node == null) {
                break;
            }
            if (node.getValue() == u) {
                return head;
            }
            node = node.getNext();
        }

        node = head;

        while (true) {
            if (node == null) {
                return head;
            }
            if (node.getValue() == v) {
                break;
            }
            node = node.getNext();
        }

        Node newNode = new Node(u, node.getNext());
        node.setNext(newNode);

        return head;
    }

    public static Node addBefore(Node head, int u, int v) {
        Node node = head;

        if (head == null) {
            return null;
        }

        while (true) {
            if (node == null) {
                break;
            }
            if (node.getValue() == u) {
                return head;
            }
            node = node.getNext();
        }

        node = head;

        if (head.getValue() == v) {
            return new Node(u, node);
        }

        while (true) {
            if (node.getNext() == null) {
                return head;
            }
            if (node.getNext().getValue() == v) {
                break;
            }
            node = node.getNext();
        }

        Node newNode = new Node(u, node.getNext());
        node.setNext(newNode);

        return head;
    }

    public static Node remove(Node head, int value) {
        if (head == null) {
            return head;
        }

        if (head.getValue() == value) {
            return head.getNext();
        }

        Node node = head;

        while (true) {
            if (node.getNext() == null) {
                return head;
            }

            if (node.getNext().getValue() == value) {
                break;
            }

            node = node.getNext();
        }

        node.setNext(node.getNext().getNext());

        return head;
    }

    public static Node reverse(Node head) {
        Node node1 = head;

        if (node1.getNext() == null) {
            return node1;
        }

        Node node2 = node1.getNext();

        if (node2.getNext() == null) {
            node2.setNext(node1);
            node1.setNext(null);
            return node2;
        }

        Node node3 = node2.getNext();
        node1.setNext(null);

        while (true) {
            if (node2.getNext() == null) {
                node2.setNext(node1);
                return node2;
            }

            node2.setNext(node1);
            node1 = node2;
            node2 = node3;
            node3 = node3.getNext();
        }
    }

    public static void printSol(Node head) {
        Node node = head;

        while (true) {
            if (node == null) {
                break;
            }
            System.out.print(node.getValue());
            System.out.print(' ');
            node = node.getNext();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Node head = null;

        int n = scanner.nextInt();

        int []arr = new int[1000];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            head = addLast(head, arr[i]);
        }

        while (true) {
            String command = scanner.next();

            if (command.equals("#")) {
                break;
            }

            if (command.equals("addlast")) {
                int k = scanner.nextInt();
                head = addLast(head, k);
            }

            if (command.equals("addfirst")) {
                int k = scanner.nextInt();
                head = addFirst(head, k);
            }

            if (command.equals("addafter")) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                head = addAfter(head, u, v);
            }

            if (command.equals("addbefore")) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                head = addBefore(head, u, v);
            }

            if (command.equals("remove")) {
                int k = scanner.nextInt();
                head = remove(head, k);
            }

            if (command.equals("reverse")) {
                head = reverse(head);
            }
        }

        printSol(head);
    }
}
