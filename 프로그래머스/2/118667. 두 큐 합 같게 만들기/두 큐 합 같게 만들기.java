import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum = 0;
        long sum1 = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for (int i : queue1) {
            sum += i;
            sum1 += i;
            q1.add(i);
        }
        
        for (int i : queue2) {
            sum += i;
            q2.add(i);
        }
        
        if (sum % 2 == 1) {
            return -1;
        }
        
        long target = sum / 2;
        
        for (int i = 0; i < 3 * (queue1.length + queue2.length); i++) {
            if (sum1 < target && !q2.isEmpty()) {
                int num = q2.poll();
                q1.add(num);
                sum1 += num;
                answer++;
                continue;
            }

            if (sum1 > target && !q1.isEmpty()) {
                int num = q1.poll();
                q2.add(num);
                sum1 -= num;
                answer++;
                continue;
            }

            if (sum1 == target) {
                break;
            }
        }
        
        if (sum1 != target) answer = -1;
        return answer;
    }
}