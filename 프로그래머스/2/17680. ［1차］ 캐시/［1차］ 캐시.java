import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // 큐에 도시이름을 순서대로 넣는다.
        // 넣으면서 큐에 contains인지 확인한다.
        List<String> list = new ArrayList<>();
        
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            
            if (list.contains(city)) {
                answer += 1;
                list.remove(city);
                list.add(city);
            } else {
                answer += 5;
                if (list.size() < cacheSize) {
                    list.add(city);
                } else {
                    if (!list.isEmpty()) {
                        list.remove(0);
                        list.add(city);    
                    }
                }
            }
        }
        
        
        return answer;
    }
}