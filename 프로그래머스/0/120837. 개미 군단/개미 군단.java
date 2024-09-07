class Solution {
    public int solution(int hp) {
        int answer = 0;
        
        int a = hp / 5;
        hp %= 5;
        int b = hp / 3;
        hp %= 3;
        
        answer = a + b + hp;
        return answer;
    }
}