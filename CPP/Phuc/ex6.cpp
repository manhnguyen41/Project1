#include <iostream>
#include <string>

using namespace std;

int main() {
    string text;

    getline(cin, text);

    int length = text.length();

    int endIndex = length;

    for (int i = length; i >= 0; i--) {
        if (i == 0) {
            for (int j = i; j < endIndex; j++) {
                cout << text[j];
            }
        }
        if (text[i - 1] == 32) {
            for (int j = i; j < endIndex; j++) {
                cout << text[j];
            }
            cout << " ";
            endIndex = i - 1;
        }
    }

    return 0;
}