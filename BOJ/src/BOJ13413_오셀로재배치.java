import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 13413 : 오셀로 재배치
 */
public class BOJ13413_오셀로재배치 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			char[] init = br.readLine().toCharArray();
			char[] des = br.readLine().toCharArray();
			
			int w = 0;
			int b = 0;
			
			int ans = 0;
			
			for(int i = 0; i < N; i++) {
				if(init[i] != des[i]) {
					if(init[i] == 'W')
						w++;
					else
						b++;
				}
			}
			
			ans = Math.min(w, b) + Math.abs(w - b);

			System.out.println(ans);
		}
	}

}
