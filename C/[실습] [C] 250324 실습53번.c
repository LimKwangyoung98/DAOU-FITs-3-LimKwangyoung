#include <stdio.h>
#define ONE

#ifdef !ONE
    int a = 1;
#endif

int main() {
    printf("a: %d\n", a);
}
