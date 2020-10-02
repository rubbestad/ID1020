#include <stdio.h>
#include <ctype.h>

/*
 *  ------------------------------------------------------------
 *  Write a simple filter to clean a text, i.e. to remove all
 *  characters that are not alphabetic, blank or newline -
 *  replacing every such character by a blank to keep the number
 *  of characters constant to the original text.
 *  ------------------------------------------------------------
 */

int main() {

	FILE *fp;
	char chars;
	fp = fopen("gutenberg.txt", "r");

	while((chars = getc(fp)) != EOF) {

		if(isalpha(chars) == 1 || chars == 10) {
			putchar(chars);
		}
		else {
			printf(" ");
		}
	}
	fclose(fp);
	return 0;
}
