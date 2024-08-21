import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int[][] board;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int idx = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            
            if (n == 0) {
                break;
            }
            
            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem " + idx + ": " + playGame() + "\n");
            idx++;
        }
        System.out.println(sb.toString());
    }
    
    public static int playGame() {
        int[][] move = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(move[i], Integer.MAX_VALUE);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        
        pq.offer(new int[]{0, 0, board[0][0]});
        move[0][0] = board[0][0];
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cost = cur[2];
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) {
                    continue;
                }
                
                if (board[nx][ny] + cost < move[nx][ny]) {
                    move[nx][ny] = board[nx][ny] + cost;
                    pq.offer(new int[]{nx, ny, move[nx][ny]});
                }
            }
        }
        return move[n - 1][n - 1];
    }
}