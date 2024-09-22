class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int n = board.length;
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        String color = board[h][w];
        for (int i = 0; i < 4; i++) {
            int nh = h + dx[i];
            int nw = w + dy[i];
            
            if (nh < 0 || nw < 0 || nh > n - 1 || nw > n - 1) {
                continue;
            }
            
            if (board[nh][nw].equals(color)) {
                answer++;
            }
        }
        return answer;
    }
}