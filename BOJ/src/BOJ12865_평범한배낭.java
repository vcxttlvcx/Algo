import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12865 : 평범한 배낭 이 문제는 아주 평범한 배낭에 관한 문제이다. 한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고
 * 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다. 준서가 여행에
 * 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수
 * 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을
 * 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.
 */
public class BOJ12865_평범한배낭 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] weights = new int[N];
		int[] values = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}
		// 메모리제이션 배열
		int[] dp = new int[K + 1];
		
		for (int i = 0; i < N; i++) {
			for (int w = K; w >= weights[i]; w--) {
				if (dp[w] < values[i] + dp[w - weights[i]])
					dp[w] = values[i] + dp[w - weights[i]];
			}
		}

		System.out.println(dp[K]);
	}

}
