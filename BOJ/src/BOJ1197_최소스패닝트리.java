import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node1197 implements Comparable<Node1197> {
	int v;
	int weight;
	
	public Node1197(int v, int weight) {
		this.v = v;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node1197 node) {
		return this.weight - node.weight;
	}
}

public class BOJ1197_최소스패닝트리 {
	// prim 알고리즘을 이용한 풀이
	public static int prim(int V, int E, ArrayList<ArrayList<Node1197>> edges) {
		boolean[] isVisited = new boolean[V + 1];
		int mst = 0;
		
		PriorityQueue<Node1197> pq = new PriorityQueue<Node1197>();
		pq.add(new Node1197(1, 0));
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Node1197 current = pq.poll();
			// 방문 했다면 continue
			if(isVisited[current.v])
				continue;
			// 방문하지 않았다면 최소 신장 트리에 추가 및 가중치 더하기
			isVisited[current.v] = true;
			mst += current.weight;
			// 연결 되어 있는 정점들 중 방문하지 않은 정점을 pq에 넣어준다
			for(Node1197 next : edges.get(current.v)) {
				if(!isVisited[next.v])
					pq.add(next);
			}
			
			if(++cnt == V)
				break;
		}
		
		return mst;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		// 간선 정보 저장 할 pq 선언 및 초기화
		ArrayList<ArrayList<Node1197>> edges = new ArrayList<ArrayList<Node1197>>();
		for(int i = 0; i <= V; i++)
			edges.add(new ArrayList<Node1197>());
		// 간선 정보 입력
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			edges.get(A).add(new Node1197(B, C));
			edges.get(B).add(new Node1197(A, C));
		}
		
		System.out.println(prim(V, E, edges));
	}

}
