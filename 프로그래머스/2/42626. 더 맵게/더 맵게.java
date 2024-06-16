import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int s : scoville) {
            pq.offer(s);
        }
        
        while (pq.size() >= 2) {
            if (pq.peek() >= K) {
                break;
            }
            
            int a = pq.poll();
            int b = pq.poll();
            
            pq.offer(a + b * 2);
            
            answer++;
        }
        
        if (pq.peek() < K) answer = -1;
        return answer;
    }
}