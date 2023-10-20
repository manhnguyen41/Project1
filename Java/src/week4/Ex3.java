package week4;

// Problem: Kiểm tra xuất hiện

import java.util.HashSet;
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        HashSet<Integer> arr = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();

            if (arr.contains(num)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            arr.add(num);
        }
    }
}
