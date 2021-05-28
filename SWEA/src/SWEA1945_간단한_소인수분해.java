import java.util.Scanner;

public class SWEA1945_간단한_소인수분해 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int a, b, c, d, e;

			a = b = c = d = e = 0;

			while (N != 1) {
				if (N % 2 == 0) {
					a++;
					N /= 2;
				}
				if (N % 3 == 0) {
					b++;
					N /= 3;
				}
				if (N % 5 == 0) {
					c++;
					N /= 5;
				}
				if (N % 7 == 0) {
					d++;
					N /= 7;
				}
				if (N % 11 == 0) {
					e++;
					N /= 11;
				}
			}

			System.out.format("#%d %d %d %d %d %d\n", test_case, a, b, c, d, e);
		}

		sc.close();
	}

}
