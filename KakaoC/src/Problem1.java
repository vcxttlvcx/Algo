import java.util.Arrays;

/**
 * 받을 수 있는 상품 번호가 적혀있는 상품권을 사람들이 각자 하나씩 가지고 있습니다.
 * 사람들은 각자 받고 싶은 상품이 있는데, 자신이 가지고 있는 상품권의 번호가 자신이 받고 싶은 상품의 번호가 아니라면
 * 다른 사람과 교환할 수 있습니다. 이때, 원하지 않는 상품을 받는 사람 수를 최소로 해야 합니다.
 * 예를 들어 상품권을 가진 사람이 5명이고, 첫 번째 사람부터 가지고 있는 상품권에 적힌 번호가 [4, 5, 3, 2, 1],
 * 각 사람이 받고 싶어 하는 상품 번호가 순서대로 [2, 4, 4, 5, 1]라고 하겠습니다.
 * 이 경우 다섯 번째 사람은 가지고 있는 상품권에 적힌 번호(1번)가 받고 싶은 상품 번호(1번)와 일치하기 때문에
 * 다른 사람과 교환하지 않아도 됩니다.
 * 또, 첫 번째, 두 번째, 네 번째 사람의 경우 아래와 같이 상품권을 교환하면 각자 받고 싶은 상품의 번호가 적힌 상품권을 가질 수 있습니다.
 * 첫 번째 사람과 네 번째 사람의 번호를 교환합니다. (4번 ↔ 2번)
 * 다시, 두 번째 사람과 네 번째 사람의 번호를 교환합니다. (5번 ↔ 4번)
 * 이제 각 사람이 가지고 있는 상품권에 적힌 번호는 아래와 같습니다.
 * 		[2, 4, 3, 5, 1]
 * 세 번째 사람은 4번 상품을 받고 싶지만, 더 교환할 사람이 없습니다. 따라서 원하는 상품을 받을 수 없는 사람은 최소 1명이 됩니다.
 * 사람들이 가지고 있는 상품권에 적힌 번호가 순서대로 들어있는 배열 gift_cards와
 * 각 사람이 받고 싶어 하는 상품 번호가 순서대로 들어있는 배열 wants가 매개변수로 주어질 때,
 * 원하는 상품을 받지 못하는 사람의 최솟값을 return 하도록 solution 함수를 완성해주세요.
 * 
 * 제한 사항
 * gift_cards와 wants의 길이는 1 이상 100,000 이하이며, 두 배열의 길이는 항상 같습니다.
 * gift_cards와 wants의 원소는 1 이상 100,000 이하인 자연수입니다.
 * 모든 상품은 여분없이 각 번호가 적힌 상품권 개수만큼 준비되어 있다고 가정합니다.
 */
public class Problem1 {
	public static int solution(int[] gift_cards, int[] wants) {
        int answer = 0;
        
        Arrays.sort(gift_cards);
        Arrays.sort(wants);
        
        answer = wants.length;
        int gIdx = 0;
        int wIdx = 0;
        while(gIdx < gift_cards.length && wIdx < wants.length) {
        	if(gift_cards[gIdx] == wants[wIdx]) {
        		answer--;
        		gIdx++;
        		wIdx++;
        	} else if(gift_cards[gIdx] > wants[wIdx]) {
        		wIdx++;
        	} else {
        		gIdx++;
        	}
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		int[] gift_cards = {5, 4, 5, 4, 5};
		int[] wants = {1, 2, 3, 3, 3};
		
		System.out.println(solution(gift_cards, wants));
	}
}
