import java.util.*;
import java.io.*;

class Main {
    static int H;
    static int W;
    static int N;
    static int M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        System.out.println((1 + (H - 1) / (N + 1)) * (1 + (W - 1) / (M + 1)));
    }
}