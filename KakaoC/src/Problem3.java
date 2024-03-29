import java.util.Arrays;
import java.util.LinkedList;

/**
 * 1부터 n까지 번호가 하나씩 붙어있는 n개의 역이 철로로 연결되어 있습니다.
 * 철로는 양방향 통행이 가능하며, 임의의 두 역을 직접 연결하는 철로는 최대 하나입니다.
 * 또, 서로 다른 두 역 사이의 이동 경로는 딱 한 가지며, 임의의 두 역 사이에 이동 불가능한 경우는 없습니다.
 * 출발역과 종착역 사이를 왕복하는 열차를 운행하려 합니다.
 * 출발역은 1번 역이며, 전체 역 중 한 곳을 종착역으로 정해야 합니다.
 * 단, 열차가 출발역에서 종착역까지 갈 때 모든 역을 방문할 필요는 없으며, 같은 역을 두 번 이상 방문하면 안 됩니다.
 * 종착역을 정하기 위해 각 역의 일일 이용객 수를 조사했습니다.
 * 이때, 열차가 방문하는 역의 일일 이용객 수의 합이 최대가 되도록 종착역을 지정하면 됩니다.
 * 만약 일일 이용객 수의 합이 최대가 되는 역이 여러개라면, 그 중 번호가 큰 역을 선택하면 됩니다.
 * 전체 역의 개수 n, 1번부터 n번 역까지 일일 이용객 수 passenger, 역 사이의 철로 연결 정보 train이 매개변수로 주어질 때,
 * 종착역으로 지정할 역 번호와 그때의 이용객 수 합을 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 * 
 * 제한사항
 * n은 2 이상 100,000 이하인 자연수입니다.
 * passenger의 길이는 n입니다.
 * 		passenger의 원소는 1 이상 10,000 이하인 자연수입니다.
 * 		passenger[i]는 i + 1번 역의 일일 이용객 수 입니다.
 * train의 세로(행) 길이는 n - 1 입니다.
 * train의 가로(열) 길이는 2입니다.
 * 		train의 원소는 [A, B] 형태입니다.
 * 		A, B는 철로가 연결하는 두 역의 번호입니다.
 * 		A, B는 1 이상 n 이하인 자연수이며, A와 B가 같은 경우는 없습니다.
 * 		같은 철로에 대한 정보가 중복해서 들어있지 않습니다.
 */

class Node {
	int num;
	Node parent;
	LinkedList<Node> childList;
	int sum = 0;

	public Node(int num) {
		this.num = num;
		parent = null;
		this.childList = new LinkedList<Node>();
	}

	public void makeParent(Node parent) {
		if (this.parent != null)
			this.parent = null;
		this.parent = parent;
	}

	public void makeChild(Node child) {
		this.childList.add(child);
	}

	public void order(int sum, int[] passenger) {
		sum += passenger[this.num - 1];
		for (Node next : childList) {
			next.order(sum, passenger);
		}
		this.sum = sum;
	}
}

public class Problem3 {

	public static int[] solution(int n, int[] passenger, int[][] train) {
		int[] answer = { 0, 0 };

		Node[] nodes = new Node[n + 1];
		for (int i = 1; i <= n; i++)
			nodes[i] = new Node(i);

		for (int i = 0; i < train.length; i++) {
			int start = train[i][0];
			int end = train[i][1];

			nodes[start].makeChild(nodes[end]);
			nodes[end].makeParent(nodes[start]);
		}

		nodes[1].order(0, passenger);
		for (int i = 1; i <= n; i++) {
			if (nodes[i].sum >= answer[1]) {
				answer[0] = i;
				answer[1] = nodes[i].sum;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int n = 6;
		int[] passenger = { 1,1,1,1,1,1 };
		int[][] train = { { 1, 2 }, { 1, 3 }, { 1, 4 }, {3, 5}, {3, 6}};

		int[] answer = solution(n, passenger, train);
		System.out.println(Arrays.toString(answer));
	}

}
