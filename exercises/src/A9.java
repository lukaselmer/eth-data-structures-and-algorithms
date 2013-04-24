import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

class A9 {

	private static class Edge {

		private int u, v;
		protected Integer weight;

		public Edge(int u, int v, int c) {
			this.u = u;
			this.v = v;
			weight = c;
		}

	}

	private static class Node {
		private List<Edge> edges = new ArrayList();

	}

	private static class Graph {

		private List<Node> nodes;
		private List<Graph> trees;
		private SortedSet<Edge> edges;

		public Graph(int n, int m) {
			nodes = new ArrayList<Node>(n);

			Comparator<Edge> cmp = new Comparator<Edge>() {
				@Override
				public int compare(Edge a, Edge b) {
					return a.weight.compareTo(b.weight);
				}
			};

			edges = new TreeSet<Edge>(cmp);
			for (int i = 0; i < n; i++) {
				nodes.add(new Node());
			}
		}

		public Graph(Node node) {
			nodes = new ArrayList<Node>();
			nodes.add(node);
		}

		public void addEdge(int u, int v, int c) {
			edges.add(new Edge(u, v, c));
		}

		public int CalculateMstCost() {
			trees = new ArrayList<Graph>(nodes.size());
			for (int i = 0; i < nodes.size(); i++) {
				trees.add(new Graph(nodes.get(i)));
			}
			for (Edge e : edges) {

			}

			// foreach (u, v) ordered by weight(u, v), increasing:
			// if FIND-SET(u) ≠ FIND-SET(v):
			// A = A ∪ {(u, v)}
			// UNION(u, v)

			// TODO implement this
			return 0;
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
					g.addEdge(u, v, c);
				}

				System.out.println(g.CalculateMstCost());

			}

		} finally {
			sc.close();
		}
	}

}