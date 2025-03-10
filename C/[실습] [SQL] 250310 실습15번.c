void assignment15() {
	int f[M] = { 3, 0, -30, -20, -1 };
	int min;

	min = f[0];
	for (int i = 1; i < M; i++) {
		if (f[i] < min) {
			min = f[i];
		}
	}

	printf("어는 점 목록: ");
	for (int i = 0; i < M; i++) {
		printf("%4d", f[i]);
	}
	printf("\n가장 낮은 어는 점: %d\n", min);

}