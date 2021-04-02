import java.util.Stack;

public class 괄호변환 {
	
	public static boolean check(String s) {
		Stack<Character> stack = new Stack<Character>();
		
		for(char c : s.toCharArray()) {
			if(c == '(') {
				stack.push(c);
			} else {
				if(stack.isEmpty() || stack.peek() != '(')
					return false;
				stack.pop();
			}
		}
		
		return true;
	}
	
	public static String solution(String p) {
		// 1. 입력이 빈 문자열일 경우, 빈 문자열을 반환
		if("".equals(p))
			return "";
		// 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 문자열"로 더 이상
		// 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
		String u = "";
		String v = "";
		int cnt = 0;
		for(int i = 0; i < p.length(); i++) {
			if(p.charAt(i) == '(')
				cnt++;
			else
				cnt--;
			if(cnt == 0) {
				u = p.substring(0, i + 1);
				v = p.substring(i + 1);
				break;
			}
		}
		// 3. 문자열 u가 "올바른 괄호 문자열"이라면 문자열 v에 대해 1단계부터 다시 수행
		if(check(u))
			return u + solution(v);
		// 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
		// 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
		StringBuffer temp = new StringBuffer("(");
		// 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
		temp.append(solution(v));
		// 4-3. ')'를 다시 붙입니다.
		temp.append(")");
		// 4-4. u의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
		for(int i = 1; i < u.length() - 1; i++) {
			if(u.charAt(i) == '(')
				temp.append(')');
			else
				temp.append('(');
		}
		// 4-5. 생선된 문자열을 반환합니다.
		
		return temp.toString();
	}

	public static void main(String[] args) {
		String p = "()))((()";
		System.out.println("p == " + p);
		System.out.println(solution(p));
	}

}
