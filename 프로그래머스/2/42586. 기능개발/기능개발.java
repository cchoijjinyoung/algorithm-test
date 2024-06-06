import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> temp = new ArrayList<>();
        
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int days = (100 - progresses[i]) / speeds[i];
            
            if ((100 - progresses[i]) % speeds[i] > 0) {
                days++;
            }
            
            q.add(days);
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            int out = 1;
            
            while (!q.isEmpty()) {
                int next = q.peek();
                if (cur >= next) {
                    q.poll();
                    out++;
                    continue;
                }
                break;
            }
            temp.add(out);
        }
        int[] answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        
        return answer;
    }
}