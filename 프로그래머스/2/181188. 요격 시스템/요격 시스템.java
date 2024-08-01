import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        
        Arrays.sort(targets, (target1, target2) -> target1[0] == target2[0] ? target1[1] - target2[1] : target1[0] - target2[0]);
        
        int start = targets[0][0];
        int end = targets[0][1];
        for (int i = 0; i < targets.length; i++) {
            if (targets[i][0] >= end) {
                answer++;
                start = targets[i][0];
                end = targets[i][1];
            } else {
                start = targets[i][0];
                end = Math.min(end, targets[i][1]);
            }
        }
        return answer;
    }
}