#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <errno.h>
#include <string.h>

int main() {
    FILE* f = fopen("./TestFile.txt", "wb");

    char str[] = "C 프로그래밍";

    if (f) {
        fwrite(str, 1, sizeof(str) - 1, f);
    } else {
        printf("Error: %d, %s", errno, strerror(errno));
    }

    return 0;
}