import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2504_괄호의값 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] bracket = br.readLine().split("");

		Stack stack = new Stack();

		for (String str : bracket) {
//			System.out.println("now : " + str);
			if (str.equals("(") || str.equals("[")) {
				stack.push(str);
			} else {
				// 스택에서 괄호가 나올 때 까지 들어 있는 숫자를 모두 더함
				int num = 0;
				while (!stack.isEmpty() && !stack.peek().equals("(") && !stack.peek().equals("[")) {
					String top = stack.pop();
					num += Integer.parseInt(top);
				} // end while

				// 숫자를 모두 꺼냈을 경우 빈 스택이면 안되므로 isEmpty 체크 필수
				if (!stack.isEmpty() && stack.peek().equals("(") && str.equals(")")) {
					stack.pop();
					// 스택에서 꺼낸 숫자가 있다면 곱해주고 없다면 더해 준다
					if (num != 0)
						num *= 2;
					else
						num += 2;

					stack.push(Integer.toString(num));
				} else if (!stack.isEmpty() && stack.peek().equals("[") && str.equals("]")) {
					stack.pop();
					// 스택에서 꺼낸 숫자가 있다면 곱해주고 없다면 더해 준다
					if (num != 0)
						num *= 3;
					else
						num += 3;

					stack.push(Integer.toString(num));
				} else { // 빈 스택이거나 괄호 쌍이 맞지 않을 경우 반복 종료
					stack.clear();
					stack.push("0");

					break;
				}
			}
//			stack.print();
//			System.out.println("--------------------------");
		} // end for
		int result = 0;
		// 스택에 들어 있는 모든 값을 더해준다. 이 때 괄호가 남아 있다면 올바른 쌍이 아니므로 0을 출력한다.
		while (!stack.isEmpty()) {
			String current = stack.pop();
			if (current.equals("(") || current.equals(")") || current.equals("[") || current.equals("]")) {
				result = 0;
				break;
			}
			result += Integer.parseInt(current);
		}

		System.out.println(result);
	}
}

// 스택 구현 class
class Stack {
	private int top;
	private String[] stack;

	public Stack() {
		top = 0;
		stack = new String[100];
	}

	public void push(String n) {
		stack[top++] = n;
	}

	public String pop() {
		return stack[--top];
	}

	public String peek() {
		return stack[top - 1];
	}

	public boolean isEmpty() {
		if (top == 0)
			return true;
		return false;
	}

	public void print() {
		for (int i = 0; i < top; i++)
			System.out.print(stack[i] + " ");
		System.out.println();
	}

	public void clear() {
		top = 0;
	}
}
