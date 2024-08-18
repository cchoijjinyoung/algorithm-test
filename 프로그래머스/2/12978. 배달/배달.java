import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        // 큐에 넣는다?
        // 일단 양방향 연결을 해야함.
        // 각 나라가 어디로 갈 수 있는지 리스트로 저장.
        // 이중리스트 필요
        List<List<int[]>> list = new ArrayList<>();
        
        // 각 나라별 리스트 초기화
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        
        // road를 순회하면서 양방향 연결
        for (int i = 0; i < road.length; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int dist = road[i][2];
            
            list.get(from).add(new int[]{to, dist});
            list.get(to).add(new int[]{from, dist});
        }
        
        boolean[] visited = new boolean[N + 1];
        int[] dists = new int[N + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> q = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        q.offer(new int[]{1, 0});
        dists[1] = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            int from = cur[0];
            int dist = cur[1];
            
            if (visited[from]) {
                continue;
            }
            
            visited[from] = true;
            
            for (int i = 0; i < list.get(from).size(); i++) {
                int[] next = list.get(from).get(i);
                int to = next[0];
                int nextDist = next[1];
                
                dists[to] = Math.min(dists[to], dist + nextDist);
                q.offer(new int[]{to ,dists[to]});
            }
        }
        
        for (int i = 1; i < dists.length; i++) {
            if (dists[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}