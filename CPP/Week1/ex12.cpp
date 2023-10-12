#include <iostream>
#include <string>

using namespace std;

int main() {
    int n, arr[100000], k, count = 0, sum = 0;

    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    for (int i = 0; i < k; i++) {
        sum += arr[i];
    }
    if (sum % 2 == 0) {
        count++;
    }

    for (int i = 1; i < n - k + 1; i++) {
        sum += arr[i + k - 1];
        sum -= arr[i - 1];
        if (sum % 2 == 0) {
            count++;
        }
    }

    cout << count;

    return 0;
}