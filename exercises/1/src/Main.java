import java.util.Scanner;

class Main {

	static int lines, i, a, b, c, d;
	static long result, i_1, i_2; 
	
	public static void main(String[] strings) {
		Scanner sc = new Scanner(System.in);

		try {

			lines = sc.nextInt();

			for (int j = 0; j < lines; ++j) {
				i = sc.nextInt();
				a = sc.nextInt();
				b = sc.nextInt();
				c = sc.nextInt();
				d = sc.nextInt();

				result = 1;

				if (i == 0) {
					result = a;
				}

				else if (i == 1) {
					result = b;
				} else {
					long i_1 = b;
					long i_2 = a;

					for (int x = 2; x <= i; x++) {
						result = c * i_1 + d * i_2;
						i_2 = i_1;
						i_1 = result;
					}
				}
				System.out.println(result);
			}

		} finally {
			sc.close();
		}
	}
}
