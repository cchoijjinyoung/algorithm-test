import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        
        int sum = 0; // pq에 있는 값의 합
        
        for (int i = 0; i < enemy.length; i++) {
            sum += enemy[i];
            pq.offer(enemy[i]);
            
            if (sum > n && k > 0) {
                sum -= pq.poll();    
                k--;
            }
            
            if (sum > n && k == 0) {
                break;
            }
            
            answer = i + 1;
        }
        
        return answer;
    }
}