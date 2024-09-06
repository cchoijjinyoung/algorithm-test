import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int L;
    static int[][] map;
    static int cnt = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < N; i++) {
            if (checkPath(i, 0, true)) {
                cnt++;
            }
            
            if (checkPath(0, i, false)) {
                cnt++;
            }
        }
        
        System.out.println(cnt);
    }
    
    static boolean checkPath(int x, int y, boolean flag) {
        int[] height = new int[N];
        boolean[] visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            if (flag) {
                height[i] = map[x][i];
            } else {
                height[i] = map[i][y];
            }
        }
        
        for (int i = 0; i < N - 1; i++) {
            int diff = height[i] - height[i + 1];
            if (diff == 0) {
                continue;
            } else if (diff == 1) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || height[i + 1] != height[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            } else if (diff == -1) {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || height[i] != height[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}