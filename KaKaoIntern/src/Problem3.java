import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Problem3 {
	static public String solution(int n, int k, String[] cmd) {
		String answer = "";

		Map<Integer, String> isRemove = new HashMap<Integer, String>();
		List<Integer> chart = new LinkedList<Integer>();

		for (int i = 0; i < n; i++)
			chart.add(i);

		int select = k;
		Stack<int[]> removed = new Stack<int[]>();
		
		for(int i = 0; i < n; i++)
			isRemove.put(i, "O");

		for (int i = 0; i < cmd.length; i++) {
			char command = cmd[i].charAt(0);

			if (command == 'U') {
				select = chart.size() + select - (cmd[i].charAt(2) - '0');
				select %= chart.size();
			} else if (command == 'D') {
				select = (select + (cmd[i].charAt(2) - '0')) % chart.size();
			} else if (command == 'C') {
				removed.add(new int[] { select, chart.get(select) });
				isRemove.put(chart.get(select), "X");
				chart.remove(select);
				if(select == chart.size())
					select--;
			} else if (command == 'Z') {
				int[] back = removed.pop();
				System.out.println(back[0] + " " + back[1]);
				if(back[0] <= select)
					select++;
				chart.add(back[0], back[1]);
				isRemove.put(back[1], "O");
			}
			System.out.println(cmd[i]);
			System.out.println("Select : " + select);
			System.out.println(isRemove.toString());
			System.out.println(chart.toString());
			System.out.println("------------------------");
		}

		for (int i = 0; i < n; i++) {
			answer += isRemove.get(i);
		}

		return answer;
	}

	public static void main(String[] args) {
		int n = 8;
		int k = 2; // 처음 선택한 행의 위치
		String[] cmd = {"U 2", "U 1"};

		System.out.println(solution(n, k, cmd));
	}
}
