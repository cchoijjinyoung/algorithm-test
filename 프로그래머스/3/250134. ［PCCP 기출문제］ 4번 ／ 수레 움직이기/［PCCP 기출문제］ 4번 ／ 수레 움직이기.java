import java.util.*;

class Solution {
    int[] red_start, red_end, blue_start, blue_end;
    int blank = 0; int wall = 5;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    int n, m, answer;
    boolean[][] red_visited, blue_visited;
    public int solution(int[][] maze) {
        answer = Integer.MAX_VALUE;
        
        n = maze.length;
        m = maze[0].length;
        
        // 시작점, 도착점 초기화
        findPoint(maze);
        
        // 깊이 탐색
        red_visited = new boolean[n][m];
        blue_visited = new boolean[n][m];
        dfs(maze, red_start[0], red_start[1], blue_start[0], blue_start[1], 0);
        
        if (answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }
    
    public void dfs(int[][] maze, int red_x, int red_y, int blue_x, int blue_y, int moveCount) {
        if (red_x == red_end[0] && red_y == red_end[1] && blue_x == blue_end[0] && blue_y == blue_end[1]) {
            answer = Math.min(answer, moveCount);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int red_nx = red_x;
            int red_ny = red_y;
            if (red_x != red_end[0] || red_y != red_end[1]) {
                red_nx += dx[i];
                red_ny += dy[i];
                if (!validate(maze, red_nx, red_ny)) continue;
                if (red_visited[red_nx][red_ny]) continue;
            }
            for (int j = 0; j < 4; j++) {
                int blue_nx = blue_x;
                int blue_ny = blue_y;
                if (blue_x != blue_end[0] || blue_y != blue_end[1]) {
                    blue_nx += dx[j];
                    blue_ny += dy[j];
                    if (!validate(maze, blue_nx, blue_ny)) continue;
                    if (red_x == blue_nx && red_y == blue_ny) continue;
                    if (blue_visited[blue_nx][blue_ny]) continue;    
                    
                }
                if (red_nx == blue_nx && red_ny == blue_ny) continue;
                red_visited[red_nx][red_ny] = true;
                blue_visited[blue_nx][blue_ny] = true;
                
                dfs(maze, red_nx, red_ny, blue_nx, blue_ny, moveCount + 1);
                red_visited[red_nx][red_ny] = false;
                blue_visited[blue_nx][blue_ny] = false;
            }
        }
    }
    
    public boolean validate(int[][] maze, int x, int y) {
        if (x < 0 || y < 0 || x > n - 1 || y > m - 1) return false;
        if (maze[x][y] == wall) return false;
        return true;
    }
    
    public void findPoint(int[][] maze) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    red_start = new int[]{i, j};
                } else if (maze[i][j] == 2) {
                    blue_start = new int[]{i, j};
                } else if (maze[i][j] == 3) {
                    red_end = new int[]{i, j};
                } else if (maze[i][j] == 4) {
                    blue_end = new int[]{i, j};
                }
            }
        }
    }
}