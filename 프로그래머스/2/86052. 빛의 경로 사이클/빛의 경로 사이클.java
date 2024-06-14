import java.util.*;

class Solution {
    Queue<int[]> q;
    int[] dx = new int[]{-1, 0, 1, 0}; // 상 좌 하 우
    int[] dy = new int[]{0, -1, 0, 1};
    List<Integer> list;
    boolean[][][] visited;
    
    public int[] solution(String[] grid) {
        list = new ArrayList<>();
        visited = new boolean[grid.length][grid[0].length()][4];
        
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length(); j++) {
                    if (visited[i][j][k]) {
                        continue;
                    }
                    q = new LinkedList<>();

                    q.offer(new int[]{i, j, k});
                    visited[i][j][k] = true;

                    int result = calc(grid);

                    list.add(result);
                }
            }
            
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
    }
    
    public int calc(String[] grid) {
        int result = 0;
        while (!q.isEmpty()) {
            // 현재 노드와 방향
            int[] cur = q.poll();
            result += 1;
            
            int nx = cur[0];
            int ny = cur[1];
            int nd = cur[2];
            
            if (grid[cur[0]].charAt(cur[1]) == 'S') {
                nd = cur[2];
                nx = cur[0] + dx[nd];
                ny = cur[1] + dy[nd];
            } else if (grid[cur[0]].charAt(cur[1]) == 'L') {
                nd = (cur[2] + 1) % 4;
                nx = cur[0] + dx[nd];
                ny = cur[1] + dy[nd];
            } else if (grid[cur[0]].charAt(cur[1]) == 'R') {
                nd = (cur[2] - 1 + 4) % 4;
                nx = cur[0] + dx[nd];
                ny = cur[1] + dy[nd];
            }
            
            if (nx < 0 || ny < 0 || nx > grid.length - 1 || ny > grid[0].length() - 1) {
                if (nd == 0) {
                    nx = grid.length - 1;
                } else if (nd == 1) {
                    ny = grid[0].length() - 1;
                } else if (nd == 2) {
                    nx = 0;
                } else if (nd == 3) {
                    ny = 0;
                }
            }
            
            if (visited[nx][ny][nd]) {
                continue;
            }
            
            visited[nx][ny][nd] = true;
            q.offer(new int[]{nx, ny, nd});
        }
        return result;
    }
}