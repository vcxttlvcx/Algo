import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 백준 18513 : 샘터
 * 
 * @author Seok
 */
public class BOJ18513_샘터 {

	private static int N;
	private static int K;
	private static Set<Integer> check;
	private static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 샘터의 개수
		K = Integer.parseInt(st.nextToken());	// 집의 개수
		
		check = new HashSet<Integer>();
		
		q = new LinkedList<int[]>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int pos = Integer.parseInt(st.nextToken());
			// 샘터의 위치를 set에 입력
			// queue에 현재 샘터의 위치와 처음 집을 지을 거리인 1을 삽입
			check.add(pos);
			q.add(new int[] {pos, pos});
		}
		
		long ans = bfs();	// 불행도의 합
		
		System.out.println(ans);
	}
	
	public static long bfs() {
		long result = 0;
		
		int[] d = {-1, 1};
		
		while(!q.isEmpty()) {
			int pos = q.peek()[0];
			int water = q.peek()[1];
			q.poll();
			
			for(int i = 0; i < 2; i++) {
				int nextPos = pos + d[i];
				// 이미 지어진 위치라면 무시
				if(check.contains(nextPos))
					continue;
				// 남은 집의 개수 감소
				K--;
				// 거리 계산하여 더하기
				result += Math.abs(nextPos - water);
				// 남은 집의 개수가 없다면 return
				if(K == 0)
					return result;
				// 지은 집의 위치에 추가한 후, queue에 추가
				check.add(nextPos);
				q.add(new int[] {nextPos, water});
			}
		}
		
		return result;
	}
}
