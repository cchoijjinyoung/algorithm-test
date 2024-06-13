import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<List<Integer>> list = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edge.length; i++) {
            list.get(edge[i][0]).add(edge[i][1]);
            list.get(edge[i][1]).add(edge[i][0]);
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        boolean[] visited = new boolean[n + 1];
        q.offer(new int[]{1, 0});
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            if (visited[cur[0]]) {
                continue;
            }
            
            visited[cur[0]] = true;
            
            for (int i = 0; i < list.get(cur[0]).size(); i++) {
                int next = list.get(cur[0]).get(i);
                dist[next] = Math.min(dist[next], cur[1] + 1);
                q.offer(new int[]{next, dist[next]});
            }
        }
            
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                continue;
            }
            max = Math.max(max, dist[i]);
        }
        
        for (int i = 0; i < n + 1; i++) {
            if (dist[i] == max) {
                answer++;
            }
        }
        return answer;
    }
}