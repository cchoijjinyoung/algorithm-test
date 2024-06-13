import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        boolean[] visited = new boolean[N + 1];
        
        // 출발노드 - {도착지 가중치}.., .., ..
        List<List<int[]>> list = new ArrayList<>();
        
        // 초기화
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < road.length; i++) {
            list.get(road[i][0]).add(new int[]{road[i][1], road[i][2]});
            list.get(road[i][1]).add(new int[]{road[i][0], road[i][2]});
        }
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        pq.offer(new int[]{1, 0});
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (visited[cur[0]]) {
                continue;
            }
            
            visited[cur[0]] = true;
            
            for (int i = 0; i < list.get(cur[0]).size(); i++) {
                int[] next = list.get(cur[0]).get(i);
                
                dist[next[0]] = Math.min(dist[next[0]], cur[1] + next[1]);
                pq.offer(new int[]{next[0], dist[next[0]]});
            }
        }
        
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
}