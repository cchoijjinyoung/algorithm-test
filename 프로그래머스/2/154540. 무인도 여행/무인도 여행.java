import java.util.*;

class Solution {
    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};
    boolean[][] visited;
    List<Integer> temp;
    
    public int[] solution(String[] maps) {
        temp = new ArrayList<>();
        
        visited = new boolean[maps.length][maps[0].length()];
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (visited[i][j]) {
                    continue;
                }
                
                if (maps[i].charAt(j) == 'X') {
                    continue;
                }
                
                BFS(maps, i, j);
            }
        }
        Collections.sort(temp);
        
        int[] answer;
        
        if (temp.isEmpty()) {
            answer = new int[]{-1};
        } else {
            answer = new int[temp.size()];
        
            for (int i = 0; i < temp.size(); i++) {
                answer[i] = temp.get(i);
            }
        }
        
        return answer;
    }
    
    public void BFS(String[] maps, int x, int y) {
        int result = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            visited[cur[0]][cur[1]] = true;
            result += (int)(maps[cur[0]].charAt(cur[1]) - '0');
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (nx < 0 || ny < 0 || nx > maps.length - 1 || ny > maps[0].length() - 1) {
                    continue;
                }
                
                if (maps[nx].charAt(ny) == 'X') {
                    continue;
                }
                
                if (visited[nx][ny]) {
                    continue;
                }
                
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        temp.add(result);
    }
}