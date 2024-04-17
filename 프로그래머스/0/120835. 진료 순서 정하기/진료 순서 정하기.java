import java.util.*;

class Solution {
    public int[] solution(int[] emergency) {
        int[] answer = new int[emergency.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        for (int i = 0; i < emergency.length; i++) {
            pq.add(emergency[i]);
        }
        
        int ranking = 1;
        while (pq.size() > 0) {
            int x = pq.poll();
            for (int i = 0; i < emergency.length; i++) {
                if (emergency[i] == x) {
                    answer[i] = ranking++;
                }
            }
        }
        return answer;
    }
}