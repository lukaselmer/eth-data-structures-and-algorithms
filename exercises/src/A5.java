import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class A5 {

	private static class Node {
		private final int value;
		private Node left, right;

		public Node(int value) {
			this.value = value;
		}

		public void insert(int new_value) {
			if (should_insert_left(new_value)) {
				if (left == null)
					left = new Node(new_value);
				else
					left.insert(new_value);
			} else {
				if (right == null)
					right = new Node(new_value);
				else
					right.insert(new_value);
			}
		}

		public int visit() {
			return visit(0);
		}

		public int visit(int depth) {
			// pre_order();
			int depth_left = depth, depth_right = depth;
			if (left != null)
				depth_left = left.visit(depth + 1);
			// in_order();
			if (right != null)
				depth_right = right.visit(depth + 1);
			
			post_order(depth, depth_left, depth_right);
			
			return Math.max(depth_left, depth_right);
		}

		private void post_order(int depth, int depth_left, int depth_right) {
			System.out.print(value + " " + depth_left + " " + depth_right);
			if(depth > 0) System.out.print(" ");
		}

		private boolean should_insert_left(int new_value) {
			return new_value <= value;
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
