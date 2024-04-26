import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        int[] wall = new int[n + 1];
        Arrays.fill(wall, 1);
        
        for (int i = 0; i < section.length; i++) {
            wall[section[i]] = 0;
        }
        
        for (int i = 1; i < wall.length; i++) {
            if (wall[i] == 0) {
                for (int j = 0; j < m; j++) {
                    if (i + j > n) {
                        break;
                    }
                    wall[i + j] = 1;    
                }
                answer++;
            }
        }
        return answer;
    }
}