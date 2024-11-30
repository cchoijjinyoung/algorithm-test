import java.util.*;

class Solution {
    int n, m, answer;
    int[] red_start, red_end, blue_start, blue_end;
    int blank = 0; int wall = 5;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
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
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    public void dfs(int[][] maze, int red_x, int red_y, int blue_x, int blue_y, int moveCount) {
        if (red_x == red_end[0] && red_y == red_end[1] && blue_x == blue_end[0] && blue_y == blue_end[1]) {
            answer = Math.min(answer, moveCount);
            return;
        }
    
        for (int i = 0; i < 4; i++) { // 빨간 수레의 4방향 순회
            int red_nx = red_x;
            int red_ny = red_y;
            
            // 도착점이 아닐 때만 움직인다.
            if (red_x != red_end[0] || red_y != red_end[1]) {
                red_nx += dx[i];
                red_ny += dy[i];
                // 빨간 수레의 다음 위치가 밖 or 벽이면, continue;
                if (!validate(maze, red_nx, red_ny)) continue;
                // 빨간 수레의 다음 위치가 이미 방문한 위치면, continue;
                if (red_visited[red_nx][red_ny]) continue;
            }
            for (int j = 0; j < 4; j++) { // 빨간 수레의 위치에 대해, 파란 수레 4방향 순회
                int blue_nx = blue_x;
                int blue_ny = blue_y;
                // 파란 수레가 도착하지 않았을 때만 이동
                if (blue_x != blue_end[0] || blue_y != blue_end[1]) {
                    blue_nx += dx[j];
                    blue_ny += dy[j];
                    // 밖 or 벽이면, continue;
                    if (!validate(maze, blue_nx, blue_ny)) continue;
                    // 이번에 가는 위치가 빨간 수레의 이전 위치면, continue;
                    if (blue_nx == red_x && blue_ny == red_y) continue;
                    // 이미 방문한 위치면, continue;
                    if (blue_visited[blue_nx][blue_ny]) continue;    
                }
                // 서로의 다음 위치가 겹치면, continue;
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