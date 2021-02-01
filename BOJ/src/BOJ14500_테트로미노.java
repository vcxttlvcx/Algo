import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14500 : 테트로미노
 * 
 * @author Seok
 */
public class BOJ14500_테트로미노 {

	private static int N;
	private static int M;

	private static int[][] map;
	private static boolean[][] isVisited;

	private static int max;

	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 종이의 크기 N * M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 종이에 쓰여져 있는 수 입력
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		max = 0;
		isVisited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				isVisited[i][j] = true;
				dfs(i, j, map[i][j], 1);
				isVisited[i][j] = false;

				checkShape5(i, j);
			}
		}
		System.out.println(max);
	}

	public static void dfs(int r, int c, int sum, int length) {
		if (length >= 4) {
			max = sum > max ? sum : max;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M || isVisited[nr][nc])
				continue;

			isVisited[nr][nc] = true;
			dfs(nr, nc, sum + map[nr][nc], length + 1);
			isVisited[nr][nc] = false;
		}
	}

	// 5. ㅜ 형태
	public static void checkShape5(int r, int c) {
		int sum = 0;
		// 1. ㅜ
		if (r >= 0 && r + 1 < N && c >= 0 && c + 2 < M) {
			sum = map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r + 1][c + 1];
			max = Math.max(max, sum);
		}

		// 2. ㅏ
		if (r >= 0 && r + 2 < N && c >= 0 && c + 1 < M) {
			sum = map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c + 1];
			max = Math.max(max, sum);
		}

		// 3. ㅗ
		if (r - 1 >= 0 && r < N && c >= 0 && c + 2 < M) {
			sum = map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r - 1][c + 1];
			max = Math.max(max, sum);
		}

		// 4. ㅓ
		if (r - 1 >= 0 && r + 1 < N && c >= 0 && c + 1 < M) {
			sum = map[r][c] + map[r][c + 1] + map[r - 1][c + 1] + map[r + 1][c + 1];
			max = Math.max(max, sum);
		}
	}
}
