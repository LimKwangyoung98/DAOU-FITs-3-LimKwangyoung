#include <stdio.h>

#define X 1

int main() {
  #if (X == 1)
    printf("X is 1\n");
  #elif (X == 2)
    printf("X is 2\n");
  #else
    printf("X is nothing\n");
  #endif
}
