import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
        (a, b) -> b - a);
        
        int answer = 1;
        
        
        for (int i = 0; i < priorities.length; i++) {
            pq.add(priorities[i]);
        }
        
        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == pq.peek()) {
                    if (location == i) {
                        return answer;
                    }
                    pq.poll();
                    answer++;
                }
                
            }
        }
        return answer;
    }
}