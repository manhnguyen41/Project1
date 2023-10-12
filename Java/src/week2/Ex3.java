package week2;

import java.util.Scanner;

public class Ex3 {
    public static int n;
    public static int []arr = new int[20];
    public static void tryBinary(int k) {
        for (int i = 0; i <= 1; i++) {
            arr[k] = i;
            if (k < n - 1) {
                tryBinary(k + 1);
            } else {
                printSol();
            }
        }
    }

    public static void printSol() {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();

        tryBinary(0);
    }
}
