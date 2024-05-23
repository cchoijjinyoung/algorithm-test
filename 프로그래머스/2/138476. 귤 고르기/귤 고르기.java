import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> hm = new HashMap<>();
        
        // 크기, 갯수
        PriorityQueue<Gyool> pq = new PriorityQueue<>(
        (g1, g2) -> g2.count - g1.count);
        
        for (int i = 0; i < tangerine.length; i++) {
            hm.put(tangerine[i], hm.getOrDefault(tangerine[i], 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            pq.add(new Gyool(entry.getKey(), entry.getValue()));
        }
        
        int count = 0;
        while (true) {
            answer++;
            Gyool cur = pq.poll();
            count += cur.count;
            if (count >= k) {
                break;
            }
        }
        // 맵에 크기에 따른 갯수들이 들어가있음.
        // 갯수가 많은 순서대로 출력
        
        return answer;
    }
    
    class Gyool {
        int size;
        int count;
        
        public Gyool(int size, int count) {
            this.size = size;
            this.count = count;
        }
    }
}