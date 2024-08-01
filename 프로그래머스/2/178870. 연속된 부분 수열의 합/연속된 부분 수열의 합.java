class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int distance = Integer.MAX_VALUE;
        
        int lt = 0, rt = 0;
        int sum = sequence[lt];
        
        while (rt < sequence.length) {
            if (sum == k) {
                if (distance > rt - lt) {
                    distance = rt - lt;
                    answer[0] = lt;
                    answer[1] = rt;
                }
                sum -= sequence[lt];
                lt++;
            } else if (sum < k) {
                rt++;
                if (rt < sequence.length) {
                    sum += sequence[rt];
                }
            } else if (sum > k) {
                sum -= sequence[lt];
                lt++;
            }
        }
        return answer;
    }
}