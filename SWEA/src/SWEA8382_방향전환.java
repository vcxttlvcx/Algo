import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 8382 : 방향 전환(D4)
 * 
 * (x1, y1 )에서 (x2, y2)로 이동하려고 한다. (x, y)에서 한 번 이동하면 (x + 1, y), (x - 1, y), (x,
 * y + 1), (x, y - 1)로 이동할 수 있다. 이 중에서, (x + 1, y), (x - 1, y)로 이동하는 것은 가로 이동,
 * (x, y + 1), (x , y - 1)로 이동하는 것은 세로 이동이라고 한다. 정우는 그냥 (x1, y1)에서 (x2, y2)로
 * 이동하는 것은 재미가 없다고 생각한다. 그래서 이전 이동이 가로 이동이었다면, 이번에는 세로 이동으로 이동하고, 이전 이동이 세로
 * 이동이었다면, 이번에는 가로 이동으로 이동하여 (x1, y1)에서 (x2, y2)로 이동하려고 한다. 가장 첫 이동은 어떤 이동 이어도
 * 상관 없다. 이 때, 최소 몇 번의 이동을 해야 (x1, y1)에서 (x2, y2)로 이동할 수 있는지 구하는 프로그램을 작성하라.
 */
public class SWEA8382_방향전환 {
	private static int x1;
	private static int y1;
	private static int x2;
	private static int y2;

	private static boolean[][][] visited;

	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 시작 지점 (x1, y1), 도착 지점 (x2, y2) 초기화
			x1 = Integer.parseInt(st.nextToken()) + 100;
			y1 = Integer.parseInt(st.nextToken()) + 100;
			x2 = Integer.parseInt(st.nextToken()) + 100;
			y2 = Integer.parseInt(st.nextToken()) + 100;
			// 맵의 모든 부분과 가로로 
			visited = new boolean[201][201][2];

			Queue<Point> queue = new LinkedList<Point>();
			queue.add(new Point(x1, y1, 0));		// 가로이동으로 도착
			queue.add(new Point(x1, y1, 1));	// 세로이동으로 도착
			visited[x1][y1][0] = true;
			visited[x1][y1][1] = true;
			// 도착점 까지 가지 위하여 bfs 탐색
			int cnt = -1;
			while(!queue.isEmpty()) {
				int size = queue.size();
				cnt++;
				// 현재 사이즈 만큼 진행하여 이동회수 카운트
				for(int i = 0; i < size; i++) {
					Point p = queue.poll();
					
					if(p.r == x2 && p.c == y2) {
						queue.clear();
						break;
					}
					
					for(int d = 0; d < 4; d++) {
						// 앞에서 가로이동으로 왔을 경우 가로 이동이면 안됨
						if(p.dir == 0 && (d == 0 || d == 1))
							continue;
						// 앞에서 세로이동으로 왔을 경우 세로 이동이면 안됨
						if(p.dir == 1 && (d == 2 || d == 3))
							continue;
						int nr = p.r + dr[d];
						int nc = p.c + dc[d];
						if(nr < 0 || nc < 0 || nr > 200 || nc > 200 || visited[nr][nc][(p.dir + 1) % 2])
							continue;
						
						visited[nr][nc][(p.dir + 1) % 2] = true;
						queue.add(new Point(nr, nc, (p.dir + 1) % 2));
					}
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}

		br.close();
	}

	static class Point {
		int r, c;
		int dir;
		
		public Point(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
		
	}
}
