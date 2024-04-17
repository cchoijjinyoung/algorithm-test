class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int total = n * n;
        int cnt = 0;

        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    cnt += 1;
                    for (int k = 0; k < 8; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];

                        if (x >= 0 && x < n && y >= 0 && y < n && board[x][y] == 0) {
                            cnt += 1;
                            board[x][y] = 2;
                        }
                    }
                }
            }
        }
        System.out.println(total - cnt);
        return total - cnt;
    }
}