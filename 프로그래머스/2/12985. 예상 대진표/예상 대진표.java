class Solution {
    public int solution(int n, int a, int b) {
        int answer = 0;
        
        while (true) {
            answer++;
            a++;
            b++;
            
            a /= 2;
            b /= 2;
            
            if (a == b) {
                break;
            }
        }
        return answer;
    }
}