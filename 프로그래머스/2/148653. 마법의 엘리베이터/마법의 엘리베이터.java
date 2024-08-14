class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey != 0) {
            int value = storey % 10;
            int next = storey / 10;
            int check = next % 10;
            
            if (value <= 4 || (value == 5 && check <= 4)) {
                storey = next;
                answer += value;
            } else {
                storey = next + 1;
                answer += 10 - value;
            }
        }
        return answer;
    }
}