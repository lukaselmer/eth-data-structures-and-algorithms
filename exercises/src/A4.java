import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class A4 {

	private static class HashMap {

		public HashMap(int m, List<Integer> values, Probing probing) {
			// TODO Auto-generated constructor stub
		}

	}

	private static interface Probing {
		int probe(int j, int k, int m);
	}

	private static class LinearProbing implements Probing {

		@Override
		public int probe(int j, int k, int m) {
			return j;
		}

	}

	private static class QuadraticProbing implements Probing {

		@Override
		public int probe(int j, int k, int m) {
			int x = (j+1) / 2;
			int sign = (j % 2) == 0 ? 1 : -1;
			return x * x * sign;
		}

	}


	private static class DoubleHashing implements Probing {

		@Override
		public int probe(int j, int k, int m) {
			int hDash = 1 + (k % (m - 2));
			return j * hDash;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {

			int lines = sc.nextInt();

			for (int j = 0; j < lines; ++j) {
				int m = sc.nextInt();
				int n = sc.nextInt();

				List<Integer> values = new ArrayList<Integer>(n);
				for (int k = 0; k < n; ++k) {
					values.add(sc.nextInt());
				}

				print(new HashMap(m, values, new LinearProbing()));
				print(new HashMap(m, values, new QuadraticProbing()));
				print(new HashMap(m, values, new DoubleHashing()));
			}

		} finally {
			sc.close();
		}
	}

	private static void print(HashMap hashMap) {
		// TODO Implement this
	}
}
