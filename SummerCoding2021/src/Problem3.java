import java.util.LinkedList;
import java.util.Queue;

public class Problem3 {
	
	static public boolean checkRange(int r, int c, int n) {
		if(r < 0 || c < 0 || r >= n || c >= n)
			return false;
		return true;
	}

	static public int magic(int[][] maps, int row, int col, int p, int range) {
		int cnt = 0;
		int n = maps.length;

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] isVisited = new boolean[n][n];

		if (checkRange(row, col, n)) {
			q.add(new int[] { row, col, 1 });
			isVisited[row][col] = true;
		}
		if (checkRange(row - 1, col, n)) {
			q.add(new int[] { row - 1, col, 1 });
			isVisited[row - 1][col] = true;
		}
		if (checkRange(row, col - 1, n)) {
			q.add(new int[] { row, col - 1, 1 });
			isVisited[row][col - 1] = true;
		}
		if (checkRange(row - 1, col - 1, n)) {
			q.add(new int[] { row - 1, col - 1, 1 });
			isVisited[row - 1][col - 1] = true;
		}

		while (!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.peek()[1];
			int dist = q.peek()[2];
			q.poll();

			if (dist < range / 2 && maps[r][c] <= p) {
				cnt++;
			} else if (dist == range / 2 && maps[r][c] <= p / 2) {
				cnt++;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!checkRange(nr, nc, n) || isVisited[nr][nc] || dist + 1 > range / 2)
					continue;

				isVisited[nr][nc] = true;
				q.add(new int[] { nr, nc, dist + 1 });
			}
		}

		return cnt;
	}

	static public int solution(int[][] maps, int p, int r) {
		int answer = 0;

		int n = maps.length;

		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= n; j++) {
				int cnt = magic(maps, i, j, p, r);
				answer = cnt > answer ? cnt : answer;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int[][] maps = { { 1, 28, 41, 22, 25, 79, 4 }, { 39, 20, 10, 17, 19, 18, 8 }, { 21, 4, 13, 12, 9, 29, 19 },
				{ 58, 1, 20, 5, 8, 16, 9 }, { 5, 6, 15, 2, 39, 8, 29 }, { 39, 7, 17, 5, 4, 49, 5 },
				{ 74, 46, 8, 11, 25, 2, 11 } };
		int p = 19;
		int r = 6;

//		int[][] maps = { { 47, 8, 99, 9, 85, 3, 8 }, { 90, 93, 8, 25, 98, 15, 97 }, { 9, 95, 91, 87, 8, 81, 9 },
//				{ 98, 88, 82, 89, 79, 81, 97 }, { 97, 35, 31, 97, 81, 2, 92 }, { 32, 16, 49, 9, 91, 89, 17 },
//				{ 53, 6, 35, 12, 13, 98, 5 } };
//		int p = 78;
//		int r = 6;

		System.out.println(solution(maps, p, r));
	}
}
