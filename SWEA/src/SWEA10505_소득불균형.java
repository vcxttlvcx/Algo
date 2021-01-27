import java.util.Scanner;

/**
 * 통계 자료를 처리할 때, 평균이 전체 집단의 특징을 꼭 잘 표현하는 것은 아니다.
 * 예를 들어, 대다수의 국가에서는 적은 수의 사람이 국가 전체 소득의 꽤 많은 부분을 차지하기 때문에, 해당 국가의 평균 소득은 보통 사람들의 소득보다 높은 경우가 많다.
 * 당신은, n명의 사람의 소득이 주어졌을 때 이 중 평균 이하의 소득을 가진 사람들의 수를 출력해야 한다.
 */
public class SWEA10505_소득불균형 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			
			long[] income = new long[N];
			
			long sum = 0;
			
			for(int i = 0; i < N; i++) {
				income[i] = sc.nextLong();
				sum += income[i];
			}
			
			double avg = (double) sum / N;
			
			int ans = 0;
			for(int i = 0; i < N; i++) {
				if(income[i] <= avg)
					ans++;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
