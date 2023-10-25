package week4;

// Problem: Kiểm tra xuất hiện

import java.util.HashSet;
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        HashSet<Integer> arr = new HashSet<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();

            if (arr.contains(num)) {
                output.append("1\n");
            } else {
                output.append("0\n");
                arr.add(num);
            }
        }

        System.out.print(output.toString());
    }
}
