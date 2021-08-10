
/**
 * 위클리 첼린지 2021.08 2주차 : 상호평가
 * @author Seok
 */
public class WeeklyChallenge202108_2 {
	public static String solution(int[][] scores) {
		String answer = "";
		
		int n = scores.length;
		
		for(int i = 0; i < n; i++) {
			int maxValue = 0;
			int maxIndex = -1;
			int minValue = 101;
			int minIndex = -1;
			
			int sum = 0;
			for(int j = 0; j < n; j++) {
				if(scores[j][i] > maxValue) {
					maxValue = scores[j][i];
					maxIndex = j;
				} else if(scores[j][i] == maxValue) {
					maxIndex = -1;
				}
				if(scores[j][i] < minValue) {
					minValue = scores[j][i];
					minIndex = j;
				} else if(scores[j][i] == minValue) {
					minIndex = -1;
				}
				sum += scores[j][i];
			}
			
			double avg = 0;
			
			if(maxIndex == i || minIndex == i) 
				avg = ((double) sum - scores[i][i]) / (n - 1);
			else
				avg = sum / n;
			if(avg >= 90)
				answer += "A";
			else if(90 > avg && avg >= 80)
				answer += "B";
			else if(80 > avg && avg >= 70)
				answer += "C";
			else if(70 > avg && avg >= 50)
				answer += "D";
			else
				answer += "F";
		}
		
		return answer;
	}

	public static void main(String[] args) {
		int[][] scores = {{70,49,90},{68,50,38},{73,31,100}};
		System.out.println(solution(scores));
	}
}
