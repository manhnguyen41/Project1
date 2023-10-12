package week3;

import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int a1 = 0, b1 = 0, step = 0;

        while (true) {
            a1 = a - (b - b1) % a;
            step += ((b - b1) / a + 1) * 2;
            if (a1 == c) {
                break;
            }
            b1 = a1;
            step += 2;
        }

        System.out.println(step);
    }
}
