import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        
        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }
        
        for (int i = 0; i < n; i++) {
            int value = pq.poll();
            if (value == 0) break;
            pq.add(value - 1);
        }
        
        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}