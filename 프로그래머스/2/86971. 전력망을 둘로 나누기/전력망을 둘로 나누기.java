import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        // 인접 리스트 생성
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int minDifference = n;
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            // 전선 하나를 끊어서 두 부분으로 나눔
            graph.get(a).remove(Integer.valueOf(b));
            graph.get(b).remove(Integer.valueOf(a));

            // 각 부분의 송전탑 개수 구함
            boolean[] visited = new boolean[n + 1];
            int countA = dfs(a, graph, visited);
            int countB = n - countA;

            // 최소 차이 갱신
            minDifference = Math.min(minDifference, Math.abs(countA - countB));

            // 전선 다시 연결
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return minDifference;
    }

    private int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        int count = 1;
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, graph, visited);
            }
        }
        return count;
    }
}