import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        int m = maps.length;
        int n = maps[0].length;
        
        maps[m - 1][n - 1] = Integer.MAX_VALUE;
        
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[]{0, 0});
        
        while (!q.isEmpty()) {
            if (visited[m - 1][n - 1]) {
                break;
            }
            int[] cur = q.poll();
            visited[cur[0]][cur[1]] = true;
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) { 
                    continue;
                }
                
                if (maps[nx][ny] == 0) {
                    continue;
                }
                
                if (visited[nx][ny]) {
                    continue;
                }
                
                maps[nx][ny] = maps[cur[0]][cur[1]] + 1;
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        
        if (maps[m - 1][n - 1] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return maps[m - 1][n - 1];
        }
    }
}