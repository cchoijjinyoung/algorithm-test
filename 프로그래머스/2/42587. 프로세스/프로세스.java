import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        for (int i = 0; i < priorities.length; i++) {
            pq.offer(priorities[i]);
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