class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for (int i = 1; i <= s.length() / 2; i++) {
            answer = Math.min(answer, check(s, s.length(), i));
        }
        return answer;
    }
    
    public int check(String s, int len, int diff) {
        int count = 1;
        StringBuilder result = new StringBuilder();
        String base = s.substring(0, diff);
        for (int i = diff; i <= len; i += diff) {
            int endIdx = Math.min(i + diff, len);
            String compare = s.substring(i, endIdx);
            if (base.equals(compare)) {
                count++;
            } else {
                if (count >= 2) {
                    result.append(count);
                }
                result.append(base);
                base = compare;
                count = 1;
            }
        }
        result.append(base);
        return result.length();
    }
}