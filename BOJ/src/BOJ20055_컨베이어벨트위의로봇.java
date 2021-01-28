import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 20055 : 컨베이어 벨트 위의 로봇 
 * 
 * @author Seok
 */
public class BOJ20055_컨베이어벨트위의로봇 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 컨베이어 벨트의 길이
		int K = Integer.parseInt(st.nextToken());	// 내구도가 0인 칸의 개수, 기저 조건
		
		int[] convey = new int[2 * N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2 * N; i++) {
			convey[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[] onRobot = new boolean[2 * N];
		
		int cnt = 0;	// 내구도가 0인 칸을 계산하기 위한 변수
		
		int upPosition = 0;	// 현재 상황에서 컨베이어 벨트의 "올라가는 위치"
		int downPosition = N - 1;	// 현재 상황에서 컨베이어 벨트의 "내려가는 위치"
		
		int step = 0;	// 반복 횟수
		
		while(cnt < K) {
			step++;
			// 1. 컨베이어 벨트가 회전한다
			if(upPosition <= 0)
				upPosition = 2 * N - 1;
			else
				upPosition--;
			if(downPosition == 0)
				downPosition = 2 * N - 1;
			else
				downPosition--;
			// 2. 가장 먼저 벨트에 올라간 로봇 부터, 벨트가 회전하는 방향으로 이동할 수 있다면 이동, 이동할 수 없다면 가만히 있는다
			// 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다
			onRobot[downPosition] = false;	// 내려가는 위치에 있는 로봇을 반드시 내려가야 한다
			// 내려가는 위치 바로 앞부터 로봇 이동 시작
			for(int i = N - 1; i > 1; i--) {
				int nowPosition = ((upPosition + i - 1) % (2 * N));	// 다음 이동할 위치
				int nextPosition = ((nowPosition + 1) % (2 * N));
				// 현재 위치에 로봇이 있으며, 이동할 위치에 로봇이 없고, 내구도가 남아 있다면 이동
				if(onRobot[nowPosition] && !onRobot[nextPosition] && convey[nextPosition] > 0) {
					onRobot[nowPosition] = false;
					onRobot[nextPosition] = true;
					convey[nextPosition]--;
					
					if(convey[nextPosition] == 0)
						cnt++;
				}
			}
			onRobot[downPosition] = false;	// 내려가는 위치에 있는 로봇은 반드시 내려가야 한다
			// 3. 올라가는 위치에 로봇이 없다면 로봇을 하나 올린다.
			// "올라가는 위치"에 로봇이 없고, 내구도가 남아있다면 로봇을 하나 올린다
			if(!onRobot[upPosition] && convey[upPosition] > 0) {
				onRobot[upPosition] = true;
				convey[upPosition]--;
				
				if(convey[upPosition] == 0)
					cnt++;
			}
		}
		
		System.out.println(step);
	}
}
