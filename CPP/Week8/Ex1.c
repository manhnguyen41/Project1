#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Define a custom Time struct
struct Time {
    int hour;
    int minute;
    int second;
};

// Function to convert a string "HH:MM:SS" to a Time struct
struct Time stringToTime(char* timeStr) {
    struct Time result;
    if (sscanf(timeStr, "%d:%d:%d", &result.hour, &result.minute, &result.second) == 3) {
        if (result.second == 60) {
            result.second = 0;
            result.minute++;
            if (result.minute == 60) {
                result.minute = 0;
                result.hour++;
                if (result.hour == 24) {
                    result.hour = 0;
                }
            }
        }
    } else {
        // Handle invalid input
        printf("Invalid time format: %s\n", timeStr);
        result.hour = result.minute = result.second = 0; // Default values
    }
    return result;
}

// Function to compare two Time structs
int compareTimes(struct Time time1, struct Time time2) {
    if (time1.hour < time2.hour) return -1;
    if (time1.hour > time2.hour) return 1;
    if (time1.minute < time2.minute) return -1;
    if (time1.minute > time2.minute) return 1;
    if (time1.second < time2.second) return -1;
    if (time1.second > time2.second) return 1;
    return 0; // The times are equal
}

// Define the structure for an Submission
struct Submission {
    char userId[10];
    char problemId[10];
    struct Time timePoint;
    int status;
    int point;
};

// Function to create a new Submission
struct Submission createSubmission(char* userId, char* problemId, struct Time timePoint, char *status, int point) {
    struct Submission newSubmission;
    strcpy(newSubmission.userId, userId);
    strcpy(newSubmission.problemId, problemId);
    newSubmission.timePoint = timePoint;
    newSubmission.status = strlen(status) == 2 ? 1 : 0;
    newSubmission.point = point;
    return newSubmission;
}

// Define the structure for a Node
struct Node {
    struct Submission submission;
    struct Node *next;
};

struct Point {
    char userId[10];
    char problemId[10];
    int point;
};

struct NodePoint {
    struct Point point;
    struct NodePoint *next;
};


struct Node *head[1000];
struct NodePoint *headPoint[1000];

int hashString(char* str) {
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

void initHashTablePoint() {
    for (int i = 0; i < 1000; i++) {
        headPoint[i] = NULL;
    }
}

// Function to create a new Node with the given Submission
struct Node* createNode(struct Submission submission) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    if (newNode) {
        newNode->submission = submission;
        newNode->next = NULL;
    }
    return newNode;
}

// Function to insert a new Node at the beginning of the linked list
struct Node* insert(struct Node* head, struct Node* newNode) {
    if (newNode == NULL) {
        return head;
    }

    newNode->next = head;
    return newNode;
}

struct Point createPoint(char* userId, char* problemId, int point) {
    struct Point newPoint;
    strcpy(newPoint.userId, userId);
    strcpy(newPoint.problemId, problemId);
    newPoint.point = point;
    return newPoint;
}

struct NodePoint* createNodePoint(struct Point point) {
    struct NodePoint* newNode = (struct NodePoint*)malloc(sizeof(struct NodePoint));
    if (newNode) {
        newNode->point = point;
        newNode->next = NULL;
    }
    return newNode;
}

struct NodePoint* insertNodePoint(struct NodePoint* head, struct NodePoint* newNode) {
    if (newNode == NULL) {
        return head;
    }

    newNode->next = head;
    return newNode;
}

int numErrOfUser(char* userId) {
    int number = 0;
    struct Node* current = head[hashString(userId)];

    while (current != NULL) {
        if (strcmp(current->submission.userId, userId) == 0 && current->submission.status == 0) {
            number++;
        }
        current = current->next;
    }
    return number;
}

int totalPointOfUser(char* userId) {
    int totalPoint = 0;
    struct NodePoint* current = headPoint[hashString(userId)];

    while (current != NULL) {
        if (strcmp(current->point.userId, userId) == 0) {
            totalPoint += current->point.point;
        }
        current = current->next;
    }

    return totalPoint;
}

