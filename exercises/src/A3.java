import java.util.Arrays;
import java.util.Scanner;

class A3 {

	static class MinHeap {

		private int[] array;
		private int elementCount;

		public MinHeap(int i) {
			array = new int[i + 1];
			Arrays.fill(array, Integer.MIN_VALUE);
			elementCount = 0;
		}

		public int insert(int el) {
			array[elementCount++] = el;
			restoreSorted(elementCount - 1);

			return queryLast();
		}

		private void restoreSorted(int childIndex) {
			if (childIndex == 0)
				return;
			int child = array[childIndex];
			int parentIndex = (childIndex - 1) / 2;
			int parent = array[parentIndex];
			if (child > parent)
				return;
			swap(childIndex, parentIndex);
			if (parentIndex != 0)
				restoreSorted(parentIndex);
		}

		private void swap(int a, int b) {
			int tmp = array[a];
			array[a] = array[b];
			array[b] = tmp;
		}

		private int queryLast() {
			return array[elementCount - 1];
		}

		public boolean isEmpty() {
			return elementCount == 0;
		}

		public int size() {
			return elementCount;
		}

		public int extractMin() {
			int out = array[0];
			array[0] = array[--elementCount];
			array[elementCount] = 0; // just for debugging
			bubbleDown(0);
			return out;
		}

		private void bubbleDown(int i) {
			int childIndexA = (i * 2) + 1;
			int childIndexB = childIndexA + 1;

			// Has no children
			if (childIndexA >= elementCount) {
				return;
			}

			// Has only one child (at childIndexA)
			if (childIndexB >= elementCount) {
				if (array[i] > array[childIndexA])
					swap(i, childIndexA);
				return;
			}

			int iMin = array[childIndexA] < array[childIndexB] ? childIndexA : childIndexB;
			if (array[i] < array[iMin])
				return;
			swap(i, iMin);
			bubbleDown(iMin);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {

			int lines = sc.nextInt();

			for (int j = 0; j < lines; ++j) {
				int i = sc.nextInt();

				MinHeap h = new MinHeap(i);
				for (int k = 0; k < i; ++k) {
					h.insert(sc.nextInt());
					System.out.print(h.queryLast());

					if (k < (i - 1))
						System.out.print(" ");
				}
				System.out.println();

				while (!h.isEmpty()) {
					System.out.print(h.extractMin());
					if (h.size() > 0)
						System.out.print(" ");
				}
				System.out.println();
			}

		} finally {
			sc.close();
		}
	}
}
