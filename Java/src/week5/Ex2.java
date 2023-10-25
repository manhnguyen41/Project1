package week5;

// Problem: Sequence of nodes visited by DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex2 {
    public static void dfs(int start, boolean []marked, boolean [][]edges, StringBuilder output, int n) {
        output.append(start);
        output.append(" ");
        marked[start] = true;

        for (int i = 1; i <= n; i++) {
            if (edges[start][i] && !marked[i]) {
                dfs(i, marked, edges, output, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean []marked = new boolean[n + 1];
        boolean [][]edges = new boolean[n + 1][n + 1];

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges[u][v] = true;
            edges[v][u] = true;
        }

        dfs(1, marked, edges, output, n);

        System.out.println(output.toString());
    }
}
