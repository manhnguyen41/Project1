package week3;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex7 {
    public static Node insertNode(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value > node.getValue()) {
            node.setRightNode(insertNode(node.getRightNode(), value));
        }
        if (value < node.getValue()) {
            node.setLeftNode(insertNode(node.getLeftNode(), value));
        }

        return node;
    }

    public static void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getValue());
        System.out.print(" ");

        preOrder(node.getLeftNode());

        preOrder(node.getRightNode());
    }

    public static class Node {
        private int value;
        private Node leftNode;
        private Node rightNode;

        public Node(int value) {
            this.value = value;
            leftNode = null;
            rightNode = null;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node root = null;

        while (true) {
            String command = scanner.next();

            if (command.equals("#")) {
                break;
            }

            int value = scanner.nextInt();
            root = insertNode(root, value);
        }

        preOrder(root);
    }
}
