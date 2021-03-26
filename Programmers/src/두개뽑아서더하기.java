import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 프로그래머스 월드 코드 챌린지 시즌1 : 두 개 뽑아서 더하기
 * 정수 배열 numbers가 주어집니다. numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.
 * 
 * @author Seok
 *
 */
class Solution {
    public int[] solution(int[] numbers) {
    	Set<Integer> set = new HashSet<Integer>();
        
        for(int i = 0; i < numbers.length; i++) {
        	for(int j = i + 1; j < numbers.length; j++) {
        		set.add(numbers[i] + numbers[j]);
        	}
        }
        int[] arr = new int[set.size()];
        int index = 0;
        for(int num: set) {
        	arr[index++] = num;
        }
        
        Arrays.sort(arr);
        
        return arr;
    }
}

public class 두개뽑아서더하기 {

	public static void main(String[] args) {
		int[] numbers = {5,0,2,7};
		
		System.out.println(Arrays.toString(new Solution().solution(numbers)));
	}

}
