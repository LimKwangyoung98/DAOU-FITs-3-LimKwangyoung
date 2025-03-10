void assignment16() {
	int temp, b[SIZE] = { 1, 2, 3, 4 ,5 };
	char swap;

	for (int repeat = 1; repeat < SIZE; repeat++) {
		swap = 'N';
		for (int i = 0; i < SIZE - repeat; i++) {
			if (b[i] > b[i + 1]) {
				temp = b[i];
				b[i] = b[i + 1];
				b[i + 1] = temp;
				swap = 'Y';
			}
		}
		if (swap == 'N') {
			break;
		}
	}

	printf("\n정렬 후 배열: ");
	for (int i = 0; i < SIZE; i++) {
		printf("%3d", b[i]);
	}
	return 0;
}