import java.util.Scanner;

class A2 {

	public static void main(String[] strings) {
		Scanner sc = new Scanner(System.in);

		try {

			int lines = sc.nextInt();

			for (int j = 0; j < lines; ++j) {
				int i = sc.nextInt();

				int[] nums = new int[i];
				for (int k = 0; k < i; ++k) {
					nums[k] = sc.nextInt();
				}
				int out = maxSubarray(nums);
				System.out.println(out);
			}

		} finally {
			sc.close();
		}
	}

	private static int maxSubarray(int[] a) {
		int max_so_far = 0;
		int max_ending_here = 0;
		int startIndex = 0;
		int endIndex = -1;

		for (int i = 0; i < a.length; i++) {
			if (0 > max_ending_here + a[i]) {
				startIndex = i + 1;
				max_ending_here = 0;
			} else {
				max_ending_here += a[i];
			}

			if (max_ending_here > max_so_far) {
				max_so_far = max_ending_here;
				endIndex = i;
			}
		}
		return max_so_far;

		/*if (startIndex <= endIndex) {
			return max_so_far;
			/*int sum = 0;
			for(int i = startIndex; i<=endIndex; ++i){
				sum += a[i];
			}* /
			//return Arrays.copyOfRange(a, startIndex, endIndex);
		}

		return max_so_far;*/

	}
}
