import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class A6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			int lines = sc.nextInt();

			for (int j = 0; j < lines; j++) {
				int n = sc.nextInt();

				int[] array = new int[n];

				for (int i = 0; i < n; i++) {
					int x = sc.nextInt();
					array[i] = x;
				}

				int m = select(array, 0, array.length - 1, medianPos(array.length), System.out);
				System.out.println(array[m]);
			}

		} finally {
			sc.close();
		}
	}

	public static int select(int[] array, int l, int r, int i, PrintStream out) {
		int v = selectPivot(array, l, r, out);

		if (out != null)
			out.print(array[v] + " ");

		if (l >= r)
			return l;

		int m = split(array, l, r, v);

		if (i == m - l)
			return m;
		else if (i > m - l)
			return select(array, m + 1, r, i - (m + 1 - l), null);
		else
			return select(array, l, m - 1, i, null);
	}

	public static int selectPivot(int[] a, int l, int r, PrintStream out) {
		int[] medians = medians(a, l, r);

		if (out != null)
			for (int i = 0; i < medians.length; i++)
				out.print(medians[i] + " ");

		if (medians.length <= 5)
			return index(a, l, r, median(medians));

		int pivot = medians[select(medians, 0, medians.length - 1, medianPos(medians.length), null)];
		return index(a, l, r, pivot);
	}

	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static int medianPos(int length) {
		return ((length + 1) / 2) - 1;
	}

	public static int median(int[] array) {
		Arrays.sort(array);
		return array[medianPos(array.length)];
	}

	public static int index(int[] array, int l, int r, int v) {
		for (int i = l; i <= r; i++)
			if (array[i] == v)
				return i;

		return -1;
	}

	public static int split(int[] array, int l, int r, int v) {
		int p = array[v];
		int s = l;

		swap(array, v, r - 1);
		for (int i = l; i <= r; i++) {
			if (array[i] < p) {
				swap(array, i, s);
				s++;
			}
		}

		swap(array, s, r - 1);
		return s;
	}

	public static int[] medians(int[] a, int l, int r) {
		int[] medians = new int[(int) Math.ceil((r - l + 1) / 5.0)];
		for (int i = l; i <= r; i = i + 5) {
			medians[(i - l) / 5] = median(Arrays.copyOfRange(a, i, Math.min(i + 5, r + 1)));
		}
		return medians;
	}
}