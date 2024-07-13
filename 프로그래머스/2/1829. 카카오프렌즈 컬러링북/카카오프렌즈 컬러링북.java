import java.util.*;

class Solution {
    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};
    
    boolean[][] visited;
    
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;
    int size = 0;
    
    public int[] solution(int m, int n, int[][] picture) {
        // m : 세로, n : 가로
        visited = new boolean[m][n];
        
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] == 0) {
                    continue;
                }
                
                if (visited[i][j]) {
                    continue;
                }
                numberOfArea++;
                bfs(i, j, picture[i][j] ,picture);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public void bfs(int x, int y, int color, int[][] picture) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        
        size++; // 현재 영역 넓이 + 1
        
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (nx < 0 || nx > picture.length - 1 || ny < 0 || ny > picture[0].length - 1) {
                    continue;
                }
                
                if (visited[nx][ny]) {
                    continue;
                }
                
                if (picture[nx][ny] != color) {
                    continue;
                }
                
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                size++;
            }
        }
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
        size = 0; // 영역 다시 초기화
    }
}