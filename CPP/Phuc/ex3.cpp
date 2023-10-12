#include <iostream>

using namespace std;

int countCharInString(string text, char kiTu) {
    int count = 0;
    int length = text.length();
    
    for (int i = 0; i < length; i++) {
        if (text[i] == kiTu) {
            count++;
        }
    }

    return count;
}

int main() {
    string text;
    char kiTu;

    cin >> kiTu;
    getline(cin, text);
    getline(cin, text);

    cout << countCharInString(text, kiTu);

    return 0;
}