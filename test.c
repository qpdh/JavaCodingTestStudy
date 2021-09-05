#include <stdio.h>

int main() {
	int n, a, b, c;
	scanf("%d", &n);
	a = n / 5;
	b = n % 5 / 3;

	if (n % 5 % 3 == 0) {
		printf("%d\n", a + b);
	}

	else{
		if (a > 1 && n % 5 % 3 == 1) { // %15 -> 1 -> 6 -> 2
			a -= 1;
			b += 2;
			printf("%d\n", a + b);
		}
		else if (a > 2 && n % 5 % 3 == 2) { // %15 -> 2 -> 12 -> 4
			a -= 2;
			b += 4;
			printf("%d\n", a + b);
		}
		else if (n % 3 == 0) // 
			printf("%d\n",n / 3);
		else
			printf("-1\n");
	}
	return 0;
}