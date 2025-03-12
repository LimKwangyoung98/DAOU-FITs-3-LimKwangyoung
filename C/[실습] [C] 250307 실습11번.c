void assignment1101() {
	int num1, num2;

	printf("가감승제를 원하는 두 수를 입력하시오: ");
	scanf_s("%d %d", &num1, &num2);

	printf("%d + %d = %d\n", num1, num2, num1 + num2);
	printf("%d - %d = %d\n", num1, num2, num1 - num2);
	printf("%d * %d = %d\n", num1, num2, num1 * num2);
	printf("%d / %d = %f\n", num1, num2, (float)num1 / (float)num2);
}

void assignment1102() {
	int n;

	printf("원하는 단을 입력하시오.: ");
	scanf_s("%d", &n);

	for (int i = 1; i <= 9; i++) {
		printf("%d X %d = %2d\n", n, i, n * i);
	}
}

void assignment1103() {
	char alphabets[50];
	int toLower = 'a' - 'A';
	int toUpper = 'A' - 'a';

	printf("문자열을 입력하시오.: ");
	scanf_s("%s", alphabets, 50);

	for (int i = 0; i < 9; i++) {
		if (alphabets[i] == '\n' || alphabets[i] == '\0') break;

		if (alphabets[i] >= 'a' && alphabets[i] <= 'z') {
			printf("%c", alphabets[i] + toUpper);
		} else if (alphabets[i] >= 'A' && alphabets[i] <= 'Z') {
			printf("%c", alphabets[i] + toLower);
		}
	}
}