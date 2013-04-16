import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class A7 {

	private static class Knapsack {

		private int n, W;
		private int[] v, w, vs;
		private int opt;

		public Knapsack(int n, int W, int[] v, int[] w) {
			this.n = n;
			this.W = W;
			this.v = v;
			this.w = w;
			solve();
		}

		private void solve() {
			//TODO: implement this
		}

		public int getOpt() {
			return opt;
		}

		public int[] getVs() {
			return vs;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			int t = sc.nextInt();

			for (int j = 0; j < t; j++) {
				int n = sc.nextInt();
				int W = sc.nextInt();

				int[] v = new int[n];
				int[] w = new int[n];

				for (int i = 0; i < n; i++) {
					v[i] = sc.nextInt();
				}
				for (int i = 0; i < n; i++) {
					w[i] = sc.nextInt();
				}

				Knapsack k = new Knapsack(n, W, v, w);
				System.out.print(k.getOpt());
				for (int i : k.getVs()) {
					System.out.print(" " + i);
				}
				System.out.println();
			}

		} finally {
			sc.close();
		}
	}

}