import java.util.*;

class Solution {
    List<List<Integer>> list;
    int answer = 0;
    public int solution(int[] info, int[][] edges) {
        int len = info.length;
        
        list = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            list.add(new ArrayList<Integer>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            
            int parent = edge[0];
            int child = edge[1];
            
            list.get(parent).add(child);
        }
        
        // from, to, sheep, wolf
        int root = 0;
        for (int i = 0; i < list.get(root).size(); i++) {
            boolean[] visited = new boolean[len];
            visited[root] = true;
            Queue<int[]> q = new LinkedList<>();
            int to = list.get(root).get(i);
            q.offer(new int[]{root, to, 1, 0});
            solve(len, info, q, visited);
        }
        return answer;
    }
    
    public void solve(int len, int[] info, Queue<int[]> q, boolean[] visited) {
        int root = 0;
        int sheep = 1;
            
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int from = cur[0];
            int to = cur[1];
            sheep = cur[2];
            int wolf = cur[3];
            
            // 만약 도착 지점이 늑대면,
            if (info[to] == 1) {
                wolf += 1;
                if (sheep == wolf) { // 늑대한테 잡아먹히는 지 확인
                    answer = Math.max(answer, sheep);
                    continue;
                }
            } else {
                sheep += 1;
            }
            
            visited[to] = true;
            // 양, 늑대 둘다 갈 수 있다면, to가 갈 수 있는 목적지를 큐에 넣는다.
            // root로 올라가서, 순회해야함
            // visited가 true인 노드의 자식들 중에 visited가 false인 노드가 타켓
            List<Integer> targets = new ArrayList<>();
            Queue<Integer> finder = new LinkedList<>();
            finder.offer(root);
            
            while (!finder.isEmpty()) {
                int parent = finder.poll();
                List<Integer> nexts = list.get(parent);
                
                for (int i = 0; i < nexts.size(); i++) {
                    int child = nexts.get(i);
                    
                    if (visited[child]) {
                        finder.offer(child);
                    } else {
                        targets.add(child);
                    }
                }
            }
            // 타겟리스트를 순회해서 큐에 넣는다.
            for (int target : targets) {
                q.offer(new int[]{to, target, sheep, wolf});
                solve(len, info, q, visited);
            }
            visited[to] = false;
        }
        answer = Math.max(answer, sheep);
    }
}