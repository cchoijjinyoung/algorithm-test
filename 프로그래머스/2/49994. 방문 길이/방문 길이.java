class Solution {
    int[] dx = {0, 1, -1, 0}; // 상 우 좌 하
    int[] dy = {1, 0, 0, -1};
    public int solution(String dirs) {
        int answer = 0;
        
        boolean[][][] visited = new boolean[11][11][4];
        
        int x = 5;
        int y = 5;
        
        for (int i = 0; i < dirs.length(); i++) {
            int direct = resolve(dirs.charAt(i));
            
            int nx = x + dx[direct];
            int ny = y + dy[direct];
            
            if (nx < 0 || ny < 0 || nx > 10 || ny > 10) {
                continue;
            }
            
            if (visited[x][y][direct] && visited[nx][ny][3 - direct]) {
                x = nx;
                y = ny;
                continue;
            }
            
            visited[x][y][direct] = true;
            x = nx;
            y = ny;
            
            visited[nx][ny][3 - direct] = true;
            answer++;
            
        }
        return answer;
    }
    
    public int resolve(char c) {
        if ('U' == c) {
            return 0;
        } else if ('D' == c) {
            return 3;
        } else if ('L' == c) {
            return 2;
        } else {
            return 1;
        }
    }
}