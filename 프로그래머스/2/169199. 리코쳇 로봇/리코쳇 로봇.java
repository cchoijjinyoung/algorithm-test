import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = 0;
        int[] R = new int[2];
        int[] G = new int[2];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    R[0] = i; R[1] = j;
                } else if (board[i].charAt(j) == 'G') {
                    G[0] = i; G[1] = j;
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(R);
        
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        int[][] visited = new int[board.length][board[0].length()];
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int dist = visited[cur[0]][cur[1]];
            
            if (cur[0] == G[0] && cur[1] == G[1]) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0];
                int ny = cur[1];
                
                while (true) {
                    nx += dx[i];
                    ny += dy[i];
                    
                    if (nx < 0 || ny < 0 || 
                        nx > board.length - 1 || ny > board[0].length() - 1 || 
                        board[nx].charAt(ny) == 'D') {
                        break;  
                    }
                }
                
                nx -= dx[i];
                ny -= dy[i];
                
                if (visited[nx][ny] > 0) {
                    continue;
                }
                visited[nx][ny] = dist + 1;
                q.offer(new int[]{nx, ny});
            }
        }
        
        return -1;
    }
}