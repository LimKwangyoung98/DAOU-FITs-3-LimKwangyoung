#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

#define MAX_SIZE 5

void practice1() {
	int row, col, arr[MAX_SIZE][MAX_SIZE], deleteRow;
	int* ptr[MAX_SIZE];

	printf("행과 열의 수를 입력하시오.: ");
	scanf("%d %d", &row, &col);

	printf("배열의 초기값은 자동으로 입력됩니다.\n");
	for (int i = 0; i < row; i++) {
		ptr[i] = arr[i];
		for (int j = 0; j < col; j++) {
			arr[i][j] = i * 10 + j;
			printf("%2d ", arr[i][j]);
		}
		printf("\n");
	}
	printf("\n");

	printf("삭제하려는 행의 인덱스 번호를 입력하시오.: ");
	scanf("%d", &deleteRow);

	if (deleteRow < 0 || deleteRow >= row) {
		printf("행의 인덱스 번호를 초과했습니다.\n");
		return;
	}
	else if (deleteRow < row - 1) {
		for (int i = deleteRow; i < row - 1; i++) {
			ptr[i] = ptr[i + 1];
		}
	}
	printf("\n");

	row--;
	printf("수정된 배열 출력\n");
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			printf("%2d ", ptr[i][j]);
		}
		printf("\n");
	}
}