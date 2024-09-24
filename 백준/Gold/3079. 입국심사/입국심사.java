import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] times = new int[N];
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(times);
        long max = (long)M * times[0];
        long left = 0;
        long right = max;
        while (left <= right) {
            long mid = (right + left) / 2;
            long cnt = 0;
            boolean isBig = false;
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
                if (cnt >= M) {
                    isBig = true;
                    break;
                }
            }
            if (isBig) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}