import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i : scoville) {
            pq.offer(i);
        }
        
        while (pq.peek() < K) {
            
            if (pq.size() >= 2) {
                int x = pq.poll();
                int y = pq.poll();

                int sum = x + y * 2;
                pq.offer(sum);

                answer++;
            } else {
                answer = -1;
                break;
            }
        }
        
        return answer;
    }
}