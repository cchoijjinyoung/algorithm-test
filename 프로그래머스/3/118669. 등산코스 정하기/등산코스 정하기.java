import java.util.*;

class Solution {
    static boolean[] isGate;
    static boolean[] isSummit;
    static List<List<int[]>> graph;
    static int[] answer;
    static int N;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        answer = new int[2];
        answer[1] = Integer.MAX_VALUE;
        
        N = n;
        
        isGate = new boolean[n + 1];
        for (int node : gates) {
            isGate[node] = true;
        }
        
        isSummit = new boolean[n + 1];
        for (int node : summits) {
            isSummit[node] = true;
        }
        
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int w = path[2];
            graph.get(from).add(new int[]{to, w});
            graph.get(to).add(new int[]{from, w});
        }
        
        Arrays.sort(summits);
        
        for (int summit : summits) {
            bfs(summit);
        }
        
        return answer;
    }
    
    public void bfs(int summit) {
        int max = Integer.MIN_VALUE;
        boolean[] visited = new boolean[N + 1];
        Queue<int[]> pq = new PriorityQueue<>(
            (n1, n2) -> n1[1] - n2[1]);
        pq.add(new int[]{summit, 0});
        
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int cur = info[0];
            int cw = info[1];
            
            if (answer[1] < cw) {
                return;
            }
            
            visited[cur] = true;
            max = Math.max(max, cw);
            
            if (isGate[cur]) {
                if (max < answer[1]) {
                    answer[1] = max;
                    answer[0] = summit;
                }
                return;
            }
            
            for (int[] edge : graph.get(cur)) {
                int next = edge[0];
                int nw = edge[1];
                if (visited[next]) continue;
                if (isSummit[next]) continue;
                pq.add(new int[]{next, nw});
            }
        }
    }
}