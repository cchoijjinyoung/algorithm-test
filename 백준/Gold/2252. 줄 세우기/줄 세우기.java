import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int M;
    static int[] indeg;
    static List<List<Integer>> list;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indeg = new int[N + 1];
        list = new ArrayList<>();
        sb = new StringBuilder();
        
        indeg[0] = -1;
        
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            indeg[to]++;
        }
        
        // 본인보다 앞에 서야할 사람이 없으면 큐에 담는다.
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(' ');
            
            for (int to : list.get(cur)) {
                indeg[to]--;
                if (indeg[to] == 0) {
                    q.offer(to);
                }
            }
        }
        System.out.println(sb.toString());
    }
}