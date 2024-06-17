class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        for (int i = 0; i <= d; i += k) { // x 축
            double y = Math.sqrt(Math.pow(d, 2) - Math.pow(i, 2)); // 현재 x에 대한 최대 y좌표
            answer += (long) (y / k) + 1;
        }
        return answer;
    }
}