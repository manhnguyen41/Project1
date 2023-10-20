package week4;

// Problem: Hash Over Strings

import java.util.Scanner;

public class Ex2 {
    public static int hash(String string, int m) {
        int result = 0;
        int length = string.length();

        for (int i = 0; i < length; i++) {
            int pow = string.charAt(i);
            for (int j = 1; j <= length - i - 1; j++) {
                pow *= 256;
                pow %= m;
            }
            result += pow;
            result %= m;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        String string = scanner.nextLine();

        for (int i = 0; i < n; i++) {
            string = scanner.nextLine();
            System.out.println(hash(string, m));
        }

    }
}
