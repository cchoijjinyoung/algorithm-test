import java.util.*;

class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    public int solution(int[][] maps) {
        int answer = 0;
        int min = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        // 1,1 -> n, m으로 가는 최소 움직임
        // 0은 벽이다.
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1}); //(0, 0)에 움직임 0으로 시작
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int move = cur[2];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx > maps.length - 1 || ny > maps[0].length -1) {
                    continue;
                }
                
                if (maps[nx][ny] == 0) {
                    continue;
                }
                
                if (visited[nx][ny]) {
                    continue;
                }
                
                if (nx == maps.length - 1 && ny == maps[0].length - 1) {
                    min = move + 1;
                }
                
                visited[nx][ny] = true;
                
                q.offer(new int[]{nx, ny, move + 1});
            }
        }
        if (min == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = min;
        }
        return answer;
    }
}