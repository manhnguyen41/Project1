#include <iostream>
#include <cmath>

using namespace std;

int main() {
    int a, b, c;

    cin >> a >> b >> c;

    if (a == 0 && b == 0 && c == 0) {
        cout << "VO SO NGHIEM";
        return 0;
    }

    int delta = b * b - 4 * a * c;

    if (delta < 0) {
        cout << "VO NGHIEM";
    } else {
        if (delta == 0) {
            if (b == 0) {
                printf("-%.4lf -%.4lf", -b * 1.0 / 2 / a, -b * 1.0 / 2 / a);
            } else {
                printf("%.4lf %.4lf", -b * 1.0 / 2 / a, -b * 1.0 / 2 / a);
            }   
        } else {
            printf("%.4lf %.4lf", (-b + sqrt(delta)) * 1.0 / 2 / a, (-b - sqrt(delta)) * 1.0 / 2 / a);
        }
    }

    return 0;
}