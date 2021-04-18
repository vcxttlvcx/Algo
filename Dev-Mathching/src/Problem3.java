import java.util.Arrays;
import java.util.HashMap;

// 각 판매자에 대한 정보를 저장하기 위한 클래스
class Seller {
	String name;
	String referral;
	int benefit;
	// 생성자 정의
	public Seller(String name, String referral) {
		this.name = name;
		this.referral = referral;
		this.benefit = 0;
	}
}

public class Problem3 {
	
	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        // HashMap 에 저장하여 이름으로 바로 찾을 수 있도록 함
        HashMap<String, Seller> sellers = new HashMap<String, Seller>();
        // 각 판매자 초기화
        for(int i = 0; i < enroll.length; i++)
        	sellers.put(enroll[i], new Seller(enroll[i], referral[i]));
        // 이익 분배
        for(int i = 0; i < seller.length; i++) {
        	Seller now = sellers.get(seller[i]);	// 판매를 한 사람
        	Seller parent = sellers.get(now.referral);	// 판매 한 사람의 추천인
        	// 팔아서 얻은 수익
        	int benefit = amount[i] * 100;
        	// 추천인에게 줘야하는 이익
        	int upBenefit = (int) (benefit * 0.1);
        	// 나의 이익금 저장
        	now.benefit += benefit - upBenefit;
        	// null이 아니거나 현재 내가 center가 아니면 반복
        	while(parent != null && !"-".equals(parent.name)) {
        		// 추천자가 가질 이득이 0원이면 더 할 필요 X
        		if(upBenefit == 0)
        			break;
        		// 내가 받을 금액
        		benefit = upBenefit;
        		// 추천인에게 주어야 하는 금액
        		upBenefit = (int) (benefit * 0.1);
        		// 나의 이익 저장
        		parent.benefit += benefit - upBenefit;
        		// 나를 추천한 사람으로 설정
        		parent = sellers.get(parent.referral);
        	}
        }
        // 모든 사람의 이익금을 배열에 저장하여 return
        for(int i = 0; i < enroll.length; i++)
        	answer[i] = sellers.get(enroll[i]).benefit;
        return answer;
    }

	public static void main(String[] args) {
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"sam", "emily", "jaimie", "edward"};
		int[] amount = {2, 3, 5, 4};
		
		System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
	}

}
