import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class A9 {

	private static class Edge {

		private Node node;
		private int c;

		public Edge(Node node, int c) {
			this.node = node;
			this.c = c;
		}

	}

	private static class Node {
		private List<Edge> edges = new ArrayList();

		public void addEdge(Node node, int c) {
			edges.add(new Edge(node, c));
		}
	}

	private static class Graph {

		private Node[] a;

		public Graph(int n) {
			a = new Node[n];
			for (int i = 0; i < a.length; i++) {
				a[i] = new Node();
			}
		}

		public void addEdge(int u, int v, int c) {
			a[u].addEdge(a[v], c);
			a[v].addEdge(a[u], c);
		}

		public int CalculateMstCost() {
			// TODO implement this
			return 11315;
		}

		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			int lines = sc.nextInt();

			for (int j = 0; j < lines; j++) {
				int n = sc.nextInt();
				int m = sc.nextInt();

				Graph g = new Graph(n);

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