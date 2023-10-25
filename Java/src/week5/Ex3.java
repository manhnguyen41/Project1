package week5;

// Problem: Sequence of nodes visited by BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ex3 {
    public static void bfs(boolean []marked, boolean [][]edges, StringBuilder output, int n, Queue<Integer> nodes) {
        while (true) {
            if (nodes.isEmpty()) {
                for (int i = 1; i <= n; i++) {
                    if (!marked[i]) {
                        nodes.add(i);
                        marked[i] = true;
                        output.append(i);
                        output.append(" ");
                        break;
                    }
                }
            }
            if (nodes.isEmpty()) {
                break;
            }
            int start = nodes.poll();
            for (int i = 1; i <= n; i++) {
                if (edges[start][i] && !marked[i]) {
                    nodes.add(i);
                    marked[i] = true;
                    output.append(i);
                    output.append(" ");
                }
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
        Queue<Integer> nodes = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges[u][v] = true;
            edges[v][u] = true;
        }

        nodes.add(1);
        marked[1] = true;
        output.append(1);
        output.append(" ");
        bfs(marked, edges, output, n, nodes);

        System.out.println(output.toString());
    }
}
