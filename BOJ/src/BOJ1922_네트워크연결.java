import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선 정보를 저장할 객체
// 우선순위 큐 사용을 위해 Comparable 인터페이스 구현
class Edge implements Comparable<Edge> {
	int a;
	int b;
	int weight;
	
	public Edge(int a, int b, int weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge edge) {
		return this.weight - edge.weight;
	}
}

public class BOJ1922_네트워크연결 {
	// 부모 노드를 반환하는 메소드
	public static int find(int a, int[] parents) {
		if(parents[a] == a)
			return a;
		parents[a] = find(parents[a], parents);
		
		return parents[a];
	}
	
	public static boolean union(int a, int b, int[] parents) {
		int pa = find(a, parents);
		int pb = find(b, parents);
		// union 연산에서 싸이클이 생성되는지 확인
		if(pa == pb)
			return false;
		
		parents[pa] = pb;
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 컴퓨터의 수
		int M = Integer.parseInt(br.readLine());	// 연결할 수 있는 선의 수
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// kruskal 알고리즘을 활요하기 위해 우선순위 큐에 삽입
			pq.add(new Edge(a, b, w));
		}
		// 자기 자신으로 부모 초기화
		int[] parents = new int[N + 1];
		for(int i = 1; i <= N; i++)
			parents[i] = i;
		int cnt = 0;	// 선택한 간선의 개수
		int cost = 0;	// 컴퓨터를 연결하는대 드는 비용
		// kruskal algorithm
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			// union 결과 false면 continue;
			if(!union(current.a, current.b, parents))
				continue;
			cnt++;
			cost += current.weight;
			// 간선을 N - 1개 선택 시 종료
			if(cnt == N - 1)
				break;
		}
		
		System.out.println(cost);
	}
}
