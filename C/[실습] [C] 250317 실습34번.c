#include <stdio.h>
#include <time.h>

void assignment34() {
	time_t now;
	struct tm t;

	printf("1970년 1월 1일부터 현재까지의 초는 %d초입니다.\n", now);

	t = *localtime(&now);
	puts(asctime(&t));

	printf("현재의 년도: %d\n", t.tm_year);
	printf("현재의 월: %d\n", t.tm_mon);
	printf("현재의 일: %d\n", t.tm_mday);
	printf("현재의 요일: %d\n", t.tm_wday);
}