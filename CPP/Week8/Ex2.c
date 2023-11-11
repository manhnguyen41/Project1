#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int numBorn[1500000];
int totalBorn[1500000];

int stringToDate(const char* dateStr) {
    int year, month, day;
    sscanf(dateStr, "%d-%d-%d", &year, &month, &day);
    return year * 365 + month * 30 + day;
}

struct Citizen {
    char code[10];
    int dob;
    char father[10];
    char mother[10];
    int isAlive;
    char regionCode[10];
};

struct Citizen createCitizen(char *code, int dob, char *father, char *mother) {
    struct Citizen newCitizen;
    strcpy(newCitizen.code, code);
    newCitizen.dob = dob;
    strcpy(newCitizen.father, father);
    strcpy(newCitizen.mother, mother);
    return newCitizen;
}

struct Node {
    struct Citizen citizen;
    struct Node *next;
};

struct Node *head[1000];

int hashString(const char* str) {
    int hash = 0;
    int len = strlen(str);

    for (int i = 0; i < len; i++) {
        hash = (hash * 31 + str[i]) % 1000;
    }

    return hash;
}

void initHashTable() {
    for (int i = 0; i < 1000; i++) {
        head[i] = NULL;
    }
}

struct Node* createNode(struct Citizen citizen) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    if (newNode) {
        newNode->citizen = citizen;
        newNode->next = NULL;
    }
    return newNode;
}

struct Node* insert(struct Node* node, struct Node* newNode) {
    if (newNode == NULL) {
        return node;
    }

    newNode->next = node;
    return newNode;
}

int numberPeopleBornAt(char *date) {
    return numBorn[stringToDate(date)];
}

int mostAliveAncestor(char *code) {
    struct Node* current = head[hashString(code)];

    int maxDepth = 0;

    while (current) {
        if (strcmp(code, current->citizen.code) == 0) {
            int fatherAncestor = -1;
            int motherAncestor = -1;

            if (strcmp(current->citizen.father, "0000000") != 0) {
                fatherAncestor = mostAliveAncestor(current->citizen.father) + 1;
            }

            if (strcmp(current->citizen.mother, "0000000") != 0) {
                motherAncestor = mostAliveAncestor(current->citizen.mother) + 1;
            }

            int ancestorDepth = (fatherAncestor > motherAncestor) ? fatherAncestor : motherAncestor;
            
            if (ancestorDepth > maxDepth) {
                maxDepth = ancestorDepth;
            }
        }
        current = current->next;
    }
    
    return maxDepth;
}

int numberPeopleBornBetween(char *date1, char *date2) {
    int count = totalBorn[stringToDate(date2)] - totalBorn[stringToDate(date1)] + numBorn[stringToDate(date1)];
    return count;
}

struct Node *searchCitizen(char *code) {
    struct Node *current = head[hashString(code)];

    if (current == NULL) {
        return NULL;
    }

    if (strcmp(current->citizen.code, "0000000") == 0) {
        return NULL;
    }

    while (current != NULL)
    {
        if (strcmp(current->citizen.code, code) == 0 
        || strcmp(current->citizen.father, code) == 0 
        || strcmp(current->citizen.mother, code) == 0) {
            return current;
        }
        current = current->next;
    }
    
    return current;
}

int main() {
    initHashTable();
    int numberPeople = 0;
    int maxUnrelatedPeople = 0;

    for (int i = 0; i < 1500000; i++) {
        numBorn[i] = 0;
        totalBorn[i] = 0;
    }

    while (1) {
        char code[10];
        if (scanf("%s", code) == EOF) {
            break;
        }

        if (strcmp(code, "*") == 0) {
            break;
        }

        char dob[15], father[10], mother[10], isAlive, regionCode[10];

        if (scanf("%s %s %s %c %s", dob, father, mother, &isAlive, regionCode) != 5) {
            printf("Invalid input format.\n");
            return 1;
        }

        int date = stringToDate(dob);
        struct Citizen newCitizen = createCitizen(code, date, father, mother);

        struct Node *fatherNode = searchCitizen(father);
        struct Node *motherNode = searchCitizen(mother);
        struct Node *node = searchCitizen(code);
        if (fatherNode == NULL && motherNode == NULL && node == NULL) {
            maxUnrelatedPeople++;
        }

        head[hashString(code)] = insert(head[hashString(code)], createNode(newCitizen));
        numBorn[date]++;
        numberPeople++;
    }

    for (int i = 1; i < 1500000; i++) {
        totalBorn[i] += totalBorn[i - 1] + numBorn[i];
    }

    while (1) {
        char command[100];
        if (scanf("%s", command) == EOF) {
            break;
        }

        if (strcmp(command, "***") == 0) {
            break;
        }

        if (strcmp(command, "NUMBER_PEOPLE") == 0) {
            printf("%d\n", numberPeople);
            continue;
        }
        if (strcmp(command, "NUMBER_PEOPLE_BORN_AT") == 0) {
            char date[15];
            if (scanf("%s", date) != 1) {
                printf("Invalid input format.\n");
                return 1;
            }
            int number = numberPeopleBornAt(date);
            printf("%d\n", number);
            continue;
        } 
        if (strcmp(command, "MOST_ALIVE_ANCESTOR") == 0) {
            char code[10];
            if (scanf("%s", code) != 1) {
                printf("Invalid input format.\n");
                return 1;
            }
            int mostAlive = mostAliveAncestor(code);
            printf("%d\n", mostAlive);
            continue;
        } 
        if (strcmp(command, "NUMBER_PEOPLE_BORN_BETWEEN") == 0) {
            char fromDateStr[50], toDateStr[50];
            if (scanf("%s %s", fromDateStr, toDateStr) != 2) {
                printf("Invalid input format.\n");
                return 1;
            }
            int number = numberPeopleBornBetween(fromDateStr, toDateStr);
            printf("%d\n", number);
            continue;
        }
        if (strcmp(command, "MAX_UNRELATED_PEOPLE") == 0) {
            printf("%d\n", maxUnrelatedPeople > numberPeople / 2 ? maxUnrelatedPeople : numberPeople - maxUnrelatedPeople);
            continue;
        } 
    }

    return 0;
}
