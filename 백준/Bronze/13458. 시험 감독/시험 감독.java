import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        long result = 0;
        int[] answer = new int[1_000_001];
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] students = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        // 주 한명이 커버 가능한 학생 수까지는 1
        for (int i = 1; i <= B; i++) {
            answer[i] = 1;
        }
        
        // 부가 1명씩 추가될 때마다 배열에 업데이트
        int start = B + 1;
        int end = B + C;
        int count = 1;
        for (int i = start; i <= end; i++) {
            if (i == 1_000_001) {
                break;
            }
            answer[i] = 1 + count;
            if (i == end) {
                end += C;
                count++;
            }
        }
        
        for (int i = 0; i < students.length; i++) {
            result += answer[students[i]];
        }
        
        System.out.println(result);
    }
}