import java.util.*;

class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public int solution(String[] board) {
        int answer = 0;
        int[] sp = new int[2];
        int[] gp = new int[2];
        
        // 시작점과 골을 찾는다.
        for (int i = 0; i < board.length; i++) {
            char[] arr = board[i].toCharArray();
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 'R') {
                    sp[0] = i; sp[1] = j;
                } else if (arr[j] == 'G') {
                    gp[0] = i; gp[1] = j;
                }
            }
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length()];
        
        // 시작점을 큐에 넣는다.
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sp[0], sp[1], 0});
        
        // 방문배열에 체크한다.
        visited[sp[0]][sp[1]] = true;
        
        // bfs를 돌린다. 멈춘 지점에서 4방향으로 움직인다.
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            int nx;
            int ny;
            int dist = cur[2];
            
            for (int i = 0; i < 4; i++) {
                nx = cur[0];
                ny = cur[1];
                // 다음 위치가 돌이나 벽일 때까지 움직인다.
                while (true) {
                    nx += dx[i];
                    ny += dy[i];
                
                    if (nx < 0 || ny < 0 || nx > board.length - 1 || ny > board[0].length() - 1) {
                        break;
                    }
                    
                    if (board[nx].charAt(ny) == 'D') {
                        break;
                    }
                }
                // 돌이나 벽의 전 위치에 서있음.
                nx -= dx[i];
                ny -= dy[i];
                
                if (nx == gp[0] && ny == gp[1]) {
                    return dist + 1;
                }
            
                if (visited[nx][ny]) {
                    continue;
                }
                
                q.offer(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }
        
        return -1;
    }
}