class Solution {
    public double solution(int[] numbers) {
        double answer = 0;
        
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        
        answer = sum / numbers.length;
        if (sum % numbers.length > 0) {
            answer += 0.5;
        }
        return answer;
    }
}