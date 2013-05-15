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
		private int[][] capacity;
		private int[][] currentFlow;
		private LinkedList<Integer>[] neighbours;
		private int m;
		private int[] parentsOfCurrentPath;
		private int[] capacityOfCurrentPath;

		public Graph(int n, int m) {
			this.n = n;
			neighbours = new LinkedList[n];
			currentFlow = new int[n][];
			capacity = new int[n][];
			parentsOfCurrentPath = new int[n];
			capacityOfCurrentPath = new int[n];
			
			for (int i = 0; i < n; i++) {
				neighbours[i] = new LinkedList<Integer>();
				currentFlow[i] = new int[n];
				capacity[i] = new int[n];
			}
		}

		public void addEdge(int index, int u, int v, int c) {
			capacity[u][v] = c;
			neighbours[u].add(v);
		}

		public int CalculateMaxFlow() {
			while (true) {
				BreadthFirstSearch();

				if (m == 0)
					break;

				f += m;

				int v = n - 1;
				while (v != 0) {
					int u = parentsOfCurrentPath[v];
					currentFlow[u][v] = currentFlow[u][v] + m;
					currentFlow[v][u] = currentFlow[v][u] - m;
					v = u;
				}
			}
			return f;
		}

		private void BreadthFirstSearch() {
			Arrays.fill(capacityOfCurrentPath, 0);
			Arrays.fill(parentsOfCurrentPath, -1);
			parentsOfCurrentPath[0] = -2;
			capacityOfCurrentPath[0] = Integer.MAX_VALUE;
			Queue<Integer> Q = new ArrayDeque<Integer>();
			Q.add(0);
			while (Q.size() > 0) {
				int u = Q.remove();
				for (Integer v : neighbours[u]) {
					if (capacity[u][v] - currentFlow[u][v] > 0 && parentsOfCurrentPath[v] == -1) {
						parentsOfCurrentPath[v] = u;
						capacityOfCurrentPath[v] = Math.min(capacityOfCurrentPath[u], capacity[u][v] - currentFlow[u][v]);
						if (v != n - 1) {
							Q.add(v);
						} else {
							m = capacityOfCurrentPath[n - 1];
							return;
						}
					}
				}
			}
			m = 0;
			return;
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