#include <iostream>
#include <cmath>

using namespace std;

int main() {
    int m, n, arr[1000][1000], sum = 0;

    cin >> m >> n;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cin >> arr[i][j];
            sum += arr[i][j];
        }
    }

    cout << sum;

    return 0;
}