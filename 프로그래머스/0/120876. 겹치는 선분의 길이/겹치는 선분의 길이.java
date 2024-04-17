class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        int[] arr = new int[201];
        for (int i = 0; i < lines.length; i++) {
            // line의 start부터 end까지 지나온 길을 배열에 체크
            int start = lines[i][0];
            int end = lines[i][1];
            for (int j = start + 100; j < end + 100; j++) {
                arr[j]++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 1) answer++;
        }
        return answer;
    }
}