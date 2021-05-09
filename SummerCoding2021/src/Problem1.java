import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem1 {
	static public int[] solution(String code, String day, String[] data) {
		int[] answer = {};
		
		// 날짜로 정렬하기 위해 우선순위 큐 사용
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] c1, int[] c2) {
				return c1[1] - c2[1];
			}
		});
		
		for(int i = 0; i < data.length; i++) {
			StringTokenizer st = new StringTokenizer(data[i]);
			// 금액, 날짜, 회사 코드 분리
			int price = Integer.parseInt(st.nextToken().substring(6));
			String company = st.nextToken().substring(5);
			String time = st.nextToken().substring(5);
			// 찾으려는 날짜에 같은 회사명을 가진 data를 우선순위 큐에 삽입
			if(day.equals(time.substring(0, 8)) && code.equals(company))
				q.add(new int[] {price, Integer.parseInt(time.substring(8))});
		}
		// 결과 저장
		answer = new int[q.size()];
		int idx = 0;
		while(!q.isEmpty()) {
			answer[idx++] = q.poll()[0];
		}

		return answer;
	}

	public static void main(String[] args) {
		String code = "012345";
		String day = "20190620";
		String[] data = { "price=80 code=987654 time=2019062113", "price=90 code=012345 time=2019062014",
				"price=120 code=987654 time=2019062010", "price=110 code=012345 time=2019062009",
				"price=95 code=012345 time=2019062111" };
		
		System.out.println(Arrays.toString(solution(code, day, data)));
	}
}
