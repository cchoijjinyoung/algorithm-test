import java.util.*;

class Solution {
    public int solution(int[] cards) {
        
        // 1번상자 2번상자 곱해주기 위한 가장 큰애 2명빼려고,
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        
        boolean[] visited = new boolean[cards.length];
        
        for (int i = 0; i < cards.length; i++) {
            int cnt = 0;
            // 만약 이미 연 상자면 다음 상자 보자
            if (visited[i]) {
                continue;
            }
            
            visited[i] = true;
            cnt++;
            
            int box = cards[i] - 1;
            while (true) {
                if (visited[box]) {
                    break;
                }
                cnt++;
                visited[box] = true;
                box = cards[box] - 1;
            }
            pq.offer(cnt);
        }
        
        if (pq.size() == 1) {
            return 0;
        } else {
            return pq.poll() * pq.poll();
        }
    }
}