class Solution {
    public int solution(String[] board) {
        int answer = 1;
        
        int Ocnt = 0;
        int Xcnt = 0;
        
        // 1) X의 갯수가 더 많은 경우
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'O') {
                    Ocnt++;
                } else if (board[i].charAt(j) == 'X') {
                    Xcnt++;
                }
            }
        }
        
        if (Xcnt > Ocnt) {
            return 0;
        }
        
        // O가 끝냈는데? X와 O갯수가 같다. return 0
        if (check(board, 'O') && Xcnt == Ocnt) {
            return 0;
        }
        
        // X가 끝냈는데? O가 갯수가 더 많다 return 0
        if (check(board, 'X') && Xcnt < Ocnt) {
            return 0;
        }
        
        if (check(board, 'O') && check(board, 'X')) {
            return 0;
        }
        
        if (Ocnt > 0 && Xcnt < Ocnt - 1) {
            return 0;
        }
        
        return answer;
    }
    
    public boolean check(String[] board, char c) {
        for (int i = 0; i < board.length; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                return true;
            }
        }
        
        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == c && board[1].charAt(j) == c && board[2].charAt(j) == c) {
                return true;
            }
        }
        
        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) {
            return true;
        }
        
        if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) {
            return true;
        }
        return false;
    }
}