import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < score.length; i++) {
            if (i < k) {
                pq.offer(score[i]);
                answer[i] = pq.peek();
            } else {
                pq.offer(score[i]);
                pq.poll();
                answer[i] = pq.peek();
            }
        }
        return answer;
    }
}