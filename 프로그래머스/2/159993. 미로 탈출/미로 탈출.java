import java.util.*;

class Solution {
    // 상 하 좌 우
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        int answer = 0;
        int[] lever = new int[2];
        int[] start = new int[2];
        int[] exit = new int[2];
        // start -> 레버 최소 수 + 레버 -> 문 최소 수
        
        for (int i = 0; i < maps.length; i++) {
            char[] arr = maps[i].toCharArray();
            
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                } else if (arr[j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (arr[j] == 'E') {
                    exit[0] = i;
                    exit[1] = j;
                }
            }
        }
        int a = find(start, lever, maps);
        int b = find(lever, exit, maps);
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = a + b;
        }
        return answer;
    }
    
    public int find(int[] d1, int[] d2, String[] maps) {
        Point start = new Point(d1[0], d1[1]);
        Queue<Point> q = new LinkedList<>();
        int[][] visited = new int[maps.length][maps[0].length()];
        
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        
        q.offer(start);
        visited[start.x][start.y] = 0;
        
        while (!q.isEmpty()) {
            // 4방향으로 이동한다.
            // 다음 위치가 벽이나 밖이면 continue;
            Point cur = q.poll();
            int cnt = visited[cur.x][cur.y];
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (!validation(nx, ny, maps)) {
                    continue;
                }
                int temp = cnt + 1;
                
                if (visited[nx][ny] > temp) {
                    visited[nx][ny] = temp;
                    q.offer(new Point(nx, ny));
                }
            }
        }
        
        return visited[d2[0]][d2[1]];
    }
    
    public boolean validation(int x, int y, String[] maps) {
        if (x < 0 || y < 0 || x >= maps.length || y >= maps[0].length()) {
            return false;
        }
        
        if (maps[x].charAt(y) == 'X') {
            return false;
        }
        return true;
    }
    
    class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // j를 i라 적고, cnt + 1을 cnt++로 하였따..
}