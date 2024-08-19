import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int M;
    static char[][] map;
    static int[] redOrigin;
    static int[] blueOrigin;
    static int[] red;
    static int[] blue;
    static int[] dx = {1, 0, -1, 0}; // 하, 우, 상, 좌
    static int[] dy = {0, 1, 0, -1};
    
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        
        redOrigin = new int[2];
        blueOrigin = new int[2];
        
        for (int i = 0; i < N; i++) {
            String rowStr = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = rowStr.charAt(j);
                if (map[i][j] == 'R') {
                    redOrigin[0] = i; redOrigin[1] = j;
                } else if (map[i][j] == 'B') {
                    blueOrigin[0] = i; blueOrigin[1] = j;
                }
            }
        }
        
        
        // 10번의 방향 시퀀스 배열 만들어주기
        int[] directs = {0, 1, 2, 3};
        int[] sequence = new int[10];
        // 하(0), 우(1), 상(2), 좌(3)
        dfs(directs, sequence, 0);
        
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }
    
    public static void dfs(int[] directs, int[] sequence, int depth) {
        if (depth == sequence.length) {
            play(sequence);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            if (depth > 0 && sequence[depth - 1] == directs[i]) {
                // 이전과 같은 방향이면 continue;
                continue;
            }
            sequence[depth] = directs[i];
            dfs(directs, sequence, depth + 1);
        }
    }
    
    public static void play(int[] sequence) {
        char[][] board = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            board[i] = Arrays.copyOf(map[i], map[i].length);
        }
        
        red = Arrays.copyOf(redOrigin, 2);
        blue = Arrays.copyOf(blueOrigin, 2);
        // 만들어진 방향 배열로 공을 굴린다.
        for (int i = 0; i < sequence.length; i++) {
            // 빨간공과 파란공 중 어느것을 먼저 굴릴 것인가? -> 검증 필요
            // sequences[i]가 0이나 2일 때, R과 B가 같은 열에 있는 지
            // 같은 열에 있다면, 0(하)일때는 두 공의 위치 중 x좌표[0]값이 큰 공이 먼저 굴러가야함.
            // 2(상)일때는 x좌표[0]값이 작은 공이 먼저 굴러가야함.
            // 같은 행에 있다면, 1(우)일때는 두 공의 위치 중 y좌표[1]값이 큰 공이 먼저 굴러가야함.
            // 3(좌)일때는 y좌표[1]값이 작은 공이 먼저 굴러가야함.
            boolean redIsArrived = false;
            boolean blueIsArrived = false;
            if (validateMoveFirstRed(sequence[i], red, blue)) {
                redIsArrived = move(board, sequence[i], red);
                blueIsArrived = move(board, sequence[i], blue);
            } else {
                blueIsArrived = move(board, sequence[i], blue);
                redIsArrived = move(board, sequence[i], red);
            }
            
            if (blueIsArrived) {
                break;
            }
            
            if (redIsArrived && blueIsArrived) {
                break;
            }
            
            if (redIsArrived && !blueIsArrived) {
                min = Math.min(min, i + 1);
                break;
            }
        }
    }
    
    public static boolean validateMoveFirstRed(int sequence, int[] red, int[] blue) {
        boolean result = true; // true면 빨간공이 먼저 굴러가도록 기획
        int rx = red[0];
        int ry = red[1];
        int bx = blue[0];
        int by = blue[1];
        
        if (sequence == 0) { // [하]
            if (rx > bx) {
                return true;
            } else if (bx > rx) {
                return false;
            }
        } else if (sequence == 2) { // [상]
            if (rx < bx) {
                return true;
            } else if (bx < rx) {
                return false;
            }
        } else if (sequence == 1) { // [우]
            if (ry > by) {
                return true;
            } else if (by > ry) {
                return false;
            }
        } else if (sequence == 3) { // [좌]
            if (ry < by) {
                return true;
            } else if (by < ry) {
                return false;
            }
        }
        return result;
    }
    
    public static boolean move(char[][] board, int direct, int[] colorBall) {
        // 공을 기울인 방향으로 벽이 만날때까지 이동
        int cx = colorBall[0];
        int cy = colorBall[1];
        char color = board[cx][cy];
        
        while (true) {
            int nx = cx + dx[direct];
            int ny = cy + dy[direct];
            
            // 유효하지 않은 좌표면,
            if (nx < 1 || ny < 1 || nx > board.length - 2 || ny > board[0].length - 2) {
                return false;
            }
            
            // 가다가 벽이나 공을 만나면,
            if (board[nx][ny] == '#' || board[nx][ny] == 'R' || board[nx][ny] == 'B') {
                return false;
            }
            
            // 가다가 구멍을 만나면,
            if (board[nx][ny] == 'O') {
                // 현재 좌표는 '.'으로 바꿔준다.(공이 구멍으로 빠져서 board에서 없애줘야함.)
                board[cx][cy] = '.';
                if (color == 'R') {
                    red[0] = nx;    
                    red[1] = ny;
                } else {
                    blue[0] = nx;
                    blue[1] = ny;
                }
                return true;
            }
            
            board[cx][cy] = '.';
            cx = nx;
            cy = ny;
            board[cx][cy] = color;
            
            if (color == 'R') {
                red[0] = cx;
                red[1] = cy;
            } else {
                blue[0] = cx;
                blue[1] = cy;
            }
        }
    }
}