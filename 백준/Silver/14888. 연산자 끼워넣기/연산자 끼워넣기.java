import java.util.*;
import java.io.*;

class Main {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int N;
    static int[] numbers;
    static int[] ops;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        ops = new int[4];
        for (int i = 0; i < 4; i++) {
            ops[i] = Integer.parseInt(st.nextToken());
        }
        
        dfs(numbers[0], 1);
        
        System.out.println(max);
        System.out.println(min);
    }
    
    public static void dfs(int num, int depth) {
        if (depth == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            if (ops[i] > 0) {
                ops[i]--;
                
                switch (i) {
                    case 0 : dfs(num + numbers[depth], depth + 1); break;
                    case 1 : dfs(num - numbers[depth], depth + 1); break;
                    case 2 : dfs(num * numbers[depth], depth + 1); break;
                    case 3 : dfs(num / numbers[depth], depth + 1); break;
                }
                ops[i]++;
            }
        }
    }
}