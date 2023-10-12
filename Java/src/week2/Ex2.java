import com.microsoft.sqlserver.jdbc.SQLServerResource_zh_CN;

import java.util.Scanner;

public class Ex2 {
    public static int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int n = scanner.nextInt();

        int result = C_k_n(k, n);

        System.out.println(result);
    }

    public static int C_k_n(int k, int n) {
        int[][] arr = new int[k + 1][n + 1];

        for (int j = 0; j <= k; j++) {
            arr[0][j] = 1;
            arr[j][j] = 1;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                if (j > i) {
                    arr[i][j] = (arr[i - 1][j - 1] + arr[i][j - 1]) % MOD;
                }
            }
        }

        return arr[k][n];
    }
}