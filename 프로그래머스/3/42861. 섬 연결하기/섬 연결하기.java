import java.util.*;

class Solution {
    int[] parents;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        
        // 흠....... 크루스칼이란 머냐 가장 짧은 간선을 선택한다. 어떻게아느냐
        Arrays.sort(costs, (x, y) -> x[2] - y[2]);
        
        // 정렬했으니까 반복문돌아서 유니온-파인드해서 parents 배열 업데이트
        for (int i = 0; i < costs.length; i++) {
            if (find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
            }
        }
        return answer;
    }
    
    public void union(int a,  int b) {
        int ap = find(a);
        int bp = find(b);
        
        
        if (ap != bp) {
            parents[bp] = ap;
        }
    }
    
    public int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        
        return parents[a] = find(parents[a]);
    }
}