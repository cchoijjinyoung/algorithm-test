class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int L = 1; int R = 100000;
        while (L <= R) {
            int level = (L + R) / 2;
            if (check(diffs, times, limit, level)) {
                R = level - 1;
                answer = level;
            } else {
                L = level + 1;
            }
        }
        return answer;
    }
    
    public boolean check(int[] diffs, int[] times, long limit, int level) {
        long duration = 0;
        
        duration += times[0];
        int time_prev = times[0];
        
        int len = diffs.length;
        for (int i = 1; i < len; i++) {
            int diff = diffs[i];
            int time_cur = times[i];
            
            if (diff <= level) {
                duration += time_cur;
            } else {
                int repeat = diff - level;
                duration += (time_prev + time_cur) * repeat + time_cur;
            }
            time_prev = time_cur;
        }
        
        return duration <= limit;
    }
}