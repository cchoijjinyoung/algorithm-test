class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        // n 혹은 n + 1에 접근가능
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    triangle[i][j] = triangle[i - 1][0] + triangle[i][j];
                } else if (j == triangle[i].length - 1) {
                    triangle[i][j] = triangle[i - 1][j - 1] + triangle[i][j];
                } else {
                    triangle[i][j] = Math.max(triangle[i - 1][j - 1] + triangle[i][j], triangle[i - 1][j] + triangle[i][j]);
                }
            }
        }
        
        for (int i = 0; i < triangle[triangle.length - 1].length; i++) {
            answer = Math.max(answer, triangle[triangle.length - 1][i]);
        }
        return answer;
    }
}