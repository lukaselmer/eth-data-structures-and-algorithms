import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class A4 {

	private static class HashMap {

		private Probing probing;
		private int m;
		private int collisions;
		private int[] values;

		public HashMap(int m, List<Integer> inputValues, Probing probing) {
			this.probing = probing;
			this.m = m;

			values = new int[m];

			for (Integer i : inputValues) {
				push(i);
			}
		}

		private void push(Integer k) {
			int position = positionFor(k);
			values[position] = k;
		}

		private int positionFor(Integer k) {
			int mod = k % m;
			int position = mod;
			int collisions = 0;
			while (values[position] != 0) {
				position = positiveMod(mod - probing.probe(++collisions, k, m), m);
				while (position < 0)
					position += m;
			}

			this.collisions += collisions; // > 0 ? 1 : 0;
			return position;
		}

		private int positiveMod(int n, int m) {
			return (n < 0) ? (m - (Math.abs(n) % m) ) %m : (n % m);
		}

		public int getCollisions() {
			return collisions;
		}

		public int[] getValues() {
			return values;
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
			int x = (j + 1) / 2;
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

				List<Integer> values = new LinkedList<Integer>();
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
		System.out.print(hashMap.getCollisions() + " ");
		printArray(hashMap.getValues());
		System.out.println();
	}

	private static void printArray(int[] values) {
		int iMax = values.length - 1;
		for (int i = 0;; i++) {
			System.out.print(values[i]);
			if (i == iMax)
				return;
			System.out.print(" ");
		}
	}
}
