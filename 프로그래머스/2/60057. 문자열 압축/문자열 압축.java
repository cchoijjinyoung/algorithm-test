class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = len;
        int min = Integer.MAX_VALUE;
        int repeat = 1;
        
        // i는 잘라야할 길이
        for (int i = 1; i <= len / 2; i++) {
            int result = 0;
            String cur = s.substring(0, i);
            
            for (int start = i; start <= len; start += i) {
                int end = Math.min(start + i, len);
                String next = s.substring(start, end);
                
                if (cur.equals(next)) {
                    repeat++;
                } else {
                    if (repeat > 1) {
                        result += String.valueOf(repeat).length();
                    }
                    result += i;
                    cur = next;
                    repeat = 1;
                }
            }
            result += cur.length();
            answer = Math.min(answer, result);
        }
        return answer;
    }
}