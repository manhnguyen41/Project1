#include <iostream>

using namespace std;

void binary(int n) {
    if (n < 0) {
        cout << 0;
        return;
    } else {
        if (n != 0) {
            int m = n % 2;
            n /= 2;
            binary(n);
            cout << m;
        } else {
            return;
        }
    }
}

int main() {
    int n;
    cin >> n;

    binary(n);

    return 0;
}