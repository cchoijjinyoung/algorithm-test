import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for (int i : queue1) {
            q1.add(i);
        }
        
        for (int i : queue2) {
            q2.add(i);
        }
        
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        
        if ((sum1 + sum2) % 2 == 1) {
            return -1;
        }
        
        long target = (sum1 + sum2) / 2;
        
        for (int i = 0; i < 2 * (queue1.length + queue2.length); i++) {
            if (sum1 < target && !q2.isEmpty()) {
                int num = q2.poll();
                q1.add(num);
                sum1 += num;
                sum2 -= num;
                answer++;
                continue;
            }

            if (sum1 > target && !q1.isEmpty()) {
                int num = q1.poll();
                q2.add(num);
                sum2 += num;
                sum1 -= num;
                answer++;
                continue;
            }

            if (sum1 == target) {
                break;
            }
        }
        
        if (sum1 != sum2) answer = -1;

        return answer;
    }
}