int numberSubmissionInPeriod(struct Time fromTime, struct Time toTime) {
    int number = 0;
    for (int i = 0; i < 1000; i++) {
        struct Node* current = head[i];
        while (current != NULL) {
            if (compareTimes(current->submission.timePoint, fromTime) >= 0 && compareTimes(current->submission.timePoint, toTime) <= 0) {
                number++;
            }
            current = current->next;
        }
    }
    return number;
}

struct NodePoint *searchNodePoint(char *userId, char *problemId) {
    struct NodePoint* current = headPoint[hashString(userId)];

    while (current != NULL) {
        if (strcmp(current->point.userId, userId) == 0 && strcmp(current->point.problemId, problemId) == 0) {
            return current;
        }
        current = current->next;
    }
    
    return current;
}

void checkMaxPoint(char *userId, char *problemId, int point) {
    struct NodePoint* current = searchNodePoint(userId, problemId);

    if (current == NULL) {
        struct Point newPoint = createPoint(userId, problemId, point);
        headPoint[hashString(userId)] = insertNodePoint(headPoint[hashString(userId)], createNodePoint(newPoint));
        return;
    }

    if (point > current->point.point) {
        current->point.point = point;
    }
}

int main() {
    initHashTable();
    initHashTablePoint();
    int totalNumberSubmissions = 0;
    int numberErrSubmission = 0;

    while (1) {
        char userId[10];
        if (scanf("%s", userId) == EOF) {
            break;
        }

        if (strcmp(userId, "#") == 0) {
            break;
        }

        char problemId[10], timeStr[50], err[10];
        int point;

        if (scanf("%s %s %s %d", problemId, timeStr, err, &point) != 4) {
            printf("Invalid input format.\n");
            return 1;
        }

        struct Time timePoint = stringToTime(timeStr);
        struct Submission newSubmission = createSubmission(userId, problemId, timePoint, err, point);

        head[hashString(userId)] = insert(head[hashString(userId)], createNode(newSubmission));

        if (strlen(err) == 2) {
            checkMaxPoint(userId, problemId, point);
        } else {
            numberErrSubmission++;
        }
        totalNumberSubmissions++;
    }

    while (1) {
        char command[100];
        if (scanf("%s", command) == EOF) {
            break;
        }

        if (strcmp(command, "#") == 0) {
            break;
        }

        if (strcmp(command, "?total_number_submissions") == 0) {
            printf("%d\n", totalNumberSubmissions);
            continue;
        }
        if (strcmp(command, "?number_error_submision") == 0) {
            printf("%d\n", numberErrSubmission);
            continue;
        } 
        if (strcmp(command, "?number_error_submision_of_user") == 0) {
            char userId[10];
            if (scanf("%s", userId) != 1) {
                printf("Invalid input format.\n");
                return 1;
            }
            int numberErr = numErrOfUser(userId);
            printf("%d\n", numberErr);
            continue;
        } 
        if (strcmp(command, "?total_point_of_user") == 0) {
            char userId[10];
            if (scanf("%s", userId) != 1) {
                printf("Invalid input format.\n");
                return 1;
            }
            int totalPointUser = totalPointOfUser(userId);
            printf("%d\n", totalPointUser);
            continue;
        } 
        if (strcmp(command, "?number_submission_period") == 0) {
            char fromTimeStr[50], toTimeStr[50];
            if (scanf("%s %s", fromTimeStr, toTimeStr) != 2) {
                printf("Invalid input format.\n");
                return 1;
            }
            struct Time fromTime = stringToTime(fromTimeStr);
            struct Time toTime = stringToTime(toTimeStr);
            int numberInPeriod = numberSubmissionInPeriod(fromTime, toTime);
            printf("%d\n", numberInPeriod);
            continue;
        }
    }
    
    return 0;
}
