package week4;

// Problem: Store & Search String

import java.util.HashSet;
import java.util.Scanner;

public class Ex1 {

    // Function to find a string
    public static int find(String string, HashSet<String> stringHashSet) {
        if (stringHashSet.contains(string)) {
            return 1;
        }
        return 0;
    }

    // Function to insert a string

    public static int insert(String string, HashSet<String> stringHashSet) {
        // Check if string is exist
        int isExist = find(string, stringHashSet);
        if (isExist == 1) {
            return 0; // If string is exist, return 0
        }
        // Else insert string and return 1
        stringHashSet.add(string);
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> stringHashSet = new HashSet<String>();

        while (true) {
            String string = scanner.nextLine();
            if (string.equals("*")) {
                break;
            }
            stringHashSet.add(string);
        }

        while (true) {
            String command = scanner.next();
            if (command.equals("***")) {
                break;
            }

            String string = scanner.next();
            if (command.equals("find")) {
                System.out.println(find(string, stringHashSet));
            }
            if (command.equals("insert")) {
                System.out.println(insert(string, stringHashSet));
            }
        }
    }
}