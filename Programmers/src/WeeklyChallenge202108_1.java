
/**
 * 위클리 첼린지 2021.08 1주차 : 부족한 금액 계산
 * @author Seok
 */
public class WeeklyChallenge202108_1 {
	public static long solution(int price, int money, int count) {
		long answer = -1;

		answer = ((((long) price * (1 + count)) * count) / 2) - money;
		if (answer <= 0)
			answer = 0;

		return answer;
	}

	public static void main(String[] args) {
		int price = 2500;
		int money = 2500;
		int count = 2;

		System.out.println(solution(price, money, count));
	}
}
