import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1931_회의실배정 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Meeting[] meetings = new Meeting[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			meetings[i] = new Meeting(s, e);
		}
		// 종료시간으로 오름차순 정렬
		Arrays.sort(meetings);

		int answer = 0;
		int endTime = -1;

		// for (Meeting meet : meetings) {
		// System.out.println(meet.toString());
		// }
		// System.out.println("-----------------------------");
		// 종료시간이 짧은 순으로 그리디 탐색
		for (int i = 0; i < N; i++) {
			if (endTime <= meetings[i].getStartTime()) {
				// System.out.println(meetings[i].toString());
				endTime = meetings[i].getEndTime();
				answer++;
			}
		}

		System.out.println(answer);
	}
}

class Meeting implements Comparable<Meeting> {
	private int startTime;
	private int endTime;

	public Meeting(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Meeting other) {
		if (this.endTime == other.endTime)
			return this.startTime - other.startTime;
		return this.endTime - other.endTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "(" + startTime + ", " + endTime + ")";
	}
}