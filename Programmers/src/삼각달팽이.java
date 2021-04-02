import java.util.Arrays;
import java.util.ArrayList;

public class 삼각달팽이 {
	public static int[] solution(int n) {
        int[] answer = {};
        
        int[][] snail = new int[n + 1][n + 1];
        
        int num = 1;
        int[] dr = {1, 0, -1};
        int[] dc = {0, 1, -1};
        
        int r = 0;
        int c = 1;
        int dir = 0;
        
        for(int i = n; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                r += dr[dir];
                c += dc[dir];
                snail[r][c] = num++;
            }
            dir = (dir + 1) % 3;
        }
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= i; j++)
                arr.add(snail[i][j]);
        answer = new int[arr.size()];
        for(int i = 0; i < arr.size(); i++)
            answer[i] = arr.get(i);
        
        return answer;
    }

	public static void main(String[] args) {
		int n = 4;
		System.out.println(Arrays.toString(solution(n)));
	}

}
