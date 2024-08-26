import java.util.*;
import java.io.*;

class Main {
    static int A;
    static int B;
    static int C;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while (true) {  
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            if (A == 0 && B == 0 && C == 0) {
                break;
            }
            
            Set<Integer> set = new HashSet<>();
            Queue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        
            set.add(A);
            set.add(B);
            set.add(C);
        
            pq.offer(A);
            pq.offer(B);
            pq.offer(C);
        
            System.out.println(print(set, pq));
        }
    }
    
    public static String print(Set<Integer> set, Queue<Integer> pq) {
        int l = pq.poll();
        int s1 = pq.poll();
        int s2 = pq.poll();
        if (s1 + s2 <= l) {
            return "Invalid";
        }
        
        if (set.size() == 1) {
            return "Equilateral";
        } else if (set.size() == 2) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }
}