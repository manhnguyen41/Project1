package week5;

// Problem: Hamiton Cycle

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int []levelOfNode = new int[n + 1];

            for (int j = 1; j <= n; j++) {
                levelOfNode[j] = 0;
            }

            for (int j = 1; j <= m; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                levelOfNode[u]++;
                levelOfNode[v]++;
            }

            boolean isHamiton = true;

            for (int j = 1; j <= n; j++) {
                if (levelOfNode[j] < n / 2) {
                    isHamiton = false;
                    break;
                }
            }

            output.append(isHamiton ? 1 : 0);
            output.append("\n");
        }

        System.out.println(output);
    }
}
