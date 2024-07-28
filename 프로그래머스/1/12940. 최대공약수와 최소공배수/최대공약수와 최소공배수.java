class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        answer[0] = gcd(n, m);
        answer[1] = n * m / answer[0];
        
        // 최소공배수 = 서로의 곱 / 최대공약수
        // 최대공약수 = n과 m이 있을 때, 
        return answer;
    }
    
    public int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        
        return gcd(b % a, a);
    }
}