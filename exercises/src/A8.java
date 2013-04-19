import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class A8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			int lines = sc.nextInt();

			for (int j = 0; j < lines; j++) {
				char[] A = sc.next().toCharArray();
				char[] B = sc.next().toCharArray();

				longestSubstring(A, B);
			}

		} finally {
			sc.close();
		}
	}

	private static void longestSubstring(char[] a, char[] b) {
		int[][] matrix = new int[a.length + 1][];
		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = new int[b.length + 1];
		}

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[i].length; j++) {
				fillField(matrix, i, j, a, b);
			}
		}

		System.out.print(matrix[a.length][b.length] + " ");

		char[] solution = new char[matrix[a.length][b.length]];
		backtrack(matrix, a, b, a.length, b.length, solution, solution.length - 1);

		System.out.print(solution);
		
		System.out.println();
	}

	private static void backtrack(int[][] matrix, char[] a, char[] b, int n, int m, char[] solution, int k) {
		if (k == -1 || n == -1 || m == -1)
			return;

		if (a[n-1] == b[m-1]) {
			solution[k] = a[n-1];
			backtrack(matrix, a, b, n - 1, m - 1, solution, k - 1);
		} else {
			if (matrix[n][m] == matrix[n - 1][m])
				backtrack(matrix, a, b, n - 1, m, solution, k);
			else
				backtrack(matrix, a, b, n, m - 1, solution, k);
		}
	}

	private static void fillField(int[][] matrix, int i, int j, char[] a, char[] b) {
		int res = 0;
		if (a[i - 1] == b[j - 1])
			res = matrix[i - 1][j - 1] + 1;
		else
			res = Math.max(matrix[i - 1][j], matrix[i][j - 1]);

		matrix[i][j] = res;
	}

}