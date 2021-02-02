import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 20057 : 마법사 상어와 토네이도
 * 
 * @author Seok
 *
 */
public class BOJ20057_마법사상어와토네이도 {

	private static int N;
	private static int[][] A;

	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { -1, 0, 1, 0 };

	private static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		A = new int[N + 1][N + 1];

		ans = 0;
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}

		int r = N / 2 + 1;
		int c = N / 2 + 1;

		int dir = 0;

		int size = 1;
		int corner = 0; // size만큼 간 후 코너를 돈 횟수
		int count = 0;
		while (!(r == 1 && c == 1)) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			moveSand(nr, nc, dir);

			count++;
			if (count % size == 0) {
				count = 0;
				corner++;
				dir = (dir + 1) % 4;
				if (corner % 2 == 0) {
					size++;
					corner = 0;
				}
			}

			r = nr;
			c = nc;
		}
		
		System.out.println(ans);
	}

	/**
	 * 토네이도의 이동에 따른 모래의 이동 메소드
	 * 
	 * @param r   토네이도가 이동한 행 위치
	 * @param c   토네이도가 이동한 열 위치
	 * @param dir 토네이도가 이동해온 방향
	 */
	public static void moveSand(int r, int c, int dir) {
		int num = A[r][c];

		// 1. 두칸 앞 5% 모래 이동
		int nr = r + 2 * dr[dir];
		int nc = c + 2 * dc[dir];

		if (nr < 1 || nc < 1 || nr > N || nc > N)
			ans += num * 0.05;
		else
			A[nr][nc] += num * 0.05;
		// 2. 대각선 앞쪽 10% 이동
		for (int i = 0; i < 2; i++) {
			nr = r + dr[dir] + dr[(dir + 1 + 2 * i) % 4];
			nc = c + dc[dir] + dc[(dir + 1 + 2 * i) % 4];

			if (nr < 1 || nc < 1 || nr > N || nc > N)
				ans += num * 0.1;
			else
				A[nr][nc] += num * 0.1;
		}
		// 3. 양옆 한칸 7% 이동
		for (int i = 0; i < 2; i++) {
			nr = r + dr[(dir + 1 + 2 * i) % 4];
			nc = c + dc[(dir + 1 + 2 * i) % 4];

			if (nr < 1 || nc < 1 || nr > N || nc > N)
				ans += num * 0.07;
			else
				A[nr][nc] += num * 0.07;
		}
		// 4. 양 옆 두칸 2% 이동
		for (int i = 0; i < 2; i++) {
			nr = r + dr[(dir + 1 + 2 * i) % 4] * 2;
			nc = c + dc[(dir + 1 + 2 * i) % 4] * 2;

			if (nr < 1 || nc < 1 || nr > N || nc > N)
				ans += num * 0.02;
			else
				A[nr][nc] += num * 0.02;
		}
		// 5. 대각선 뒤 쪽 1% 이동
		for (int i = 0; i < 2; i++) {
			nr = r + dr[(dir + 2) % 4] + dr[(dir + 1 + 2 * i) % 4];
			nc = c + dc[(dir + 2) % 4] + dc[(dir + 1 + 2 * i) % 4];

			if (nr < 1 || nc < 1 || nr > N || nc > N)
				ans += num * 0.01;
			else
				A[nr][nc] += num * 0.01;
		}

		num -= (int) (A[r][c] * 0.05);
		num -= (int) (A[r][c] * 0.1) * 2;
		num -= (int) (A[r][c] * 0.07) * 2;
		num -= (int) (A[r][c] * 0.02) * 2;
		num -= (int) (A[r][c] * 0.01) * 2;

		nr = r + dr[dir];
		nc = c + dc[dir];

		if (nr < 1 || nc < 1 || nr > N || nc > N)
			ans += num;
		else
			A[nr][nc] += num;

		A[r][c] = 0;
	}
}
