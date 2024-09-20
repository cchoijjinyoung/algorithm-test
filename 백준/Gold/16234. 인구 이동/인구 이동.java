import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static int[][] copyMap;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        int days = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 인구 이동 -> 반복문 : 국경선이 열리면 계속 반복
        while (true) {
            int openCnt = 0;
            copyMap = copy(map);
            // together 이차원 배열 필요(이미 연합에 소속되어 있는 지 체크)
            // - 인구 이동을 할 때마다 연합을 만들어주기 위함
            boolean[][] together = new boolean[N][N];
            // map[0][0]부터 bfs.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (together[i][j]) {
                        continue;
                    }
                    openCnt = bfs(openCnt, i, j, together);
                }
            }
            if (openCnt == 0) {
                break;
            }
            map = copy(copyMap);
            days++;
        }
        System.out.println(days);
    }
    
    public static int[][] copy(int[][] map) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i] = Arrays.copyOf(map[i], N);
        }
        return result;
    }
    
    public static int bfs(int openCnt, int i, int j, boolean[][] together) {
        int total = 0;
        Queue<int[]> countrys = new LinkedList<>();
        
        Queue<int[]> q = new LinkedList<>();
        countrys.offer(new int[]{i, j});
        q.offer(new int[]{i, j});
        together[i][j] = true;
        total += map[i][j];
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            
            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                
                if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
                    continue;
                }
                
                if (together[nx][ny]) {
                    continue;
                }
                
                if (canTogether(map[cx][cy], map[nx][ny])) {
                    openCnt++;
                    countrys.offer(new int[]{nx, ny});
                    q.offer(new int[]{nx, ny});
                    together[nx][ny] = true;
                    total += map[nx][ny];
                }
            }
        }
        // 큐 다 돌면, 각나라 인구 수를 total/나라 수로 바꿔줘야함.
        if (countrys.size() > 1) {
            int value = total / countrys.size();
            while (!countrys.isEmpty()) {
                int[] country = countrys.poll();
                copyMap[country[0]][country[1]] = value;
            }
        }
        return openCnt;
    }
    
    public static boolean canTogether(int p1, int p2) {
        int diff = Math.abs(p1 - p2);
        if (L <= diff && diff <= R) {
            return true;
        }
        return false;
    }
}