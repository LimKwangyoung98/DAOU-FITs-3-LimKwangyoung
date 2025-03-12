void assignment_1001() {
	printf("\n========================\n");
	printf("1. 원의 둘레 구하기\n");
	printf("2. 원의 넓이 구하기\n");
	printf("3. 구의 부피 구하기\n");
	printf("4. 그만두기\n");
	printf("========================\n");

	int choice;
	int radius;
	while (1) {
		printf("원하는 옵션을 입력하시오.: ");
		scanf_s("%d", &choice);

		printf("반지름을 입력하시오.: ");
		scanf_s("%d", &radius);

		switch (choice) {
		case 1:
			printf("반지름이 %d인 원의 둘레는 %.2f\n", radius, 2 * M_PI * radius);
			break;
		case 2:
			printf("반지름이 %d인 원의 넓이는 %.2f\n", radius, M_PI * radius * radius);
			break;
		case 3:
			printf("반지름이 %d인 구의 부피는 %.2f\n", radius, 4 / 3 * M_PI * pow(radius, 3));
			break;
		case 4:
			break;
		}
	}
}

void assignment_1002() {
	int num;
	printf("정수 n을 입력하시오.: ");
	scanf_s("%d", &num);

	int total = 0;
	for (int i = 2; i <= num; i += 2) {
		total += i;
	}
	printf("정수 1에서 %d 이하 짝수들의 합은 %d입니다.", num, total);
}