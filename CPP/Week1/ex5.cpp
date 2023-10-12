#include <iostream>
#include <string>

using namespace std;

string extract(string date) {
    int length = date.length();

    if (length != 10) {
        return "INCORRECT\n";
    }

    if (date[4] != 45 || date[7] != 45) {
        return "INCORRECT\n";
    }

    date[4] = 48;
    date[7] = 48;

    for (int i = 0; i < length; i++) {
        if (date[i] < 48 || date[i] > 57) {
            return "INCORRECT\n";
        }
    }

    int year = stoi(date) / 1000000;
    int month = (stoi(date) - year * 1000000) / 1000;

    if (month < 0 || month > 12) {
        return "INCORRECT\n";
    }

    int day = (stoi(date) - year * 1000000 - month * 1000);

    if (day < 0 || day > 30) {
        return "INCORRECT\n";
    }

    return to_string(year) + ' ' + to_string(month) + ' ' + to_string(day);
    
}

int main() {
    string date;

    cin >> date;

    cout << extract(date);

    return 0;
}