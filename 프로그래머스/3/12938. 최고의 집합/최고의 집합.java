class Solution {
    public int[] solution(int n, int s) {
        int div = s / n;
        
        if (div == 0) return new int[]{-1};
        
        int mod = s % n;
        
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = div;
        }
        
        for (int i = 1; i <= mod; i++) {
            answer[n - i] += 1;
        }
        return answer;
    }
}