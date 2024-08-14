class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for (int i = 0; ; i++) {
            int x = i * k;
            
            if (x > d) {
                break;
            }
            
            double y = Math.sqrt(Math.pow((long)d, 2) - Math.pow((long)x , 2));
            answer += (long)y / k + 1;
        }
        return answer;
    }
}