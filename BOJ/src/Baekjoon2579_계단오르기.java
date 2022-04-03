import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon2579_계단오르기 {
	public static int N;
	public static int[] stairs;
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		stairs = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[N + 1];

		dp[0] = 0;
		dp[1] = stairs[1];
		if (N >= 2)
			dp[2] = Math.max(stairs[1] + stairs[2], stairs[1]);

		for (int i = 3; i <= N; i++) {
			// OXOO인 경우와 OXO인 경우 중 큰 값을 선택해 나간다
			dp[i] = Math.max(dp[i - 3] + stairs[i - 1] + stairs[i], dp[i - 2] + stairs[i]);
		}

		System.out.println(dp[N]);
	}

}
