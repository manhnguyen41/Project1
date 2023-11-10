#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Account {
    char account[50];
    struct Account* next;
};

struct Account *accHead;

void initListOfAccount() {
    accHead = NULL;
}

// Function to add an account to the linked list if it doesn't exist
void addAccount(const char* account) {
    // Check if the account already exists in the linked list
    struct Account* current = accHead;
    while (current != NULL) {
        if (strcmp(current->account, account) == 0) {
            // The account already exists, so don't add it again
            return;
        }
        current = current->next;
    }

    // Create a new node and add the account to the linked list
    struct Account* newNode = (struct Account*)malloc(sizeof(struct Account));
    if (newNode) {
        strcpy(newNode->account, account);
        newNode->next = accHead;
        accHead = newNode;
    }
}

// Function to list sorted accounts
void listSortedAccounts() {
    // Create an array of strings to store accounts temporarily
    char accounts[1000][50];
    int count = 0;

    // Copy accounts from the linked list to the array
    struct Account* current = accHead;
    while (current != NULL) {
        strcpy(accounts[count], current->account);
        current = current->next;
        count++;
    }

    // Sort the accounts in the array
    for (int i = 0; i < count - 1; i++) {
        for (int j = i + 1; j < count; j++) {
            if (strcmp(accounts[i], accounts[j]) > 0) {
                char temp[50];
                strcpy(temp, accounts[i]);
                strcpy(accounts[i], accounts[j]);
                strcpy(accounts[j], temp);
            }
        }
    }

    // Print the sorted accounts
    for (int i = 0; i < count; i++) {
        printf("%s ", accounts[i]);
    }
    printf("\n");
}

// Define a custom Transaction struct
struct Transaction {
    char fromAccount[50];
    char toAccount[50];
    int money;
    struct Transaction *next;
};

int hashString(const char* str) {
    int hash = 0;
    int len = strlen(str);

    for (int i = 0; i < len; i++) {
        hash = (hash * 31 + str[i]) % 1000;
    }

    return hash;
}

struct Transaction *head[1000];

void initHashTable() {
    for (int i = 0; i < 1000; i++) {
        head[i] = NULL;
    }
}

// Function to create a new Transaction
struct Transaction* createTransaction(const char* fromAccount, const char* toAccount, int money) {
    struct Transaction* newTransaction = (struct Transaction*)malloc(sizeof(struct Transaction));
    if (newTransaction) {
        strcpy(newTransaction->fromAccount, fromAccount);
        strcpy(newTransaction->toAccount, toAccount);
        newTransaction->money = money;
        newTransaction->next = NULL;
    }
    return newTransaction;
}

// Function to insert a new Transaction at the beginning of the linked list
struct Transaction* insertTransaction(struct Transaction* newTransaction) {
    if (newTransaction == NULL) {
        return head;
    }

    int index = hashString(newTransaction->fromAccount);
    newTransaction->next = head[index];
    head[index] = newTransaction;
    return head;
}

int totalMoneyTransactionFrom(const char* fromAccount, struct Transaction* head[]) {
    int totalMoney = 0;
    int index = hashString(fromAccount);
    struct Transaction* current = head[index];

    while (current != NULL) {
        if (strcmp(current->fromAccount, fromAccount) == 0) {
            totalMoney += current->money;
        }
        current = current->next;
    }
    return totalMoney;
}

// Define a custom structure for a linked list node
struct VisitedNode {
    const char* account;
    struct VisitedNode* next;
};

// Initialize the visited linked list
struct VisitedNode* visitedList = NULL;

// Function to add an account to the visited linked list
void addVisited(const char* account) {
    struct VisitedNode* newNode = (struct VisitedNode*)malloc(sizeof(struct VisitedNode));
    if (newNode) {
        newNode->account = account;
        newNode->next = visitedList;
        visitedList = newNode;
    }
}

// Function to check if an account is in the visited list
int isVisited(const char* account) {
    struct VisitedNode* current = visitedList;
    while (current != NULL) {
        if (strcmp(current->account, account) == 0) {
            return 1; // Account is visited
        }
        current = current->next;
    }
    return 0; // Account is not visited
}

// Function to inspect cycles
int inspectCycle(const char* account, const char* account1, int count, int k) {
    int max = 0;
    int index = hashString(account);
    struct Transaction* current = head[index];

    while (current != NULL) {
        // Check if the current account is visited
        if (isVisited(current->toAccount)) {
            current = current->next;
            continue;
        }
        if (strcmp(current->toAccount, account1) == 0) {
            if (count + 1 == k) {
                max = 1;
            } else {
                current = current->next;
                continue;
            }
        }

        if (max == 1) {
            return 1;
        }

        addVisited(current->toAccount);

        max = inspectCycle(current->toAccount, account1, count + 1, k);

        // Unmark the current account when backtracking
        visitedList = visitedList->next;

        current = current->next;
    }

    return max;
}

int main() {
    initListOfAccount();
    initHashTable();
    int totalTransactions = 0;
    int totalMoney = 0;

    while (1) {
        char fromAccount[50];
        if (scanf("%s", fromAccount) == EOF) {
            break;
        }

        if (strcmp(fromAccount, "#") == 0) {
            break;
        }

        char toAccount[50];
        if (scanf("%s", toAccount) != 1) {
            printf("Invalid input format.\n");
            return 1;
        }

        int money;
        if (scanf("%d", &money) != 1) {
            printf("Invalid input format.\n");
            return 1;
        }

        char time[50];
        if (scanf("%s", time) != 1) {
            printf("Invalid input format.\n");
            return 1;
        }

        char atm[50];
        if (scanf("%s", atm) != 1) {
            printf("Invalid input format.\n");
            return 1;
        }

        struct Transaction* newTransaction = createTransaction(fromAccount, toAccount, money);
        insertTransaction(newTransaction);

        addAccount(fromAccount);
        addAccount(toAccount);

        totalTransactions++;
        totalMoney += money;
    }

    while (1) {
        char command[100];
        if (scanf("%s", command) == EOF) {
            break;
        }

        if (strcmp(command, "#") == 0) {
            break;
        }

        if (strcmp(command, "?number_transactions") == 0) {
            printf("%d\n", totalTransactions);
            continue;
        }

        if (strcmp(command, "?total_money_transaction") == 0) {
            printf("%d\n", totalMoney);
            continue;
        }

        if (strcmp(command, "?list_sorted_accounts") == 0) {
            listSortedAccounts();
            continue;
        }

        if (strcmp(command, "?total_money_transaction_from") == 0) {
            char id[50];
            if (scanf("%s", id) != 1) {
                printf("Invalid input format.\n");
                return 1;
            }
            printf("%d\n", totalMoneyTransactionFrom(id, head));
            continue;
        }

        if (strcmp(command, "?inspect_cycle") == 0) {
            char id[50];
            if (scanf("%s", id) != 1) {
                printf("Invalid input format.\n");
                return 1;
            }
            
            int k;
            if (scanf("%d", &k) != 1) {
                printf("Invalid input format.\n");
                return 1;
            }

            printf("%d\n", inspectCycle(id, id, 0, k));
        }
    }

    return 0;
}