import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] A;
    static int[] cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        cnt = new int[100000 + 1];
        
        long answer = 0;
        
        for (int L = 1, R = 0; L <= N; L++) {
            while (R + 1 <= N && cnt[A[R + 1]] == 0) {
                R++;
                cnt[A[R]]++;
            }
            answer += R - L + 1;
            
            cnt[A[L]]--;
        }
        System.out.println(answer);
    }
}