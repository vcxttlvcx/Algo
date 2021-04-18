import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 사악한 암흑의 군주 이민혁은 드디어 마법 구슬을 손에 넣었고, 그 능력을 실험해보기 위해 근처의 티떱숲에 홍수를 일으키려고 한다. 이
 * 숲에는 고슴도치가 한 마리 살고 있다. 고슴도치는 제일 친한 친구인 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다. 티떱숲의 지도는
 * R행 C열로 이루어져 있다. 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은
 * 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다. 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다.
 * (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다. 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은
 * 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할
 * 수 없다. 티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면
 * 고슴도치가 물에 빠지기 때문이다.
 * 
 * @author Seok
 */
class Point3055 {
	int r;
	int c;

	public Point3055() {
	}

	public Point3055(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class BOJ3055_탈출 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 상 하 좌 우
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		// R, C 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		// 물의 위치를 저장한 큐와 고슴도치의 현재 위치를 저장할 큐, 고슴도치가 방문한 위치와 물이 차있는 위치를 기록한 배열 선언 및 초기화
		Queue<Point3055> water = new LinkedList<Point3055>();
		Queue<Point3055> hedgehog = new LinkedList<Point3055>();
		boolean[][] visited = new boolean[R][C];
		// 지도 정보 받기
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			map[i] = temp.toCharArray();

			for (int j = 0; j < C; j++) {
				if (map[i][j] == '.')
					continue;
				else if (map[i][j] == '*')// 물이 있는 위치 모두 큐에 삽입
					water.add(new Point3055(i, j));
				else if (map[i][j] == 'S') { // 고슴도치의 위치 큐에 삽입 및 방문 처리
					hedgehog.add(new Point3055(i, j));
					visited[i][j] = true;
				}
			}
		}
		// bfs를 통해 탐색
		int time = 0;
		boolean success = false;
		while (!hedgehog.isEmpty()) {
			// 물을 먼저 움직여서 비버가 갈 수 없는 위치를 먼저 기록한다
			int forSize = water.size(); // 현재 사이즈 만큼만 반복
			// 4방향 이동하며 확인
			for (int i = 0; i < forSize; i++) {
				Point3055 w = water.poll();
				for (int d = 0; d < 4; d++) {
					int nr = w.r + dr[d];
					int nc = w.c + dc[d];
					// 범위 밖이거나, 돌이거나, 비버의 굴이거나, 이미 물이 차있는 경우 skip
					if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 'X' || map[nr][nc] == 'D'
							|| map[nr][nc] == '*')
						continue;
					// 다음 위치에 물이라는 것을 표시하고 큐에 삽입
					map[nr][nc] = '*';
					water.add(new Point3055(nr, nc));
				}
			}
			// 고슴도치 이동 가능한 위치 모두 이동
			forSize = hedgehog.size();
			for (int i = 0; i < forSize; i++) {
				// 목적지에 도착했을 경우 반복문 종료
				if (success)
					break;
				Point3055 h = hedgehog.poll();
				// 4방향 이동하며 확인
				for (int d = 0; d < 4; d++) {
					int nr = h.r + dr[d];
					int nc = h.c + dc[d];
					// 범위를 벗어나거나, 돌이거나, 물이 있거나, 이미 고슴도치가 방문했던 위치일 경우 skip
					if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 'X' || map[nr][nc] == '*'
							|| visited[nr][nc])
						continue;
					// 목적지에 도착한 경우 큐를 비우고 성공 체크를 한다
					if (map[nr][nc] == 'D') {
						success = true;
						hedgehog.clear();
						break;
					}
					// 방문 체크 및 큐에 삽입
					visited[nr][nc] = true;
					hedgehog.add(new Point3055(nr, nc));
				}
			}

			time++;
		}
		// 성공 했을 경우 시간 출력, 실패 했을 경우 KAKTUS 출력
		if (success)
			System.out.println(time);
		else
			System.out.println("KAKTUS");

	}

}
