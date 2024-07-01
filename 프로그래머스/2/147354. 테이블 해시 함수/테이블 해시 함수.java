import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (row1, row2) -> row1[col - 1] == row2[col - 1] ? row2[0] - row1[0] : row1[col - 1] - row2[col - 1]);
        
        int[] SiArr = new int[row_end - row_begin + 1];
        
        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            int S_i = 0;
            for (int j = 0; j < data[i].length; j++) {
                S_i += data[i][j] % (i + 1);
            }
            SiArr[i + 1 - row_begin] = S_i;
        }
        
        for (int i = 0; i < SiArr.length; i++) {
            System.out.print(SiArr[i]);
        }
        
        answer = SiArr[0];
        for (int i = 1; i < SiArr.length; i++) {
            answer = answer ^ SiArr[i];
        }
        return answer;
    }
}