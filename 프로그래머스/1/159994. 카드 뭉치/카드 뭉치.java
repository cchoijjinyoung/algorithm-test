import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        
        for (String s : cards1) {
            q1.offer(s);
        }
        
        for (String s : cards2) {
            q2.offer(s);
        }
        
        boolean road = false;
        for (int i = 0; i < goal.length; i++) {
            boolean flag = false;
            if (!q1.isEmpty() && q1.peek().equals(goal[i])) {
                flag = true;
                q1.poll();
            } else if (!q2.isEmpty() && q2.peek().equals(goal[i])) {
                flag = true;
                q2.poll();
            }
            
            if (!flag) {
                break;
            }
            
            if (i == goal.length - 1) {
                road = true;
            }
        }
        
        if (!road) {
            answer = "No";
        }
        return answer;
    }
}