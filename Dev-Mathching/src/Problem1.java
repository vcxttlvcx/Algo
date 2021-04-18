import java.util.Arrays;

public class Problem1 {
	// 맞춘 번호를 받아 몇등인지 출력
	public static int winRank(int collectNum) {
		if (collectNum == 6)
			return 1;
		else if (collectNum == 5)
			return 2;
		else if (collectNum == 4)
			return 3;
		else if (collectNum == 3)
			return 4;
		else if (collectNum == 2)
			return 5;

		return 6;
	}

	public static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		// 두 배열을 모두 정렬하여 검사
		Arrays.sort(lottos);
		Arrays.sort(win_nums);
		// 각 배열에서 사용할 index 변수 선언
		int lottosIndex = 0;
		int winIndex = 0;
		// 맞춘 번호의 개수를 저장
		int collectNum = 0;
		// 알아 볼 수 없는 숫자의 개수 저장
		int zeroNum = 0;
		// 두 배열 중 하나라도 범위를 벗어나면 반복 종료
		while (lottosIndex < lottos.length && winIndex < win_nums.length) {
			// 알아 볼수 없는 숫바 개수 파악
			if (lottos[lottosIndex] == 0)
				zeroNum++;
			// 로또 번호와 당첨 번호가 같으면 맞춘 번호의 개수를 증가시키고,
			// 각 배열의 인덱스 값도 증가 시킨다
			if (lottos[lottosIndex] == win_nums[winIndex]) {
				collectNum++;
				lottosIndex++;
				winIndex++;
			} else if (lottos[lottosIndex] < win_nums[winIndex]) // 로또 번호가 더 작을 시 로또 번호의 인덱스 증가
				lottosIndex++;
			else // 당첨 번호가 더 작을 시 당첨 번호 증가
				winIndex++;
		}
		// 0번 인덱스에 가장 높은 등수, 1번 인덱스에 가장 낮은 등수 저장
		answer[0] = winRank(collectNum + zeroNum);
		answer[1] = winRank(collectNum);

		return answer;
	}

	public static void main(String[] args) {
		int[] lottos = { 45, 4, 35, 20, 3, 9 };
		int[] win_nums = { 20, 9, 3, 45, 4, 35 };

		System.out.println(Arrays.toString(solution(lottos, win_nums)));
	}

}
