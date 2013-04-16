import java.awt.List;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class A7 {

	private static class Knapsack {

		private int n, W;
		private int[] v, w;
		ArrayList<Integer> vs;
		private int[][] matrix;

		public Knapsack(int n, int W, int[] v, int[] w) {
			this.n = n;
			this.W = W;
			this.v = v;
			this.w = w;
			solve();
		}

		private void solve() {
			matrix = new int[n + 1][];
			for (int i = 0; i < matrix.length; i++) {
				matrix[i] = new int[W + 1];
			}

			for (int i = 0; i < W; i++) {
				matrix[0][i] = 0;
			}

			for (int i = 1; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					if (j >= w[i - 1]) {
						matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - w[i - 1]] + v[i - 1]);
					} else {
						matrix[i][j] = matrix[i - 1][j];
					}
				}
			}

			vs = new ArrayList<Integer>();
			int cn = n;
			int cw = W;
			while (cn > 0) {
				if (w[cn - 1] <= cw && matrix[cn][cw] == matrix[cn - 1][cw - w[cn - 1]] + v[cn - 1]) {
					vs.add(cn);
					cw = cw - w[cn - 1];
				}
				cn = cn - 1;
			}
		}

		public int getOpt() {
			return matrix[n][W];
		}

		public int[] getVs() {
			int[] arr = new int[vs.size()];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = vs.get(i);
			}
			Arrays.sort(arr);
			return arr;
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