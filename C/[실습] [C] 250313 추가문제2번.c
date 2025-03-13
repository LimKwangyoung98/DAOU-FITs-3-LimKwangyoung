#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

#define ROWS 3
#define COLS 3

typedef void (*operation)(int**);
void sum(int**);
void max(int**);
void min(int**);
void square(int**);

void practice2() {
    operation operations[4] = { sum, max, min, square };

    int array[ROWS][COLS] = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8}
    };
    int* arr[ROWS] = { array[0], array[1], array[2] };
    void (*fn) (int**);
    int choice;

    printf("연산 방법을 선택하기\n");
    printf("(0: 합, 1: 최대값, 2: 최소값, 3: 제곱): ");
    scanf("%d", &choice);

    fn = operations[choice];
    fn((int**)arr);
}

void sum(int** arr) {
    int result = 0;

    for (int i = 0; i < ROWS * COLS; i++) {
        result += **arr + i;
    }
    
    printf("배열의 합은 %d\n", result);
}

void max(int** arr) {
    int result = -999;

    for (int i = 0; i < ROWS * COLS; i++) {
        result = **arr + i > result ? **arr =+ i : result;
    }
    printf("배열의 최댓값은 %d\n", result);
}

void min(int** arr) {
    int result = 999;

    for (int i = 0; i < ROWS * COLS; i++) {
        result = **arr + i < result ? **arr + i : result;
    }
    printf("배열의 최솟값은 %d\n", result);
}

void square(int** arr) {
    int result = 0;

    printf("배열의 제곱값은\n");
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            printf("%2d ", *(*(arr + i) + j) * *(*(arr + i) + j));
        }
        printf("\n");
    }
}