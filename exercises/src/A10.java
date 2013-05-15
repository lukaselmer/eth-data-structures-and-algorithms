import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class A10 {

	// Implementation algorithm from http://en.wikipedia.org/wiki/Edmonds%E2%80%93Karp_algorithm
	
	private static class Ret {

		public int m;
		public int[] P;

		public Ret(int m, int[] p) {
			this.m = m;
			this.P = p;
		}
	}

	private static class Graph {
		private int n;
		private int f = 0;
		private int[][] C;
		private int[][] F;
		private LinkedList<Integer>[] E;

		public Graph(int n, int m) {
			this.n = n;
			E = new LinkedList[n];
			F = new int[n][];
			C = new int[n][];
			for (int i = 0; i < n; i++) {
				E[i] = new LinkedList<Integer>();
				F[i] = new int[n];
				C[i] = new int[n];
			}
		}

		public void addEdge(int index, int u, int v, int c) {
			C[u][v] = c;
			E[u].add(v);
		}

		public int CalculateMaxFlow() {
			while (true) {
				Ret ret = BreadthFirstSearch();
				int m = ret.m;
				int[] P = ret.P;

				if (ret.m == 0)
					break;

				f = f + m;

				int v = n - 1;
				while (v != 0) {
					int u = P[v];
					F[u][v] = F[u][v] + m;
					F[v][u] = F[v][u] - m;
					v = u;
				}
			}

			return f;
		}

		private Ret BreadthFirstSearch() {
			int[] P = new int[n];
			int[] M = new int[n];
			Arrays.fill(P, -1);
			P[0] = -2;
			M[0] = Integer.MAX_VALUE;
			Queue<Integer> Q = new ArrayDeque<Integer>();
			Q.add(0);
			while (Q.size() > 0) {
				int u = Q.remove();
				for (Integer v : E[u]) {
					if (C[u][v] - F[u][v] > 0 && P[v] == -1) {
						P[v] = u;
						M[v] = Math.min(M[u], C[u][v] - F[u][v]);
						if (v != n - 1) {
							Q.add(v);
						} else {
							return new Ret(M[n - 1], P);
						}
					}
				}
			}
			return new Ret(0, P);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			int lines = sc.nextInt();

			for (int j = 0; j < lines; j++) {
				int n = sc.nextInt();
				int m = sc.nextInt();

				Graph g = new Graph(n, m);

				for (int k = 0; k < m; k++) {
					int u = sc.nextInt() - 1;
					int v = sc.nextInt() - 1;
					int c = sc.nextInt();
					g.addEdge(k, u, v, c);
				}

				System.out.println(g.CalculateMaxFlow());

			}

		} finally {
			sc.close();
		}
	}

}