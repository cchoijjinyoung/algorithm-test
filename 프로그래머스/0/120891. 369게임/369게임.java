class Solution {
    public int solution(int order) {
        int answer = 0;
        char[] cArr = String.valueOf(order).toCharArray();
        
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == '3' || cArr[i] == '6' || cArr[i] == '9') {
                answer++;
            }
        }
        return answer;
    }
}