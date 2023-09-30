#include <iostream>
#include <string>

using namespace std;

int find_max(int *arr, int n);

int find_min(int *arr, int n);

int sum(int *arr, int n);

int find_max_segment(int *arr, int n, int i, int j);

int main() {
    int n, arr[100000], a[100000], b[100000];

    string command[10000];

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    cin >> command[0];
    int i = 1;
    while (true)
    {
        cin >> command[i];
        if (command[i] == "***") {
            break;
        }
        if (command[i] == "find-max-segment") {
            cin >> a[i] >> b[i];
        }
        i++;
    }
    int numOfCommand = i;

    for (int i = 1; i < numOfCommand; i++) {
        if (command[i] == "find-max") {
            cout << find_max(arr, n) << endl;
            continue;
        }
        
        if (command[i] == "find-min") {
            cout << find_min(arr, n) << endl;
            continue;
        }

        if (command[i] == "sum") {
            cout << sum(arr, n) << endl;
            continue;
        }

        cout << find_max_segment(arr, n, a[i] - 1, b[i] - 1) << endl;
    }

    return 0;
}

int find_max(int *arr, int n) {
    int max = arr[0];

    for (int i = 1; i < n; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    
    return max;
}

int find_min(int *arr, int n) {
    int min = arr[0];

    for (int i = 1; i < n; i++) {
        if (arr[i] < min) {
            min = arr[i];
        }
    }
    
    return min;
}

int sum(int *arr, int n) {
    int sum = 0;
    
    for (int i = 0; i < n; i++) {
        sum += arr[i];
    }

    return sum;
}

int find_max_segment(int *arr, int n, int i, int j) {
    int max = arr[i];

    for (int k = i; k <= j; k++) {
        if (arr[k] > max) {
            max = arr[k];
        }
    }

    return max;
}