package week3;

import java.util.LinkedList;
import java.util.Scanner;

public class Ex3 {
    public static boolean checkParenthesis(String string) {
        int length = string.length();
        LinkedList<Character> stack = new LinkedList<Character>();

        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.addLast(c);
            } else {
                try {
                    char c1 = stack.getLast();
                    if ((c1 == '(' && c == ')') || (c1 == '[' && c == ']') || (c1 == '{' && c == '}')) {
                        stack.removeLast();
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        System.out.println(checkParenthesis(string) ? 1 : 0);
    }

}
