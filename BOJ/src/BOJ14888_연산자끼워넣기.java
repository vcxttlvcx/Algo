import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 백준 14888 : 연산자 끼워넣기
 * 
 * @author Seok
 *
 */
public class BOJ14888_연산자끼워넣기 {
	private static int N;
	private static int max;
	private static int min;
	
	private static int[] numbers;
	private static int[] opNums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		// N개의 수로 이루어진 수열 입력
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		// 합이 N - 1이 되는 연산자의 개수 입력
		opNums = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			
			opNums[i] = Integer.parseInt(st.nextToken());
		}
		// 최소 -10억에서 최대 10억까지
		max = -1000000000;
		min = 1000000000;
		// +, -, *, /를 순서대로 넣어보며 결과를 계산한다
		dfs(1, numbers[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	/**
	 * 연산자를 순서대로 넣어가며 DFS를 진행
	 * 
	 * @param numIdx : 현재 연산에 사용 될 수가 저장되어 있는 index
	 * @param result : 연산 결과를 저장해서 들고 다님
	 */
	public static void dfs(int numIdx, int result) {
		if(numIdx >= N) {
			max = result > max ? result : max;
			min = result < min ? result : min;
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(opNums[i] <= 0)
				continue;
			// 연산자의 개수 감소
			opNums[i]--;
			// i에 따라 연산자 다르게 처리
			switch(i) {
			case 0:
				dfs(numIdx + 1, result + numbers[numIdx]);
				break;
			case 1:
				dfs(numIdx + 1, result - numbers[numIdx]);
				break;
			case 2:
				dfs(numIdx + 1, result * numbers[numIdx]);
				break;
			case 3:
				dfs(numIdx + 1, result / numbers[numIdx]);
				break;
			}
			// 다른 가지에서 탐색을 위해 연산자 개수 복구
			opNums[i]++;
		}
	}
}
