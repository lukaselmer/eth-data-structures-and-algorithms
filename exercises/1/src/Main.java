import java.util.Scanner;

class Main {

	static void main(String[] strings) {
		Scanner sc = new Scanner(System.in);

		int lines = sc.nextInt();
		int i, a, b, c, d;

		for (int j = 0; j < lines; ++j) {
			i = sc.nextInt();
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			d = sc.nextInt();
			System.out.println(calc(i, a, b, c, d));
		}
	}

	private static long calc(int i, int a, int b, int c, int d) {
		if (i < 2)
			return i == 0 ? a : b;

		return (c * calc(i - 1, a, b, c, d)) + (d * calc(i - 2, a, b, c, d));
	}
}
