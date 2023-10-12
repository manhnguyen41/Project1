package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> stack = new LinkedList<Integer>();
        ArrayList<String> commands = new ArrayList<String>();

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("#")) {
                break;
            }
            commands.add(command);
        }

        for (String command: commands) {
            if (command.equals("POP")) {
                try {
                    int out = stack.getFirst();
                    stack.removeFirst();
                    System.out.println(out);
                } catch (Exception e) {
                    System.out.println("NULL");
                }
            } else {
                stack.addLast(Integer.parseInt(command.substring(5)));
            }
        }
    }

}
