import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int area = 2 * w + 1;
        int before_end = 0;
        for (int i = 0; i < stations.length; i++) {
            int station = stations[i];
            
            int cur_start = Math.max(1, station - w);
            int cur_end = Math.min(n, station + w);
            
            if (cur_start <= before_end) {
                before_end = cur_end;
                continue;
            }
            
            int dist = cur_start - before_end - 1;
            answer += dist / area;
            if (dist % area != 0) answer++;
            before_end = cur_end;
        }
        
        // 남은 부분
        if (before_end != n) {
            int dist = n - before_end;
            answer += dist / area;
            if (dist % area != 0) answer++;
        }
        
        return answer;
    }
}