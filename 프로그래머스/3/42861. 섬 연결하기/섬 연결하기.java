import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        List<List<int[]>> list = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        
        // Arrays.sort(data, (x, y) -> x[2] - y[2]);
        
        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < costs.length; i++) {
            list.get(costs[i][0]).add(new int[]{costs[i][1], costs[i][2]});
            list.get(costs[i][1]).add(new int[]{costs[i][0], costs[i][2]});
        }
        
        // int[] = {도착노드, 가중치}
        PriorityQueue<int[]> pq = new PriorityQueue<>((x , y) -> x[1] - y[1]);
        
        pq.offer(new int[]{1, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;
            answer += cur[1];
            
            for (int i = 0; i < list.get(cur[0]).size(); i++) {
                int[] next = list.get(cur[0]).get(i);
                
                if (visited[next[0]]) {
                    continue;
                }
                
                pq.offer(next);
            }
        }
        
        
        
        return answer;
    }
}