class Solution {
    int[] ops = {1, -1};
    int answer = 0;
    public int solution(int[] numbers, int target) {
        
        int[] arr = new int[numbers.length];
        
        dfs(numbers, target, arr, 0);
        
        return answer;
    }
    
    public void dfs(int[] numbers, int target, int[] arr, int depth) {
        if (depth == arr.length) {
            calc(numbers, target, arr);
            return;
        }
        
        for (int i = 0; i < 2; i++) {
            arr[depth] = ops[i];
            dfs(numbers, target, arr, depth + 1);
        }
    }
    
    public void calc(int[] numbers, int target, int[] arr) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            int cur = numbers[i] * arr[i];
            sum += cur;
        }
        if (sum == target) {
            answer++;
        }
    }
}