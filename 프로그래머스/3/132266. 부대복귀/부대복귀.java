import java.util.*;

class Solution {
    List<List<Integer>> graph;
    int[] dist;
    boolean[] visited;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 다익스트라
        int[] answer = new int[sources.length];
        graph = new ArrayList<>();
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        visited = new boolean[n + 1];
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        dijkstra(destination);
        
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
    
    public void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        pq.add(new int[]{start, 0});
        visited[start] = true;
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int cur = info[0];
            int w = info[1];
            
            for (int next : graph.get(cur)) {
                if (visited[next]) continue;
                visited[next] = true;
                dist[next] = w + 1;
                pq.add(new int[]{next, w + 1});
            }
        }
    }
}