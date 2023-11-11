package week6;

// Problem: Shortest Path between 2 nodes on a directed graph with non-negative weights
// Khong biet lam kieu gi de accept luon

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Ex2 {
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int dijkstra(List<int[]>[] graph, int s, int t, int n) {
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE / 2);
        d[s] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            int distU = current.distance;

            if (u == t) {
                return distU;
            }

            // Skip if this node is outdated
            if (distU != d[u])
                continue;

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];
                if (distU + w < d[v]) {
                    d[v] = distU + w;
                    pq.add(new Node(v, d[v]));
                }
            }

        }

        return d[t];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            String[] lineContent = br.readLine().split(" ");
            int u = Integer.parseInt(lineContent[0]);
            int v = Integer.parseInt(lineContent[1]);
            int w = Integer.parseInt(lineContent[2]);

            graph[u].add(new int[]{v, w});

        }

        line = br.readLine().split(" ");
        int s = Integer.parseInt(line[0]);
        int t = Integer.parseInt(line[1]);


        System.out.println(dijkstra(graph, s, t, n));
    }
}
