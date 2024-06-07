import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        
        List<Integer> list = new ArrayList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
            
        // maps 순회해서 visited가 false이고, X가 아닌 지점을 찾는다.
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (visited[i][j]) {
                    continue;
                }
                
                if (!valid(i, j, maps)) {
                    continue;
                }
                
                char cur = maps[i].charAt(j);
                
                if (cur == 'X') {
                    continue;
                }
                
                // 찾으면 List에 담을 자원의 수를 초기화한다.
                int day = bfs(maps, visited, i, j, cur);
                // BFS를 돌려서 X가 아닌 땅을 만나면 visited를 true로 만든다.
                list.add(day);
            }
        }
        
        if (list.isEmpty()) {
            return new int[]{-1};
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    public int bfs(String[] maps, boolean[][] visited, int x, int y, char c) {
        int result = 0;
        Queue<Point> q = new LinkedList<>();
        int[] dx = {1, -1, 0, 0}; // 하, 상, 우, 좌
        int[] dy = {0, 0, 1, -1};
        
        q.add(new Point(x, y, (int)(c - '0')));
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            
            visited[cur.x][cur.y] = true;

            result += cur.value;
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (!valid(nx, ny, maps)) {
                    continue;
                }
                
                if (visited[nx][ny]) {
                    continue;
                }
                
                char next = maps[nx].charAt(ny);
                
                if (next == 'X') {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Point(nx, ny, (int)(next - '0')));
            }
        }
        return result;
    }
    
    public boolean valid(int x, int y, String[] maps) {
        if (x < 0 || x > maps.length - 1 || y < 0 || y > maps[0].length() - 1) {
            return false;
        }
        return true;
    }
    
    class Point {
        int x;
        int y;
        int value;
        
        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}