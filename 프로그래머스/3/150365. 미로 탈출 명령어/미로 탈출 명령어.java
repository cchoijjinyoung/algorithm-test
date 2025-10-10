class Solution {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] chars = {'d', 'l', 'r', 'u'};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = solve(n, m, x, y, r, c, k);
        return answer.length() != k ? "impossible" : answer;
    }
    
    public String solve(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder sb = new StringBuilder();
        while (k > 0) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 1 || ny < 1 || nx > n || ny > m) {
                    continue;
                }
                
                int remainDist = Math.abs(r - nx) + Math.abs(c - ny);
    
                if (remainDist <= k - 1 && ((k - 1 - remainDist) % 2 == 0)) {
                    sb.append(chars[i]);
                    x = nx;
                    y = ny;
                    break;
                }
            }
            k--;
        }
        return sb.toString();
    }
}