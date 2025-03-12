#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int max(int, int);
int min(int, int);

int assignment27() {
	int (*fpmm) (int, int);
	int num1, num2, flag;

	printf("두 개의 숫자를 입력하시오.: ");
	scanf("%d %d", &num1, &num2);

	printf("원하는 값을 입력하시오. (1: 큰 값, 2: 작은 값): ");
	scanf("%d", &flag);

	if (flag == 1) {
		fpmm = max;
		printf("두 수 중 큰 값은 %d\n", fpmm(num1, num2));
	} else if (flag == 2) {
		fpmm = min;
		printf("두 수 중 작은 값은 %d\n", fpmm(num1, num2));
	}
}

int max(int x, int y) {
	return x > y ? x : y;
}

int min(int x, int y) {
	return x < y ? x : y;
}