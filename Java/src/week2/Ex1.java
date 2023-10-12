package week2;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex1 {
    public static void main (String[] args) {
        ArrayList<Integer> fibo = new ArrayList<Integer>();
        fibo.add(0);
        fibo.add(1);

        Scanner myObj = new Scanner(System.in);
        int n = myObj.nextInt();

        for (int i = 2; i < n; i++) {
            fibo.add(fibo.get(i - 1) + fibo.get(i - 2));
        }

        System.out.println(fibo.get(n - 1));
    }
}