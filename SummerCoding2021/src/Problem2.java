import java.util.Arrays;
import java.util.PriorityQueue;

class Ticket implements Comparable<Ticket> {
	int id; // 손님의 아이디
	int t; // 도착 시간
	int r; // 우선 순위

	public Ticket(int id, int t, int r) {
		this.id = id;
		this.t = t;
		this.r = r;
	}

	@Override
	public int compareTo(Ticket t) {
		if (this.r == t.r) {
			if (this.t == t.t)
				return this.id - t.id;
			return this.t - t.t;
		}

		return this.r - t.r;
	}

}

public class Problem2 {

	static public int[] solution(int[] t, int[] r) {
		int[] answer = new int[t.length];
		// 정렬을 위하 우선순위 큐
		PriorityQueue<Ticket> pq = new PriorityQueue<Ticket>();

		int time = 0;
		int cnt = 0;
		while (cnt < t.length) {
			// 현재 시간에 도착한 사람을 찾아 우선순위 큐에 넣는다
			for (int i = 0; i < t.length; i++) {
				if (time == t[i])
					pq.add(new Ticket(i, t[i], r[i]));
			}
			// 우선순위 큐에 들어 있다면 하나 꺼내서 결과에 저장
			if (!pq.isEmpty())
				answer[cnt++] = pq.poll().id;

			time++;
		}

		return answer;
	}

	public static void main(String[] args) {
		int[] t = { 0, 1, 3, 0 };
		int[] r = { 0, 1, 2, 0 };

//		int[] t = {7, 6, 8, 1};
//		int[] r = {0, 1, 2, 3};

		System.out.println(Arrays.toString(solution(t, r)));
	}
}
