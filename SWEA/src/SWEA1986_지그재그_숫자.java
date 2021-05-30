import java.util.Scanner;

class SWEA1986_지그재그_숫자 {
	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T;
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int answer = 0;

			for (int i = 1; i <= N; i++) {
				if (i % 2 == 1)
					answer += i;
				else
					answer -= i;
			}

			System.out.format("#%d %d \n", test_case, answer);
		}

		sc.close();
	}
}