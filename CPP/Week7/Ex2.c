#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define a custom Time struct
struct Time {
    int hour;
    int minute;
    int second;
};

// Function to convert a string "HH:MM:SS" to a Time struct
struct Time stringToTime(const char* timeStr) {
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

// Define the structure for an Order
struct Order {
    char customerId[50];
    char productId[50];
    int price;
    char shopId[50];
    struct Time timePoint;
};

// Function to create a new Order
struct Order createOrder(const char* customerId, const char* productId, int price, const char* shopId, struct Time timePoint) {
    struct Order newOrder;
    strcpy(newOrder.customerId, customerId);
    strcpy(newOrder.productId, productId);
    newOrder.price = price;
    strcpy(newOrder.shopId, shopId);
    newOrder.timePoint = timePoint;
    return newOrder;
}

// Define the structure for a Node
struct Node {
    struct Order order;
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

// Function to create a new Node with the given Order
struct Node* createNode(struct Order order) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    if (newNode) {
        newNode->order = order;
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

// Function to calculate the revenue of a shop based on shopId
int revenueOfShop(const char* shopId) {
    int totalRevenue = 0;
    struct Node* current = head[hashString(shopId)];

    while (current != NULL) {
        if (strcmp(current->order.shopId, shopId) == 0) {
            totalRevenue += current->order.price;
        }
        current = current->next;
    }
    return totalRevenue;
}

// Function to calculate the total revenue for a shop selling products to a specific customer
int totalRevenueForShopToCustomer(const char* shopId, const char* customerId) {
    int totalRevenue = 0;
    for (int i = 0; i < 1000; i++) {
        struct Node* current = head[i];
        while (current != NULL) {
            if (strcmp(current->order.shopId, shopId) == 0 && strcmp(current->order.customerId, customerId) == 0) {
                totalRevenue += current->order.price;
            }
            current = current->next;
        }
    }
    return totalRevenue;
}

// Function to calculate the total revenue for a given period
int totalRevenueInPeriod(const struct Time fromTime, const struct Time toTime) {
    int totalRevenue = 0;
    for (int i = 0; i < 1000; i++) {
        struct Node* current = head[i];
        while (current != NULL) {
            if (compareTimes(current->order.timePoint, fromTime) >= 0 && compareTimes(current->order.timePoint, toTime) <= 0) {
                totalRevenue += current->order.price;
            }
            current = current->next;
        }
    }
    return totalRevenue;
}

int main() {
    initHashTable();
    int totalNumberOrders = 0;
    int totalRevenue = 0;

    while (1) {
        char customerId[50];
        if (scanf("%s", customerId) == EOF) {
            break;
        }

        if (strcmp(customerId, "#") == 0) {
            break;
        }

        char productId[50], shopId[50], timeStr[50];
        int price;

        if (scanf("%s %d %s %s", productId, &price, shopId, timeStr) != 4) {
            printf("Invalid input format.\n");
            return 1;
        }

        struct Time timePoint = stringToTime(timeStr);
        struct Order newOrder = createOrder(customerId, productId, price, shopId, timePoint);

        head[hashString(shopId)] = insert(head[hashString(shopId)], createNode(newOrder));
        totalNumberOrders++;
        totalRevenue += price;
    }

    while (1) {
        char command[100];
        if (scanf("%s", command) == EOF) {
            break;
        }

        if (strcmp(command, "#") == 0) {
            break;
        }

        if (strcmp(command, "?total_number_orders") == 0) {
            printf("%d\n", totalNumberOrders);
            continue;
        }
        if (strcmp(command, "?total_revenue") == 0) {
            printf("%d\n", totalRevenue);
            continue;
        } 
        if (strcmp(command, "?revenue_of_shop") == 0) {
            char shopId[50];
            if (scanf("%s", shopId) != 1) {
                printf("Invalid input format.\n");
                return 1;
            }
            int shopRevenue = revenueOfShop(shopId);
            printf("%d\n", shopRevenue);
            continue;
        } 
        if (strcmp(command, "?total_consume_of_customer_shop") == 0) {
            char customerId[50], shopId[50];
            if (scanf("%s %s", customerId, shopId) != 2) {
                printf("Invalid input format.\n");
                return 1;
            }
            int customerShopRevenue = totalRevenueForShopToCustomer(shopId, customerId);
            printf("%d\n", customerShopRevenue);
            continue;
        } 
        if (strcmp(command, "?total_revenue_in_period") == 0) {
            char fromTimeStr[50], toTimeStr[50];
            if (scanf("%s %s", fromTimeStr, toTimeStr) != 2) {
                printf("Invalid input format.\n");
                return 1;
            }
            struct Time fromTime = stringToTime(fromTimeStr);
            struct Time toTime = stringToTime(toTimeStr);
            int periodRevenue = totalRevenueInPeriod(fromTime, toTime);
            printf("%d\n", periodRevenue);
            continue;
        }
    }

    return 0;
}
