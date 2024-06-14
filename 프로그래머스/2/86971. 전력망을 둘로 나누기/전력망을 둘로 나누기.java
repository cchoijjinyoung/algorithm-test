import java.util.*;

class Solution {
    int min = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        List<List<Integer>> list = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < wires.length; i++) {
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }
        
        // wires[0] ~ wires[wires.length - 1]까지 한 개씩 끊어봄.
        for (int i = 0; i < wires.length; i++) {
            // 끊기
            list.get(wires[i][0]).remove(Integer.valueOf(wires[i][1]));
            list.get(wires[i][1]).remove(Integer.valueOf(wires[i][0]));
            
            // 계산 = 전역 변수 min 업데이트
            calc(n, list);
            
            // 다시 연결
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }
        return min;
    }
    
    public void calc(int n, List<List<Integer>> list) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q1 = new LinkedList<>();
        q1.offer(1);
        
        int part1 = 0;
        while (!q1.isEmpty()) {
            int cur = q1.poll();
            visited[cur] = true;
            part1 += 1;
            
            for (int i = 0; i < list.get(cur).size(); i++) {
                int next = list.get(cur).get(i);
                if (visited[next]) {
                    continue;
                }
                
                q1.offer(next);
            }
        }
        
        Queue<Integer> q2 = new LinkedList<>();
        
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            q2.offer(i);
            break;
        }
        
        int part2 = 0;
        while (!q2.isEmpty()) {
            int cur = q2.poll();
            visited[cur] = true;
            part2 += 1;
            
            for (int i = 0; i < list.get(cur).size(); i++) {
                int next = list.get(cur).get(i);
                if (visited[next]) {
                    continue;
                }
                q2.offer(next);
            }
        }
        int dividen = Math.abs(part1 - part2);
        min = Math.min(min, dividen);
    }
}