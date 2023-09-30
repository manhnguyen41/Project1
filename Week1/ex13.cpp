#include <iostream>
#include <string>

using namespace std;

// int countInLine(string line) {
//     int count = 0;
//     int length = line.length();

//     for (int i = 0; i < length; i++) {
//         if ((line[i] >= 65 && line[i] <= 90) || (line[i] >= 97 && line[i] <= 122) || (line[i] >= 48 && line[i] <= 57)) {
//             if (i == 0) {
//                 count++;
//             } else {
//                 if (line[i - 1] == 32 || line[i - 1] == 9 || line[i - 1] == 10 || line[i - 1] == 34 || line[i - 1] == 40) {
//                     count++;
//                 }
//                 if (line[i] >= 48 && line[i] <= 57 && line[i - 1] == 36) {
//                     count++;
//                 }
//             }
//         }
//     }

//     return count;
// }

int main() {
    string text;
    int count = 0;

    getline(cin, text, static_cast<char>(EOF));

    int length = text.length();

    for (int i = 0; i < length; i++) {
        while (text[i] == 10 || text[i] == 9 || text[i] == 11)
        {
            text[i] = ' ';
        }
    }

    for (int i = 1; i < length; i++) {
        if (text[i] != 32 && (text[i - 1] == 32 || i == 1)) {
            count++;
        }
    }
    
    // cout << text;
    cout << count;

    return 0;
}