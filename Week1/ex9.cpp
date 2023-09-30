#include <iostream>
#include <string>

using namespace std;

string extract(string time) {
    int length = time.length();

    if (length != 8) {
        return "INCORRECT\n";
    }

    if (time[2] != 58 || time[5] != 58) {
        return "INCORRECT\n";
    }

    time[2] = 48;
    time[5] = 48;

    for (int i = 0; i < length; i++) {
        if (time[i] < 48 || time[i] > 57) {
            return "INCORRECT\n";
        }
    }

    int hour = stoi(time) / 1000000;

    if (hour < 0 || hour > 23) {
        return "INCORRECT\n";
    }
    
    int minute = (stoi(time) - hour * 1000000) / 1000;

    if (minute < 0 || minute > 59) {
        return "INCORRECT\n";
    }

    int second = (stoi(time) - hour * 1000000 - minute * 1000);

    if (second < 0 || second > 59) {
        return "INCORRECT\n";
    }

    return to_string(hour * 3600 + minute * 60 + second);
    
}

int main() {
    string time;

    cin >> time;

    cout << extract(time);

    return 0;
}