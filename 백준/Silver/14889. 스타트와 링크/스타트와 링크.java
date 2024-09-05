import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        visited = new boolean[N];
        
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(min);
    }
    
    static void dfs(int idx, int depth) {
        if (depth == N / 2) {
            calc();
            return;
        }
        
        for (int i = idx; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }
    
    static void calc() {
        int startTeam = 0;
        int linkTeam = 0;
        
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    startTeam += map[i][j];
                    startTeam += map[j][i];
                } else if (!visited[i] && !visited[j]) {
                    linkTeam += map[i][j];
                    linkTeam += map[j][i];
                }
            }
        }
        
        int diff = Math.abs(startTeam - linkTeam);
        
        if (diff == 0) {
            min = 0;
            return;
        }
        
        min = Math.min(min, diff);
    }
}