package week5;

// Problem: Minimum Spanning Tree - Kruskal

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Ex1 {
    static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return parent[i] = find(parent, parent[i]);
    }

    static void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY)
            parent[rootX] = rootY;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int weight = 0;
        PriorityQueue<int[]> edge = new PriorityQueue<>(m, (a, b) -> a[2] - b[2]);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge.offer(new int[]{u, v, w});
        }

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int j = 0;

        while (!edge.isEmpty()){
            int []e = edge.poll();
            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (find(parent, u) != find(parent, v)) {
                union(parent, u, v);
                weight += w;
                j++;
                if (j == n - 1) {
                    break;
                }
            }
        }

        System.out.println(weight);
    }
}
