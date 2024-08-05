import java.util.*;

class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    public int solution(String[] maps) {
        int answer = 0;
        
        int[] S = new int[2];
        int[] E = new int[2];
        int[] L = new int[2];
    
        // S, E, L 을 찾는다.
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                char c = maps[i].charAt(j);
                
                if (c == 'S') {
                    S[0] = i; S[1] = j;
                } else if (c == 'E') {
                    E[0] = i; E[1] = j;
                } else if (c == 'L') {
                    L[0] = i; L[1] = j;
                }
            }
        }
        
        int SL = BFS(maps, S, L);
        int LE = BFS(maps, L, E);
        
        if (SL == -1 || LE == -1) {
            return -1;
        }
        
        return SL + LE;
    }
    
    public int BFS(String[] maps, int[] start, int[] end) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        q.offer(new int[]{start[0], start[1], 0});
        
        // S에서 L로 가는 최소 거리를 구한다.
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            
            visited[cur[0]][cur[1]] = true;
            
            int dist = cur[2];
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (nx == end[0] && ny == end[1]) {
                    return dist + 1;
                }
                
                // 통로가 아니라면 continue;
                if (nx < 0 || ny < 0 || nx > maps.length - 1 || ny > maps[0].length() - 1) {
                    continue;
                }
                
                if (maps[nx].charAt(ny) == 'X') {
                    continue;
                }
                
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }
        return -1;
    }
}