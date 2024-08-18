class Solution {
    public int solution(int [][]board) {
        int max = 0;
        
        // 한 좌표는 왼쪽대각선, 왼쪽, 위의 세가지 경우의 최솟값 + 1의 사각형을 만들수있음.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                
                if (i - 1 < 0 || j - 1 < 0) {
                    max = Math.max(max, board[i][j]);
                    continue;
                }
                
                int leftTop = board[i - 1][j - 1];
                int left = board[i][j - 1];
                int top = board[i - 1][j];
                
                board[i][j] = Math.min(Math.min(leftTop, left), top) + 1;
                max = Math.max(max, board[i][j]);
            }
        }
        
        return max * max;
    }
}