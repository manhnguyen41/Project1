package week2;

import java.util.Scanner;

public class Ex6 {
    public static int [][]arr = new int[10][10];
    public static boolean [][]markCol = new boolean[10][10];
    public static boolean [][]markRow = new boolean[10][10];
    public static boolean [][]markSquare = new boolean[10][10];
    public static int count = 0;

    public static void tryK(int k, int l) {
        if (arr[k][l] != 0) {
            if (k == 9 && l == 9) {
                count++;
            } else {
                if (l != 9) {
                    tryK(k, l + 1);
                } else {
                    tryK(k + 1,1);
                }
            }
            return;
        }
        
        for (int i = 1; i <= 9; i++) {
            if (markCol[l][i] || markRow[k][i] || markSquare[(k-1)/3*3+(l-1)/3+1][i]) {
                continue;
            }
            
            arr[k][l] = i;
            markCol[l][i] = true;
            markRow[k][i] = true;
            markSquare[(k-1)/3*3+(l-1)/3+1][i] = true;
            
            if (k == 9 && l == 9) {
                count++;
            } else {
                if (l != 9) {
                    tryK(k, l + 1);
                } else {
                    tryK(k + 1,1);
                }
            }

            arr[k][l] = 0;
            markCol[l][i] = false;
            markRow[k][i] = false;
            markSquare[(k-1)/3*3+(l-1)/3+1][i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                markCol[j][j] = false;
                markRow[j][j] = false;
                markSquare[j][j] = false;
            }
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                arr[i][j] = scanner.nextInt();
                
                if (arr[i][j] != 0) {
                    markCol[j][arr[i][j]] = true;
                    markRow[i][arr[i][j]] = true;
                    markSquare[(i-1)/3*3+(j-1)/3+1][arr[i][j]] = true;
                }
            }
        }
        
        tryK(1, 1);
        System.out.println(count);
    }
}
