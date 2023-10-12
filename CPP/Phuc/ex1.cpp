#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;

    int giaiThua = 1;
    for (int i = 1; i <= n; i++) {
        giaiThua *= i;
    }

    cout << giaiThua;

    return 0;
}