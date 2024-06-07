class Solution {
    int answer = 0;
    int[] signNum = new int[]{1, -1};
    public int solution(int[] numbers, int target) {
        int[] sign = new int[numbers.length];
        
        dfs(numbers, target, sign, 0);
        return answer;
    }
    
    public void dfs(int[] numbers, int target, int[] sign, int depth) {
        if (depth == sign.length) {
            int sum = calc(numbers, sign);
            if (sum == target) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < signNum.length; i++) {
            sign[depth] = signNum[i];
            dfs(numbers, target, sign, depth + 1);
        }
    }
    
    public int calc(int[] numbers, int[] sign) {
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result += numbers[i] * sign[i];
        }
        return result;
    }
}