import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        // LRU : 가장 오랫동안 참조되지 않은 것을 교체
        List<String> cache = new LinkedList<>();
        
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();
            
            // 캐시에 존재하면
            if (cache.contains(city)) {
                answer += 1;
                cache.remove(city);
            } else { // 캐시에 존재하지 않으면
                answer += 5;
                if (cache.size() >= cacheSize && cacheSize != 0) {
                    cache.remove(0);
                }
            }
            if (cacheSize > 0 && cache.size() < cacheSize) {
                cache.add(city);    
            }
        }
        return answer;
    }
}