import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int count = 0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            count++;
        }
        
        System.out.println(count);
    }
}