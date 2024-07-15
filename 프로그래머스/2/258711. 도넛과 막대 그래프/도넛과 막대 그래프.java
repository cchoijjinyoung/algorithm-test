import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int[] inCount = new int[1000001];
        int[] outCount = new int[1000001];
        
        Map<Integer, List<Integer>> hm = new HashMap<>();
        
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            outCount[from]++;
            inCount[to]++;
            
            if (hm.get(from) == null) {
                hm.put(from, new ArrayList<Integer>());
            }
            
            if (hm.get(to) == null) {
                hm.put(to, new ArrayList<Integer>());
            }
            
            hm.get(from).add(to);
        }
        
        int max = Integer.MIN_VALUE;
        int createNode = 0;
        for (Map.Entry<Integer, List<Integer>> entry : hm.entrySet()) {
            if (inCount[entry.getKey()] == 0 && outCount[entry.getKey()] > 0) {
                if (max < outCount[entry.getKey()]) {
                    max = outCount[entry.getKey()];
                    createNode = entry.getKey();
                }
            }
        }
        answer[0] = createNode;
        
        boolean[] visited = new boolean[1000001];
        
        for (int i = 0; i < hm.get(createNode).size(); i++) {
            Queue<Integer> q = new LinkedList<>();
            int node = 0;
            int edge = 0;
            
            int firstNode = hm.get(createNode).get(i);
            q.offer(firstNode);
            visited[firstNode] = true;
            node++;
            
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (hm.get(cur).isEmpty()) {
                    continue;
                }
                List<Integer> nexts = hm.get(cur);
                
                for (int next : nexts) {
                    if (visited[next]) {
                        edge++;
                        continue;
                    }
                    q.offer(next);
                    visited[next] = true;
                    node++;
                    edge++;
                }
            }
            // q가 끝났을때, 정점수와 간선수 비교
            if (node == edge) {
                answer[1]++;
            } else if (node == edge - 1) {
                answer[3]++;
            } else {
                answer[2]++;
            }
        }
        
        return answer;
    }
}