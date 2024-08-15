import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        // int[] {크기, 갯수}
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        Map<Integer, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < tangerine.length; i++) {
            int size = tangerine[i];
            hm.put(size, hm.getOrDefault(size, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        
        while (k > 0) {
            int[] cur = pq.poll();
            k -= cur[1];
            answer++;
        }
        
        return answer;
    }
}