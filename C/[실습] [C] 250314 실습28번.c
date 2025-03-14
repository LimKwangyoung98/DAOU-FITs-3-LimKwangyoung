#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

struct game {
	char name[7];
	int R1, R2, R3;
};

void assignment28() {
	struct game player;
	double avg;

	printf("선수의 이름은: ");
	scanf("%s", player.name);
	printf("1, 2, 3 라운드 점수는? ");
	scanf("%d %d %d", &player.R1, &player.R2, &player.R3);
	
	avg = (double)(player.R1 + player.R2 + player.R3) / 3;
	printf("%s 선수의 게임 결과 평균 %.1f점\n", player.name, avg);
}