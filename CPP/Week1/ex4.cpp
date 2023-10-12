#include <iostream>

using namespace std;

string upperCase(string text) {
    int length = text.length();

    for (int i = 0; i < length; i++) {
        if (text[i] >= 97 && text[i] <= 122) {
            text[i] -= 32;
        } 
    }

    return text;
}

int main() {
    string text;

    getline(cin, text, static_cast<char>(EOF));

    cout << upperCase(text) << endl;
    return 0;
}