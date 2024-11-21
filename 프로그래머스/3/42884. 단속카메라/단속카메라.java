import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (r1, r2) -> r1[0] - r2[0]);
        
        answer++;
        int beforeIn = routes[0][0];
        int beforeOut = routes[0][1];
        for (int i = 1; i < routes.length; i++) {
            int in = routes[i][0];
            int out = routes[i][1];
            
            if (in > beforeOut) {
                beforeIn = in;
                beforeOut = out;
                answer++;
                continue;
            }
            beforeIn = Math.max(beforeIn, in);
            beforeOut = Math.min(beforeOut, out);
        }
        return answer;
    }
}