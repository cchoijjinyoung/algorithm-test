import java.lang.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        double a = Math.sqrt(n);
        
        boolean flag = false;
        for (long i = 1; i <= a; i++) {
            if (i * i == n) {
                flag = true;
                answer = (i + 1) * (i + 1);
                break;
            }
        }
        if (!flag) answer = -1;
        return answer;
    }
}