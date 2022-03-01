import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon1158_요세푸스문제 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 데이터 입력 받기
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 큐 생성
		Queue queue = new Queue();
		// 큐에 데이터 삽입
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		// 결과 저장할 리스트
		LinkedList<Integer> result = new LinkedList<Integer>();
		// 큐를 순회하면서 K 번째 데이터 빼네기
		while (!queue.isEmpty()) {
			int num = 0;
			for (int i = 1; i <= K; i++) {
				num = queue.poll();
				if (i != K)
					queue.add(num);
			}
			result.add(num);
		}
		// 결과 가공
		String s = "<";
		for (int i = 0; i < N; i++) {
			int n = result.get(i);
			if (i == 0)
				s += n;
			else
				s += ", " + n;
		}
		s += ">";
		System.out.println(s);
	}

}
// 큐 구현체
class Queue {
	private int front;
	private int tail;
	private final int MAX_SIZE = 10000;
	private int[] queue;

	public Queue() {
		front = 0;
		tail = 0;
		queue = new int[MAX_SIZE];
	}

	public void add(int data) {
		if (!isFull()) { // 큐가 가득 차 있지 않으면 삽입
			tail = (tail + 1) % MAX_SIZE;
			queue[tail] = data;
		}
	}

	public int poll() {
		if (isEmpty()) {
			return -1;
		}
		front = (front + 1) % MAX_SIZE;
		return queue[front];
	}

	public boolean isFull() {
		if ((tail + 1) % MAX_SIZE == front) {
			return true;
		}
		return false;
	}

	public boolean isEmpty() {
		return front == tail;
	}
}