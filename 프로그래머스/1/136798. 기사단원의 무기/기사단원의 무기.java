class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        int[] arr = new int[number + 1];
        
        for (int i = 1; i <= number; i++) {
            for (int j = i; j <= number; j += i) {
                arr[j]++;
            }
        }
        
        for (int i : arr) {
            if (i > limit) {
                answer += power;
            } else {
                answer += i;    
            }
        }
        
        return answer;
    }
}