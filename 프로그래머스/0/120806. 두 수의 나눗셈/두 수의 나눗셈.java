class Solution {
    public int solution(int num1, int num2) {
        int answer = 0;
        double temp = (double) num1 / num2;
        System.out.println(temp);
        double result = temp * 1000;
        answer = (int) result;
        return answer;
    }
}