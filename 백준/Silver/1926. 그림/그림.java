import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static int bfs(int x, int y) {
        int result = 0;
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        result++;
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
                    continue;
                }
                
                if (visited[nx][ny]) continue;
                
                if (map[nx][ny] == 0) continue;
                
                q.add(new int[]{nx, ny});
                result++;
                visited[nx][ny] = true;
            }
        }
        return result;
    }
    
    static void pro() {
        int count = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                if (visited[i][j]) continue;
                int result = bfs(i, j);
                count++;
                max = Math.max(max, result);
            }
        }
        System.out.println(count);
        System.out.println(max);
    }
    
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}