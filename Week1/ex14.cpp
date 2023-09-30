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

string replace(string text, string string1, string string2, int index) {
    string newText = text + string2;

    for (int i = text.length() + string2.length() - string1.length(); i >= index + string2.length(); i--) {
        newText[i] = text[i - string2.length() + string1.length()];
    }

    for (int i = 0; i < string2.length(); i++) {
        newText[index + i] = string2[i];
    }

    for (int i = text.length() + string2.length() - string1.length(); i < newText.length(); i++) {
        newText[i] = 0;
    }
    
    return newText;
}

int main() {
    string text, string1, string2;

    getline(cin, string1);
    getline(cin, string2);
    getline(cin, text);

    // text = text + string2;

    // text[text.length() - 1] = 0;

    for (int i = 0; i < text.length(); i++) {
        if (check(text, string1, i)) {
            text = replace(text, string1, string2, i);
        }
    }

    cout << text;

    return 0;
}