import java.util.Stack;

/**
 * Programmers 2019 카카오 개발자 겨울 인턴십 : 크레인 인형뽑기 게임
 * 
 * @author Seok
 *
 */
public class 크레인_인형뽑기_게임 {

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;

		Stack<Integer> basket = new Stack<Integer>();
		// 이동 회수 만큼 반복하며 인형을 집어 바구니에 옮겨 담는다
		for (int move : moves) {
			// move 위치 인덱스는 1부터 시작하기에 -1을 해줌
			move--;
			// 다음 바구니에 옮길 인형을 찾는다
			int doll = 0;
			for (int i = 0; i < board.length; i++) {
				if (board[i][move] != 0) {
					doll = board[i][move];
					board[i][move] = 0;
					break;
				}
			}
			// 잡을 인형이 없다면 pass
			if (doll == 0)
				continue;
			// 인형을 바구니에 넣기 전에 바구니 맨위의 인형과 같은지 확인
			// 같다면 바구니에 넣지않고 stack의 맨위를 제거
			// 다르다면 바구니에 인형 추가
			if (!basket.isEmpty() && doll == basket.peek()) {
				basket.pop();
				answer += 2;
			} else {
				basket.add(doll);
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };

		System.out.println("answer : " + solution(board, moves));
	}

}
