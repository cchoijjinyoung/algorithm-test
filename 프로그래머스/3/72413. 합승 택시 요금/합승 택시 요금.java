import java.util.*;

class Solution {
    int MAX = Integer.MAX_VALUE;
    int N;
    List<Node>[] graph;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        int answer = MAX;
        
        graph = new ArrayList[N + 1];
        
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < fares.length; i++) {
            int[] fare = fares[i];
            graph[fare[0]].add(new Node(fare[1], fare[2]));
            graph[fare[1]].add(new Node(fare[0], fare[2]));
        }
        
        int[] minS = dijkstra(s);
        int[] minA = dijkstra(a);
        int[] minB = dijkstra(b);
        
        for (int i = 1; i <= N; i++) {
            answer = Math.min(answer, minS[i] + minA[i] + minB[i]);
        }
        
        return answer;
    }
    
    public int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, MAX);
        
        dist[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node from = pq.poll();
            
            for (Node to : graph[from.node]) {
                if (dist[to.node] > from.cost + to.cost) {
                    dist[to.node] = from.cost + to.cost;
                    pq.offer(new Node(to.node, from.cost + to.cost));
                }
            }
        }
        return dist;
    }
    
    public class Node {
        int node;
        int cost;
        
        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}