import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class 베스트앨범 {

	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};

		HashSet<String> keys = new HashSet<String>();
		HashMap<String, Integer> sum = new HashMap<String, Integer>();
		HashMap<String, ArrayList<Integer>> genre = new HashMap<String, ArrayList<Integer>>();

		for (int i = 0; i < genres.length; i++) {
			keys.add(genres[i]);
			if (!genre.containsKey(genres[i])) {
				sum.put(genres[i], 0);
				genre.put(genres[i], new ArrayList<Integer>());

			}
			sum.put(genres[i], sum.get(genres[i]) + plays[i]);
			genre.get(genres[i]).add(i);
		}
		for (String key : keys) {
			Collections.sort(genre.get(key), new Comparator<Integer>() {
				@Override
				public int compare(Integer a, Integer b) {
					if (plays[a] == plays[b])
						return a - b;

					return plays[b] - plays[a];
				}
			});
		}
		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		
		while(sum.size() > 0) {
			String key = "";
			int max = 0;
			for(String k : keys) {
				if(sum.get(k) > max) {
					key = k;
					max = sum.get(k);
				}
			}
			
			ans.add(genre.get(key).get(0));
			if(genre.get(key).size() >= 2)
				ans.add(genre.get(key).get(1));
			sum.remove(key);
			genre.remove(key);
			keys.remove(key);
		}
		answer = new int[ans.size()];
		for(int i = 0; i < ans.size(); i++)
			answer[i] = ans.get(i);

		return answer;
	}

	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };

		System.out.println(Arrays.toString(solution(genres, plays)));
	}

}
