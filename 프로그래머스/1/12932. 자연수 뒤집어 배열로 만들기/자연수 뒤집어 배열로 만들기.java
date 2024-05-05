class Solution {
    public int[] solution(long n) {
        String s = String.valueOf(n);
        
        int[] answer = new int[s.length()];
        
        int idx = 0;
        for (int i = answer.length - 1; i >= 0; i--) {
            answer[i] = s.charAt(idx++) - '0';
        }
        return answer;
    }
}