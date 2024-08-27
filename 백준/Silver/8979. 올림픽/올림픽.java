import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (x, y) -> {
                if (x[1] == y[1]) {
                    if (x[2] == y[2]) {
                        return y[3] - x[3];
                    } else {
                        return y[2] - x[2];
                    }
                } else {
                    return y[1] - x[1];
                }
            }
        );
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            
            pq.offer(new int[]{idx, gold, silver, bronze});
        }
        
        int count = 0;
        while (!pq.isEmpty()) {
            count++;
            int[] cur = pq.poll();
            if (cur[0] == K) {
                break;
            }
        }
        
        System.out.println(count);
    }
}