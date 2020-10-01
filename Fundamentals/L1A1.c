#include <stdio.h>

/*
 *  ---------------------------------------------------------------------
 *  In C implement a recursive and an iterative version of a function
 *  which reads characters from stdin until a newline character is read
 *  and then prints them on stdout in reverse order. Hint: use getchar(),
 *  putchar() (or getc(), putc()). For the iterative version you may
 *  assume a fixed max length of the input.
 *  ---------------------------------------------------------------------
 */

void rec() {
	char c = getchar();

	if(c != '\n') {
		rec(c);
	}
	putchar(c);
}

void iter() {
	char c;
	int counter = 0;
  char list[128];

  while (( c = getchar()) != '\n') {
  	list[counter]=c;
    counter++;
	}

  for (int i =counter-1; i >= 0; i--) {
    printf("%c", list[i]) ;
  }

  printf("\n");
}

int main() {
	printf("Write characters:\n");
	//rec();
	iter();
	printf("\n");
	return 0;
};
