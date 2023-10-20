package week4;

// Problem: Sum pair of sequence equal to a number

import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int []count = new int[1000000];

        int result = 0;

        for (int i = 1; i < 1000000; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            result += count[m - num];
            count[num]++;
        }

        System.out.println(result);
    }
}
