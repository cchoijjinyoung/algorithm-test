import java.util.*;

class Solution {
    List<Integer> list;
    Queue<int[]> q;
    boolean[][] visited;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    public int[] solution(String[] maps) {
        // maps를 순회할건데,
        // [0][0] 부터 visited가 false면 q에 넣고 시작
        // q가 빌 때까지 +1 해주고, list.add(넓이);
        
        list = new ArrayList<>();
        
        visited = new boolean[maps.length][maps[0].length()];
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    int area = calc(maps);
                    list.add(area);
                }
            }
        }
        
        if (list.isEmpty()) {
            return new int[]{-1};
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        Arrays.sort(answer);
        
        return answer;
    }
    
    public int calc(String[] maps) {
        int result = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            visited[cur[0]][cur[1]] = true;
            result += maps[cur[0]].charAt(cur[1]) - '0';
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (nx < 0 || ny < 0 || nx > maps.length - 1 || ny > maps[0].length() - 1) {
                    continue;
                }
                
                if (visited[nx][ny]) {
                    continue;
                }
                
                if (maps[nx].charAt(ny) == 'X') {
                    continue;
                }
                
                visited[nx][ny] = true;
                
                q.offer(new int[]{nx, ny});
            }
        }
        return result;
    }
}