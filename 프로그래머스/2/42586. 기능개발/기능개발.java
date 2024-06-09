import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int need = 100 - progresses[i];
            int dividen = need % speeds[i];
            
            int days = dividen > 0 ? need / speeds[i] + 1 : need / speeds[i];
            q.add(days);
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            int out = 1;
            
            while (!q.isEmpty() && cur >= q.peek()) {
                q.poll();
                out++;
            }
            list.add(out);
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}