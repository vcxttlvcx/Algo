
public class Problem4 {
	
	public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        
        boolean onTrap = false;
        
        int[][] edge = new int[n + 1][n + 1];
        for (int i = 0; i < roads.length; i++) {
        	int s = roads[i][0];
        	int e = roads[i][1];
        	int t = roads[i][2];
        	
        	edge[s][e] = t;
        	edge[e][s] = t;
        }
        
        // 시작 점에서 출발할 수 있는 모든 경우를 검사한다
        for(int i = 1; i <= n; i++) {
        	if(edge[start][i] == 0)
        		continue;
        	
        	int s = start;
        	int e = i;
        	int time = 0;
        	boolean[][] isVisited = new boolean[n + 1][n + 1];
        	
        	
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		int n = 3;
		int start = 1;
		int end = 3;
		int[][] roads = {{1, 2, 2}, {3, 2, 3}};
		int[] traps = {2};
		
		System.out.println(solution(n, start, end, roads, traps));
	}
}
