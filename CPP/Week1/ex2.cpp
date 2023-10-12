#include <iostream>

using namespace std;

int main() {
    int n;

    cin >> n;

    for (int i = 0; i <= 999; i++) {
        if (i * n >= 100 && i * n <= 999) {
            cout << i * n << ' ';
        }
    }

    return 0;
}