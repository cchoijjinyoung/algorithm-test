import java.util.*;
import java.io.*;

class Main {
    static int N;
    static String A;
    static int[] cnt;
    static int kind;
    public static void main(String[] args) {
        int answer = 0;
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        A = sc.nextLine();
        cnt = new int[26];
        kind = 0;
        
        // 문자열을 순회하면서, 두포인터 알고리즘을 사용할 것
        // L ~ R : 검사하고 싶은 구간
        for (int L = 0, R = 0; R < A.length(); R++) {
            // R번째 문자를 오른쪽에 추가
            add(A.charAt(R));
            
            // 불가능하면, 가능할 때까지 L을 이동
            while (kind > N) {
                erase(A.charAt(L++));
            }
            // answer 갱신
            answer = Math.max(answer, R - L + 1);
        }
        System.out.println(answer);
    }
    
    static void add(char x) {
        cnt[x - 'a']++;
        if (cnt[x - 'a'] == 1) {
            kind++;
        }
    }
    
    static void erase(char x) {
        cnt[x - 'a']--;
        if (cnt[x - 'a'] == 0) {
            kind--;
        }
    }
}