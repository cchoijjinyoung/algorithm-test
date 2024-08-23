import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 정점의 수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수
        int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점 번호

        graph = new int[N + 1][N + 1]; // 1부터 시작
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u][v] = graph[v][u] = 1; // 양방향 간선
        }

        visited = new boolean[N + 1];
        dfs(V);

        System.out.println();

        visited = new boolean[N + 1];
        bfs(V);
    }

    // dfs : 깊이우선탐색(재귀)
    public static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        if (v == graph.length) {
            return;
        }
        for (int i = 1; i < graph.length; i++) {
            if (graph[v][i] == 1 && visited[i] == false) {
                dfs(i);
            }
        }
    }

    // bfs : 너비우선탐색(큐)
    public static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = true;
        System.out.print(v + " ");

        while (!q.isEmpty()) {
            int tmp = q.poll();
            for (int i = 1; i < graph.length; i++) {
                if (graph[tmp][i] == 1 && visited[i] == false) {
                    q.add(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }
}