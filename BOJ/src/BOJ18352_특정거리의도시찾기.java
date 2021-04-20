import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18352_특정거리의도시찾기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 도시의 개수
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수
		int K = Integer.parseInt(st.nextToken()); // 거리 정보
		int X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호
		// A에서 B로 가는 당방향 도로 M개 입력
		ArrayList<ArrayList<Integer>> roads = new ArrayList<ArrayList<Integer>>();
		int[] dist = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			roads.add(new ArrayList<Integer>());
			dist[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			roads.get(A).add(B);
		}
		// BFS를 통해 풀기
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(X);
		dist[X] = 0;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int v : roads.get(current)) {
				if(dist[v] == Integer.MAX_VALUE) {
					dist[v] = dist[current] + 1;
					queue.add(v);
				}
			}
		}
		// 결과 출력
		boolean flag = false;
		for(int i = 1; i <= N; i++) {
			if(dist[i] != K)
				continue;
			System.out.println(i);
			flag = true;
		}
		if(!flag)
			System.out.println(-1);
	}

}
