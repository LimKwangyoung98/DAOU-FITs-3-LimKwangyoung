void assignment_0601() {
	char gender;
	int age;
	float height;

	printf("성별은? (남자라면 M / 여자라면 F) >>> ");
	scanf("%c", &gender);

	printf("나이는? >>> ");
	scanf("%d", &age);

	printf("키는? >>> ");
	scanf("%f", &height);

	printf("\n");
	printf("==========\n");
	printf("성별: %c\n", gender);
	printf("나이: %d세\n", age);
	printf("키: %.1fcm\n", height);
	printf("\n");
}

void assignment_0602() {
	char name[10];  // 배열
	char gender;

	printf("이름은? >>> ");
	scanf_s("%s", name, 10);
	getchar();

	printf("성별은? (남자라면 M / 여자라면 F) >>> ");
	gender = getchar();

	printf("%s\n", name);
	putchar(gender);
}

void assignment_0603() {
	int num1, num2, num3;

	printf("두 개의 정수를 입력하세요. >>> ");
	scanf("%d %d", &num1, &num2);
	printf("%d %d\n", num1, num2);

	printf("세 개의 정수를 입력하세요. >>> ");
	scanf("%d %d %d", &num1, &num2, &num3);
	printf("%d %d %d\n", num1, num2, num3);
}