import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14501 : 퇴사 (삼성 SW 역량테스트 기출 문제)
 * 
 * @author Seok
 */
public class BOJ14501_퇴사 {

	private static int N;
	private static int[] period;
	private static int[] profit;
	private static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		period = new int[N + 1];
		profit = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			period[i] = Integer.parseInt(st.nextToken());
			profit[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 2];
		// 마지막 날 부터 동적계획법 진행
		for (int i = N; i >= 1; i--) {
			// 현재 상담을 진행한다면 끝나는 날
			int endDay = i + period[i];
			// 끝나는 날이 퇴사날 보다 크다면
			if (endDay > N + 1)
				dp[i] = dp[i + 1];
			else	// 현재 이익과 이 일이 끝나는 날까지 계산해둔 이익과 이날 일을 하지 않을 경우 다음날 이익을 비교한다
				dp[i] = Math.max(dp[i + 1], dp[endDay] + profit[i]);
		}

		System.out.println(dp[1]);
	}
}
