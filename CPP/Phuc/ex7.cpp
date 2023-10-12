#include <iostream>
#include <string>

using namespace std;

int check(string text, string string1, int index) {
    for (int i = 0; i < string1.length(); i++) {
        if (text[index + i] != string1[i]) {
            return 0;
        }
    }

    return 1;
}

int main() {
    string word;
    string text;

    cin >> word;
    getline(cin, text);
    getline(cin, text);

    int lengthText = text.length();
    int lengthWord = word.length();

    int count = 0;

    for (int i = 0; i < lengthText; i++) {
        if (check(text, word, i)) {
            count++;
        }
    }

    cout << count;

    return 0;
}