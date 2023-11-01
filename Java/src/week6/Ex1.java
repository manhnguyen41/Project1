package week6;

// Problem: Max Flow

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class Ex1 {
    public static LinkedList<Integer> bfs(int [][]edges, int [][]flow, int s, int t, int n) {
        LinkedList<Integer> shortestPath = new LinkedList<>();
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(s);
        boolean []visited = new boolean[n + 1];
        visited[s] = true;
        int []parent = new int[n + 1];
        parent[s] = s;
        parent[t] = t;

        while (!bfsQueue.isEmpty()) {
            int u = bfsQueue.poll();

            if (visited[t]) {
                break;
            }

            for (int i = 1; i <= n; i++) {
                if (edges[u][i] - flow[u][i] != 0 && !visited[i]) {
                    bfsQueue.add(i);
                    visited[i] = true;
                    parent[i] = u;
                }
            }
        }

        int node = t;

        while (parent[node] != node) {
            shortestPath.addFirst(node);
            node = parent[node];
        }
        shortestPath.addFirst(s);

        return shortestPath;
    }

    public static void augment(int [][]edges, int [][]flow, LinkedList<Integer> shortestPath) {
        int minCapacity = MAX_VALUE;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            int c = edges[shortestPath.get(i)][shortestPath.get(i + 1)]
                    - flow[shortestPath.get(i)][shortestPath.get(i + 1)];
            if (c < minCapacity) {
                minCapacity = c;
            }
        }

        for (int i = 0; i < shortestPath.size() - 1; i++) {
            int capacity = edges[shortestPath.get(i)][shortestPath.get(i + 1)];
            if (capacity != 0) {
                flow[shortestPath.get(i)][shortestPath.get(i + 1)] += minCapacity;
            } else {
                flow[shortestPath.get(i)][shortestPath.get(i + 1)] -= minCapacity;
            }
        }
    }
    public static int EdmondKarp(int [][]edges, int s, int t, int n) {
        int [][]flow = new int[n + 1][n + 1];
        int maxFlow = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                flow[i][j] = 0;
            }
        }

        while (true) {
            LinkedList<Integer> shortestPath = bfs(edges, flow, s, t, n);
            if (shortestPath.size() == 1) {
                break;
            }
            augment(edges, flow, shortestPath);
        }
        for (int i = 1; i <= n; i++) {
            maxFlow += flow[s][i];
        }
        return maxFlow;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int [][]edges = new int[n + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[u][v] = c;
        }

        System.out.println(EdmondKarp(edges, s, t, n));
    }
}
