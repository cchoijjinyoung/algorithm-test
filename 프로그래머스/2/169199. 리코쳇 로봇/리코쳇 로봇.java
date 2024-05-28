import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = -1;
        int[][] visited = new int[board.length][board[1].length()];
        
        Queue<Point> q = new LinkedList<>();
        Point start = null;
        Point goal = null;
        
        // R을 찾는다.
        for (int i = 0; i < board.length; i++) {
            char[] arr = board[i].toCharArray();
            
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 'R') {
                    start = new Point(i, j);
                } else if (arr[j] == 'G') {
                    goal = new Point(i, j);
                }
            }
        }
        
        q.offer(start);
        // R에서부터 상하좌우 움직인다.
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            
            if(board[cur.x].charAt(cur.y) == 'G'){
                answer = visited[cur.x][cur.y];
                break;
            }
            int depth = visited[cur.x][cur.y];
            
            for (int i = 0; i < 4; i++) {
            // 상하좌우 움직일 때는 다음 위치가 돌과 벽일 때까지 이동해야한다.
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                while (true) {
                    if (isWall(board, nx, ny)) {
                        // 벽이나 돌이면, 그 전 위치가 다음 위치
                        break;
                    }
                    nx += dx[i];
                    ny += dy[i];
                }
                
                nx = nx - dx[i];
                ny = ny - dy[i];
                Point next = new Point(nx, ny);
                
                if (visited[nx][ny] > 0 && visited[nx][ny] <= depth + 1) {
                    continue;
                } else {
                    visited[nx][ny] = depth + 1;
                    q.offer(next);
                }
            }
        }
        answer = visited[goal.x][goal.y] != 0 ? visited[goal.x][goal.y] : -1;
        return answer;
    }
    
    public boolean isWall(String[] board, int nx, int ny) {
        if (nx < 0 || nx > board.length - 1 || ny < 0 || ny > board[0].length() - 1) {
            return true;
        }
        
        if (board[nx].charAt(ny) == 'D') {
            return true;
        }
        
        return false;
    }
    
    class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}