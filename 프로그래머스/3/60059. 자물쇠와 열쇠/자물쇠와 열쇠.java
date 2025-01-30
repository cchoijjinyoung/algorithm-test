class Solution {
    static int M;
    static int N;
    public boolean solution(int[][] key, int[][] lock) {
        // 4 * (N + (M - 1)) * (M - 1)
        M = key.length;
        N = lock.length;
        
        // 열쇠를 평행 이동
        for (int i = 0; i < 4; i++) {
            for (int shift_row = -M + 1; shift_row < N; shift_row++) {
                for (int shift_col = -M + 1; shift_col < N; shift_col++) {
                    if (possible(key, lock, shift_row, shift_col)) {
                        return true;
                    }
                }
            }
            key = turn(key);
        }
        return false;
    }
    
    static boolean possible(int[][] key, int[][] lock, int shift_row, int shift_col) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                int shift_x = x - shift_row;
                int shift_y = y - shift_col;
                
                if (lock[x][y] == 0) {
                    if (shift_x < 0 || shift_y < 0 || shift_x > M - 1 || shift_y > M - 1) {
                        return false;
                    }
                    if (key[shift_x][shift_y] == 0) return false;
                } else if (lock[x][y] == 1) {
                    if (shift_x < 0 || shift_y < 0 || shift_x > M - 1 || shift_y > M - 1) {
                        continue;
                    }
                    if (key[shift_x][shift_y] == 1) return false;
                }
            }
        }
        return true;
    }
    
    static int[][] turn(int[][] key) {
        int[][] result = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                result[i][j] = key[j][M - 1 - i];
            }
        }
        return result;
    }
}