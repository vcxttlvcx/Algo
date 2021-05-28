import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node2887 implements Comparable<Node2887> {
	int a;
	int b;
	int w;

	public Node2887(int a, int b, int w) {
		this.a = a;
		this.b = b;
		this.w = w;
	}

	@Override
	public int compareTo(Node2887 node) {
		return this.w - node.w;
	}

}

public class BOJ2887_행성터널 {

	public static int calcDist(int[] a, int[] b) {
		return Math.min(Math.abs(a[0] - b[0]), Math.min(Math.abs(a[1] - b[1]), Math.abs(a[2] - b[2])));
	}

	public static int find(int x, int[] p) {
		if (x != p[x])
			p[x] = find(p[x], p);
		return p[x];
	}

	public static boolean union(int a, int b, int[] p) {
		int pa = find(a, p);
		int pb = find(b, p);

		if (pa == pb)
			return false;
		p[pa] = pb;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] planet = new int[N][3];
		PriorityQueue<Node2887> pq = new PriorityQueue<Node2887>();
		// 행성 정보 및 최소 간선 배열 초기화
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			planet[i][0] = Integer.parseInt(st.nextToken());
			planet[i][1] = Integer.parseInt(st.nextToken());
			planet[i][2] = Integer.parseInt(st.nextToken());

			for (int j = 0; j < i; j++) {
				pq.add(new Node2887(j, i, calcDist(planet[i], planet[j])));
			}
		}

		int result = 0;
		int[] parents = new int[N];
		for (int i = 0; i < N; i++)
			parents[i] = i;
		int cnt = 0;
		while (cnt < N - 1) {
			Node2887 cur = pq.poll();
			// union 시에 싸이클이 형성 된다면 false를 반환 받는다
			if (!union(cur.a, cur.b, parents))
				continue;
			System.out.println(cur);
			cnt++;
			result += cur.w;
		}

		// 결과 출력
		System.out.println(result);
	}

}
