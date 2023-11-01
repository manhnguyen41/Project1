package week6;

// Problem: Shortest Path between 2 nodes on a directed graph with non-negative weights
// Khong biet lam kieu gi de accept luon

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.MAX_VALUE;

public class Ex2 {
    public static int dijkstra(int [][]edges, int s, int t, int n) {
        int []d = new int[n + 1];
        if (n >= 0) System.arraycopy(edges[s], 1, d, 1, n);

        d[s] = 0;
        boolean []marked = new boolean[n + 1];
        marked[s] = true;
        int numMarked = n - 1;

        while (numMarked != 0) {
            int minWeight = MAX_VALUE;
            int u = 0;
            for (int i = 1; i <= n; i++) {
                if (!marked[i] && d[i] < minWeight) {
                    u = i;
                    minWeight = d[i];
                }
            }
            marked[u] = true;
            numMarked--;

            for (int i = 1; i <= n; i++) {
                if (!marked[i] && (d[i] > d[u] + edges[u][i] && edges[u][i] != MAX_VALUE)) {
                    d[i] = d[u] + edges[u][i];
                }
            }
        }

        return d[t];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [][]edges = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                edges[i][j] = MAX_VALUE;
            }
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[u][v] = w;
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(edges, s, t, n));
    }
}
