import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Set<Integer> os = new HashSet<>();
        Set<Integer> ys = new HashSet<>();
        
        Map<Integer, Integer> ym = new HashMap<>();
        
        for (int i = 0; i < topping.length; i++) {
            ys.add(topping[i]);
            ym.put(topping[i], ym.getOrDefault(topping[i], 0) + 1);
        }
        
        if (ys.size() == os.size()) {
                answer++;
        }
        
        for (int i = 0; i < topping.length; i++) {
            os.add(topping[i]);
            ym.put(topping[i], ym.get(topping[i]) - 1);
            if (ym.get(topping[i]) == 0) {
                ys.remove(topping[i]);
            }
            if (ys.size() == os.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}