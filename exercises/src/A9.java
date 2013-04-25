import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

class A9 {

	private static class Edge {

		private int u, v;
		protected int weight;

		public Edge(int u, int v, int c) {
			this.u = u;
			this.v = v;
			weight = c;
		}

		@Override
		public String toString() {
			return "[u:" + (u + 1) + ",v:" + (v + 1) + ",weight:" + weight + "]";
		}
	}

	private static class Graph {
		private int[] sets;
		private Edge[] edges;
		private LinkedList<Edge> chosenEdges = new LinkedList<Edge>();
		
		private Comparator<Edge> cmp = new Comparator<Edge>() {
			@Override
			public int compare(Edge a, Edge b) {
				return a.weight - b.weight;
			}
		};

		public Graph(int n, int m) {
			sets = new int[n];
			for (int i = 0; i < n; i++) {
				sets[i] = i;
			}

			edges = new Edge[m];
		}

		public void addEdge(int index, int u, int v, int c) {
			edges[index] = new Edge(u, v, c);
		}

		public int CalculateMstCost() {
			Arrays.sort(edges, cmp);
			
			for (Edge e : edges) {
				if (!isInSameSet(e.u, e.v)) {
					chosenEdges.add(e);
					mergeSets(e.u, e.v);
				}
			}

			int sum = 0;
			for (Edge e : chosenEdges) {
				sum += e.weight;
			}

			return sum;
		}

		private void mergeSets(int u, int v) {
			int toReplace = sets[v];
			for (int i = 0; i < sets.length; i++) {
				if (sets[i] == toReplace)
					sets[i] = sets[u];
			}
		}

		private boolean isInSameSet(int u, int v) {
			return sets[u] == sets[v];
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

				System.out.println(g.CalculateMstCost());

			}

		} finally {
			sc.close();
		}
	}

}