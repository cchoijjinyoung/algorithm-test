import java.util.*;

class Solution {
    int[][][] priceMap;
    int[] dx = new int[]{1, 0, -1, 0}; // 하, 우, 상, 좌
    int[] dy = new int[]{0, 1, 0, -1};
    public int solution(int[][] board) {
        priceMap = new int[board.length][board[0].length][4];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < 4; k++) {
                    priceMap[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        for (int i = 0; i < 4; i++) {
            priceMap[0][0][i] = 0;    
        }
        
        bfs(board);
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            min = Math.min(min, priceMap[board.length - 1][board.length - 1][i]);
        }
        return min;
    }
    
    public void bfs(int[][] board) {
        // 차가 이동하면서 비용 누적, 방향 체크, 방향이 꺾이면 비용 += 500
        Queue<int[]> q = new LinkedList<>();
        // (0, 0), 0원, 방향
        if (board[0][1] != 1) {
            q.add(new int[]{0, 1, 100, 1});
            priceMap[0][1][1] = 100;
        }
        if (board[1][0] != 1) {
            q.add(new int[]{1, 0, 100, 0});
            priceMap[1][0][0] = 100;
        }
        
        while (!q.isEmpty()) {
            int[] info = q.poll();
            int cx = info[0];
            int cy = info[1];
            int cost = info[2];
            int dir = info[3];
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx < 0 || ny < 0 || nx > board.length - 1 || ny > board[0].length - 1) {
                    continue;
                }
                
                if (board[nx][ny] == 1) continue;
                
                int nextCost = 100;
                
                if (dir != i) {
                    nextCost += 500;
                }
                
                // 현재 이동 시 지불 비용이 이전보다 크면 continue;
                if (cost + nextCost > priceMap[nx][ny][i]) continue;
                priceMap[nx][ny][i] = cost + nextCost;
                q.add(new int[]{nx, ny, cost + nextCost, i});
            }
        }
    }
}