package week3;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex5 {

    public static Node insertNode(Node node, int u, int v) {
        if (node == null) {
            return null;
        }

        if (node.getValue() == u) {
            return node;
        }

        if (node.getValue() == v) {
            ArrayList<Node> children = node.getChildren();
            children.add(new Node(u));
            node.setChildren(children);

            return node;
        }

        ArrayList<Node> children = node.getChildren();

        for (int i = 0; i < children.size(); i++) {
            children.set(i, insertNode(children.get(i), u, v));
        }

        node.setChildren(children);

        return node;
    }

    public static void preOrder(Node node) {
        if (node == null) {
            return;
        }

        ArrayList<Node> children = node.getChildren();

        if (children.isEmpty()) {
            System.out.print(node.getValue());
            System.out.print(' ');
            return;
        }

        System.out.print(node.getValue());
        System.out.print(' ');

        preOrder(children.get(0));

        for (int i = 1; i < children.size(); i++) {
            preOrder(children.get(i));
        }
    }

    public static void inOrder(Node node) {
        if (node == null) {
            return;
        }

        ArrayList<Node> children = node.getChildren();

        if (children.isEmpty()) {
            System.out.print(node.getValue());
            System.out.print(' ');
            return;
        }

        inOrder(children.get(0));

        System.out.print(node.getValue());
        System.out.print(' ');

        for (int i = 1; i < children.size(); i++) {
            inOrder(children.get(i));
        }
    }

    public static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        ArrayList<Node> children = node.getChildren();

        if (children.isEmpty()) {
            System.out.print(node.getValue());
            System.out.print(' ');
            return;
        }

        for (int i = 0; i < children.size(); i++) {
            postOrder(children.get(i));
        }

        System.out.print(node.getValue());
        System.out.print(' ');
    }

    public static class Node {
        private int value;
        private ArrayList<Node> children = new ArrayList<Node>();

        public Node(int value) {
            setValue(value);
        }

        public int getValue() {
            return value;
        }

        public ArrayList<Node> getChildren() {
            return children;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setChildren(ArrayList<Node> children) {
            this.children = children;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Node root = new Node(0);

        while (true) {
            String command = scanner.next();
            if (command.equals("*")) {
                break;
            }

            if (command.equals("MakeRoot")) {
                int u = scanner.nextInt();
                root.setValue(u);
            }

            if (command.equals("Insert")) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                root = insertNode(root, u, v);
            }

            if (command.equals("PreOrder")) {
                preOrder(root);
                System.out.println();
            }

            if (command.equals("InOrder")) {
                inOrder(root);
                System.out.println();
            }

            if (command.equals("PostOrder")) {
                postOrder(root);
                System.out.println();
            }
        }
    }
}
