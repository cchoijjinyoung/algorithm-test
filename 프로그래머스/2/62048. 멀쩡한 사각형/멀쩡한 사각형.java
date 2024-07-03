class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        
        long temp = 0;
        for (int i = 0; i < w; i++) {
            temp += calc(w, h, i);
        }
        
        answer = temp * 2;
        return answer;
    }
    
    public long calc(int w, int h, long x) {
        long y = h * x / w;
        return y;
    }
}