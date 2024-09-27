import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int C;
    static int[] points;
    static int answer;
    public static void main(String[] args) throws Exception {
        answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        points = new int[N];
        
        for (int i = 0; i < N; i++) {
            int point = Integer.parseInt(br.readLine());
            points[i] = point;
        }
        Arrays.sort(points);
        solve();
        System.out.println(answer);
    }
    
    public static void solve() {
        int left = 1;
        int right = points[points.length - 1];
        

        while (left <= right) {
            int dist = right - (right - left) / 2;
            int cur = 0;
            int cnt = 1;
            for (int i = 1; i < points.length; i++) {
                if(points[i] - points[cur] >= dist) {
                    cnt++;
                    cur = i;
                }
            }

            if (cnt < C) {
                right = dist - 1;
            } else {
                left = dist + 1;
            }
        }
        answer = left - 1;
    }
}