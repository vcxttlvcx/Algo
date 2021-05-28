
public class Problem1 {

	static String[] number = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

	public static int solution(String s) {
		int idx = 0;
		String result = "";

		while (idx < s.length()) {
			char c = s.charAt(idx);
			if (c >= '0' && c <= '9') {
				result += c;
				idx++;
				continue;
			}
			
			for(int i = 0; i < 10; i++) {
				if(idx + number[i].length() <= s.length() && number[i].equals(s.substring(idx, idx + number[i].length()))) {
					result += i;
					idx += number[i].length();
					break;
				}
			}
		}

		return Integer.parseInt(result);
	}

	public static void main(String[] args) {
		String s = "123";
		
//		System.out.println(s.subSequence(4, 4 + 5));

		System.out.println(solution(s));
	}
}
