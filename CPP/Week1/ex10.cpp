#include <iostream>
#include <cmath>

using namespace std;

int main() {
    int a, b, c;

    cin >> a >> b >> c;

    int delta = b * b - 4 * a * c;

    if (delta < 0) {
        cout << "NO SOLUTION";
    } else {
        if (delta == 0) {
            printf("%.2lf", -b * 1.0 / 2 / a);
        } else {
            printf("%.2lf %.2lf", (-b - sqrt(delta)) * 1.0 / 2 / a, (-b + sqrt(delta)) * 1.0 / 2 / a);
        }
    }

    return 0;
}