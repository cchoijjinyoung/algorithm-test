import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder total = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        // 게임에서 외칠 전체 문자열 만들기
        for(int i = 0; total.length() <= t * m; i++){
            String s = Integer.toString(i, n);
            total.append(s);
        }
        
        // 튜브가 외칠 문자만 골라내기
        for(int i = 0; i < t; i++){
            answer.append(total.charAt(p - 1 + i * m));
        }
        
        // 10 -> a와 같이 변환되므로, 대문자로 변경
        return answer.toString().toUpperCase();
    }
}