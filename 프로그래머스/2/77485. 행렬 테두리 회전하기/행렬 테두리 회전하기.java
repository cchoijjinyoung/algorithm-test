class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] board = new int[rows][columns];
        
        int number = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = number++;
            }
        }
        
        for (int q = 0; q < queries.length; q++) {
            int x1 = queries[q][0] - 1;
            int y1 = queries[q][1] - 1;
            int x2 = queries[q][2] - 1;
            int y2 = queries[q][3] - 1;
            
            int min = Integer.MAX_VALUE;
            
            int temp = board[x1][y1];
            
            for (int i = 0; i < x2 - x1; i++) {
                min = Math.min(min, board[x1 + i][y1]);
                board[x1 + i][y1] = board[x1 + i + 1][y1];
            }
            
            for (int i = 0; i < y2 - y1; i++) {
                min = Math.min(min, board[x2][y1 + i]);
                board[x2][y1 + i] = board[x2][y1 + i + 1];
            }
            
            for (int i = 0; i < x2 - x1; i++) {
                min = Math.min(min, board[x2 - i][y2]);
                board[x2 - i][y2] = board[x2 - i - 1][y2];
            }
            
            for (int i = 0; i < y2 - y1 - 1; i++) {
                min = Math.min(min, board[x1][y2 - i]);
                board[x1][y2 - i] = board[x1][y2 - i - 1];
            }
            
            min = Math.min(min, board[x1][y1 + 1]);
            board[x1][y1 + 1] = temp;
            
            answer[q] = min;
        }
        
        return answer;
    }
}