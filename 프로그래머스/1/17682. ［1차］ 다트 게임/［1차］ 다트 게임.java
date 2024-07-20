import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int[] point = new int[3];
        Queue<Character> q = new LinkedList<>();
        
        int idx = -1;
        char[] arr = dartResult.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                q.add(arr[i]);
                continue;
            }

            if ('S' == arr[i] || 'D' == arr[i] || 'T' == arr[i]) {
                int pow = calcPow(arr[i]);
                String num = "";
                
                idx++;
                while(!q.isEmpty()) {
                    num += q.poll();
                }
                point[idx] = (int)(Math.pow(Integer.parseInt(num), pow));
                continue;
            }
            
            if ('*' == arr[i]) {
                if (idx > 0) {
                    point[idx - 1] *= 2;
                }
                point[idx] *= 2;
            } else if ('#' == arr[i]) {
                point[idx] *= -1;
            }
        }
        
        for (int i = 0; i < point.length; i++) {
            answer += point[i];
        }
        return answer;
    }
    
    public int calcPow(char bonus) {
        if (bonus == 'S') {
            return 1;
        } else if (bonus == 'D') {
            return 2;
        } else {
            return 3;
        }
    }
}