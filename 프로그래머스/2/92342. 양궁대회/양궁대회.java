import java.util.*;

class Solution {
    int max = 0;
    int[] temp = new int[11];
    
    public int[] solution(int n, int[] info) {
        int[] lion = new int[11];
        
        dfs(n, info, lion, 0);
        
        int[] answer;
        if (max != 0) {
            answer = Arrays.copyOf(temp, temp.length);
        } else {
            answer = new int[]{-1};
        }
        
        return answer;
    }
    
    public void dfs(int n, int[] info, int[] lion, int depth) {
        if (depth == 11) {
            calc(info, lion);
            return;
        }
        for (int i = n; i >= 0; i--) {
            lion[depth] = i;
            dfs(n - i, info, lion, depth + 1);
        }
    }
    
    public void calc(int[] info, int[] lion) {
        int lp = 0;
        int ap = 0;
        
        // 같은 점수에 쏜 화살 갯수가 같으면 어피치가 win
        for (int i = 0; i < info.length; i++) {
            if (lion[i] > info[i]) {
                lp += 10 - i;
            } else if (lion[i] <= info[i] && info[i] > 0) {
                ap += 10 - i;
            }
        }
        int diff = lp - ap;
        
        if (diff > max) {
            max = diff;
            temp = Arrays.copyOf(lion, lion.length);
        } else if (diff == max) {
            for (int i = 10; i >= 0; i--) {
                if (lion[i] > temp[i]) {
                    temp = Arrays.copyOf(lion, lion.length);
                    break;
                } else if (lion[i] < temp[i]) {
                    break;
                } else {
                    continue;
                }
            }
        }
    }
}