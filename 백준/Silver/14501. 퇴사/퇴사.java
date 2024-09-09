import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N + 1];
        int[] pay = new int[N + 1];
        int[] dp = new int[N + 2];
        int max = 0;
        
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int j = 1; j <= N + 1; j++) {
            dp[j] = Math.max(max, dp[j]);
            
            if (j <= N && j + time[j] <= N + 1) {
                dp[j + time[j]] = Math.max(dp[j] + pay[j], dp[j + time[j]]);
            }
            
            max = Math.max(max, dp[j]);
        }
        System.out.println(max);
    }
}