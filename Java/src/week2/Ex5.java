package week2;

import java.util.Scanner;

public class Ex5 {
    public static int n;
    public static int []arr = new int[20];
    public static boolean []mark = new boolean[100];
    public static void tryK(int k) {
        for (int i = 1; i <= n; i++) {
            if (mark[i]) {
                continue;
            }
            arr[k] = i;
            mark[i] = true;
            if (k < n - 1) {
                tryK(k + 1);
            } else {
                printSol();
            }
            mark[i] = false;
        }
    }

    public static void printSol() {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();

        for (int i = 0; i <= n; i++) {
            mark[i] = false;
        }

        tryK(0);
    }
}
