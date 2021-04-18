import java.util.Arrays;

public class Problem2 {
	// 직사각형의 테두리만 회전하는 메소드
	public static int rotation(int[] query, int[][] map) {
		int lr = query[0];	// 왼쪽 위 행
		int lc = query[1];	// 왼쪽 위 열
		int rr = query[2];	// 오른쪽 아래 행
		int rc = query[3];	// 오른쪽 아래 열
		// 이동을 위한 배열 선언 {우 하 좌 상} 순
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		// 현재 위치 저장을 위한 변수
		int row = lr;
		int col = lc;
		// 방향을 설정하기 위한 변수
		int dir = 0;
		// 앞에 값을 저장하기 위한 변수
		int before = map[row][col];
		// 회전에 영향 받은 값중 최소값을 저장할 변수
		int min = map[row][col];
		// 맨 처음 값을 lr, lc로 설정했기에 do~while문으로 반복을 무조건 1회 실행하도록 함
		do {
			// 도달한 위치에 따라 다음 진행 방향 변경
			if (row == lr && col == rc)	// 오른쪽 위 도달 시 방향을 아래로 바꿈
				dir = 1;
			else if (row == rr && col == rc)	// 오른쪽 아래 도달 시 방향을 왼쪽으로 바꿈
				dir = 2;
			else if(row == rr && col == lc)		// 왼쪽 아래 도달 시 방향을 위로 바꿈
				dir = 3;
			// 다음 위치로 이동
			row += dr[dir];
			col += dc[dir];
			// 최소값 저장
			min = map[row][col] < min ? map[row][col] : min;
			// 회전을 위한 swap 연산 진행
			int temp = map[row][col];
			map[row][col] = before;
			before = temp;
		} while(row != lr || col != lc);	// 원래 위치에 도달 시 반복 종료
		
		return min;
	}
	
	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];	// queries의 length 만큼 배열 선언
		// 회전 수행을 위한 map 선언
		int[][] map = new int[rows + 1][columns + 1];
		// map 초기화 진행
		int num = 1;
		for(int i = 1; i <= rows; i++)
			for(int j = 1; j <= columns; j++)
				map[i][j] = num++;
		// queries 만큼 회전 연산 수행하며 최소값을 배열에 저장
		for(int i = 0; i < queries.length; i++)
			answer[i] = rotation(queries[i], map);

		return answer;
	}

	public static void main(String[] args) {
		int rows = 100;
		int columns = 97;
		int[][] queries = { { 1, 1, 100, 97 } };
		System.out.println(Arrays.toString(solution(rows, columns, queries)));
	}

}
