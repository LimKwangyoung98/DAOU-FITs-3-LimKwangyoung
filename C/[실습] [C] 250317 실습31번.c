#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

typedef struct student {
	char name[20];
	char sex;
	int stid;
	int sub1;
	int sub2;
	int sub3;
	double avg;
} ST;

void input31(ST *st);

void assignment31() {
	ST st1 = { "kdhong", 'm', 1508001, 0, 0, 0, 0 };
	input31(&st1);
}

void input31(ST *st) {
	printf("%s 학생의 3과목 성적을 입력하시오.(공란으로 구분): ", st->name);
	scanf("%d %d %d", &st->sub1, &st->sub2, &st->sub3);
	printf("input() 함수에서 입력된 값은 %d, %d, %d입니다.\n", st->sub1, st->sub2, st->sub3);
}