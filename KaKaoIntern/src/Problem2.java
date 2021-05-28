import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem2 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static public int calcDist(int[] p1, int[] p2) {
		return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
	}

	static public int[] solution(String[][] places) {
		int[] answer = new int[5];

		fs: for (int i = 0; i < 5; i++) {
			answer[i] = 1;
			System.out.println("---------------------------");

			char[][] waitingRoom = new char[5][5];
			// 대기실 정보 char 배열로 변환
			for (int j = 0; j < 5; j++)
				waitingRoom[j] = places[i][j].toCharArray();

			List<int[]> people = new ArrayList<int[]>();

			for (int row = 0; row < 5; row++) {
				for (int col = 0; col < 5; col++) {
					if (waitingRoom[row][col] == 'P')
						people.add(new int[] { row, col });
				}
			}

			for (int j = 0; j < people.size(); j++) {
				for (int k = j + 1; k < people.size(); k++) {
					int[] p1 = people.get(j);
					int[] p2 = people.get(k);
					int dist = calcDist(p1, p2);
					if (dist > 2)
						continue;
					else if (dist == 1) {
						answer[i] = 1;
						continue fs;
					}

					int middleR = (p1[0] + p2[0]) / 2;
					int middleC = (p1[1] + p2[1]) / 2;
					if (p1[0] == p2[0]) {
						if (waitingRoom[p1[0]][middleC] != 'X') { // 같은 행에 있으면서 파티션이 없는 경우
							System.out.printf("1 (%d, %d) , (%d, %d) \n", p1[0], p1[1], p2[0], p2[1]);
							answer[i] = 0;
							continue fs;
						}
					} else if (p1[1] == p2[1]) {
						if (waitingRoom[middleR][p1[1]] != 'X') {
							System.out.printf("2 (%d, %d) , (%d, %d) \n", p1[0], p1[1], p2[0], p2[1]);
							answer[i] = 0;
							continue fs;
						}
					} else if (waitingRoom[Math.min(p1[0], p2[0])][Math.max(p1[1], p2[1])] != 'X'
							|| waitingRoom[Math.max(p1[0], p2[0])][Math.min(p1[1], p2[1])] != 'X') {
						System.out.printf("3 (%d, %d) , (%d, %d) \n", p1[0], p1[1], p2[0], p2[1]);
						System.out.printf("3 (%d, %d) , (%d, %d) \n", Math.min(p1[0], p2[0]), Math.max(p1[1], p2[1]), Math.max(p1[0], p2[0]), Math.min(p1[1], p2[1]));
						answer[i] = 0;
						continue fs;
					}
				}
			}

		}

		return answer;
	}

	public static void main(String[] args) {
		String[][] places = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };

		int[] answer = solution(places);
		System.out.println(Arrays.toString(answer));
	}
}
