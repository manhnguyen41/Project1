#include <iostream>

using namespace std;

double tinhGiaCu(int soKwhDien) {
    double gia = 0;
    if (soKwhDien >= 0 && soKwhDien <= 50) {
        gia += soKwhDien * 1728;
    }
    if (soKwhDien >= 51 && soKwhDien <= 100) {
        gia += 50 * 1728;
        soKwhDien -= 50;
        gia += soKwhDien * 1786;
    }
    if (soKwhDien >= 101 && soKwhDien <= 200) {
        gia += 50 * 1728;
        gia += 50 * 1786;
        soKwhDien -= 100;
        gia += soKwhDien * 2074;
    }
    if (soKwhDien >= 201 && soKwhDien <= 300) {
        gia += 50 * 1728;
        gia += 50 * 1786;
        gia += 100 * 2074;
        soKwhDien -= 200;
        gia += soKwhDien * 2612;
    }
    if (soKwhDien >= 301 && soKwhDien <= 400) {
        gia += 50 * 1728;
        gia += 50 * 1786;
        gia += 100 * 2074;
        gia += 100 * 2612;
        soKwhDien -= 300;
        gia += soKwhDien * 2919;
    }
    if (soKwhDien >= 401) {
        gia += 50 * 1728;
        gia += 50 * 1786;
        gia += 100 * 2074;
        gia += 100 * 2612;
        gia += 100 * 2919;
        soKwhDien -= 400;
        gia += soKwhDien * 3015;
    }

    return gia*1.1;
}

double tinhGiaMoi(int soKwhDien) {
    double gia = 0;
    if (soKwhDien >= 0 && soKwhDien <= 100) {
        gia += soKwhDien * 1728;
    }
    if (soKwhDien >= 101 && soKwhDien <= 200) {
        gia += 100 * 1728;
        soKwhDien -= 100;
        gia += soKwhDien * 2074;
    }
    if (soKwhDien >= 201 && soKwhDien <= 400) {
        gia += 100 * 1728;
        gia += 100 * 2074;
        soKwhDien -= 200;
        gia += soKwhDien * 2612;
    }
    if (soKwhDien >= 401 && soKwhDien <= 700) {
        gia += 100 * 1728;
        gia += 100 * 2074;
        gia += 200 * 2612;
        soKwhDien -= 400;
        gia += soKwhDien * 3111;
    }
    if (soKwhDien >= 701) {
        gia += 100 * 1728;
        gia += 100 * 2074;
        gia += 200 * 2612;
        gia += 300 * 3111;
        soKwhDien -= 700;
        gia += soKwhDien * 3457;
    }

    return gia*1.1;
}

int main() {
    int soKwhDien;

    cin >> soKwhDien;

    printf("%.2lf", tinhGiaMoi(soKwhDien) - tinhGiaCu(soKwhDien));

    return 0;
}