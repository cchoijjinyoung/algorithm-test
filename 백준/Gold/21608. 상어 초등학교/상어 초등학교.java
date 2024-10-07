import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[] A;
    static Map<Integer, List<Integer>> hm;
    static int[][] board;
    static int[] dr = new int[]{1, 0, -1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = N * N;
        
        A = new int[M];
        hm = new HashMap<>();
        board = new int[N][N];
        
        for (int i = 1; i <= M; i++) {
            hm.put(i, new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int stuNo = Integer.parseInt(st.nextToken());
            A[i] = stuNo;
            
            for (int j = 0; j < 4; j++) {
                hm.get(stuNo).add(Integer.parseInt(st.nextToken()));    
            }
        }
        
        // 출력할 총 만족도
        int totalHappy = 0;
        
        // 처음 학생은 (1, 1)에 위치한다.
        board[1][1] = A[0];
        
        // 두번째 학생부터 차례대로 순회한다.
        for (int i = 1; i < M; i++) {
            int student = A[i];
            
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x1, x2) -> {
                    // int[] : {r, c, like, empty};
                    if (x1[2] == x2[2]) {
                        if (x1[3] == x2[3]) {
                            if (x1[0] == x2[0]) {
                                return x1[1] - x2[1];
                            }
                            return x1[0] - x2[0];
                        }
                        return x2[3] - x1[3];
                    }
                    return x2[2] - x1[2];
                }
            );
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // 모든 자리에 대해, 4방향의 인접한 자리를 점검
                    if (board[r][c] != 0) {
                        continue;
                    }
                    // 현재 자리는 인접한 자리에 좋아하는 친구가 몇명인가?
                    int like = 0;
                    // 현재 자리의 인접한 자리 중 몇자리가 비어있는가?
                    int empty = 0;
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        
                        // 만약 board를 벗어나면 continue;
                        if (nr < 0 || nc < 0 || nr > N - 1 || nc > N - 1) {
                            continue;
                        }
                        
                        int next = board[nr][nc];
                        if (next != 0) {
                            if (hm.get(student).contains(next)) {
                                like += 1;
                            }
                        } else {
                            empty += 1;
                        }
                    }
                    pq.offer(new int[]{r, c, like, empty});
                }
            }
            if (!pq.isEmpty()) {
                int[] selected = pq.poll();
                board[selected[0]][selected[1]] = student;
            }
        }
        
        // 학생들의 자리를 모두 구했다면, 만족도의 총 합을 구한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = board[i][j];
                
                int happy = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    
                    // 만약 board를 벗어나면 continue;
                    if (nr < 0 || nc < 0 || nr > N - 1 || nc > N - 1) {
                        continue;
                    }
                    
                    int friend = board[nr][nc];
                    if (hm.get(student).contains(friend)) {
                        happy += 1;
                    }
                }
                if (happy > 0) {
                    totalHappy += Math.pow(10, happy - 1);
                }
            }
        }
        System.out.println(totalHappy);
    }
}