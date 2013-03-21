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
			// pre_order();
			int depth_left = -1, depth_right = -1;
			if (left != null)
				depth_left = left.visit();
			// in_order();
			if (right != null)
				depth_right = right.visit();

			int depth = Math.max(depth_left, depth_right) + 1;
			post_order(depth + 1, depth_left + 1, depth_right + 1);

			return depth;
		}

		private void post_order(int depth, int depth_left, int depth_right) {
			System.out.print(value + " " + depth_left + " " + depth_right + " ");
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
				int n = sc.nextInt();

				Node tree = new Node(sc.nextInt());
				for (int k = 0; k < n - 1; ++k) {
					tree.insert(sc.nextInt());
				}
				tree.visit();
				System.out.println();
			}

		} finally {
			sc.close();
		}
	}
}
