#include <iostream>

using namespace std;

int main() {
    int n, arr[100000], odd = 0, even = 0;

    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> arr[i];
        if (arr[i] % 2 == 0) {
            even++;
        } else {
            odd++;
        }
    }

    cout << odd << ' ' << even;

    return 0;
}