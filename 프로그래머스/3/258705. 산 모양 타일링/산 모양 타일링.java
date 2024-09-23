class Solution {
    public int solution(int n, int[] tops) {
        int answer = 1;
        int beforeAnswer = 0;
        int beforebefore = 0;
        
        for (int top : tops) {
            beforeAnswer = answer;
            
            if (top == 1) {
                answer = ((4 * answer) % 10007 - beforebefore + 10007) % 10007;
            } else {
                answer = ((3 * answer) % 10007 - beforebefore + 10007) % 10007;
            }
            beforebefore = beforeAnswer;
        }
        return answer % 10007;
    }
}