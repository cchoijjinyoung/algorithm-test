class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[]{0, sequence.length - 1};
        
        int lt = 0;
        int rt = 0;
        int sum = sequence[0];
        while (lt < sequence.length && rt < sequence.length) {
            if (sum == k) {
                compare(lt, rt, answer);
                if (rt == sequence.length - 1) {
                    break;
                }
                sum -= sequence[lt++];
                sum += sequence[++rt];
            } else if (sum > k) {
                sum -= sequence[lt++];
            } else if (sum < k) {
                if (rt == sequence.length - 1) {
                    break;
                }
                sum += sequence[++rt];
            }
        }
        return answer;
    }
        
    public void compare(int lt, int rt, int[] answer) {
        if (answer[1] - answer[0] > rt - lt) {
            answer[0] = lt;
            answer[1] = rt;
        }
    }
}