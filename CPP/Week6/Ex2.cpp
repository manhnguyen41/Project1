#include <iostream>
#include <vector>
#include <limits>
#include <algorithm>

using namespace std;

const int MAX_VALUE = numeric_limits<int>::max();

int dijkstra(vector<vector<int>>& edges, int s, int t, int n) {
    vector<int> d(n + 1, MAX_VALUE);
    for (int i = 1; i <= n; i++) {
        d[i] = edges[s][i];
    }

    d[s] = 0;
    vector<bool> marked(n + 1, false);
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

int main() {
    int n, m;
    cin >> n >> m;

    vector<vector<int>> edges(n + 1, vector<int>(n + 1, MAX_VALUE));

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            edges[i][j] = MAX_VALUE;
        }
    }

    for (int i = 1; i <= m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        edges[u][v] = w;
    }

    int s, t;
    cin >> s >> t;

    int result = dijkstra(edges, s, t, n);
    cout << result << endl;

    return 0;
}
