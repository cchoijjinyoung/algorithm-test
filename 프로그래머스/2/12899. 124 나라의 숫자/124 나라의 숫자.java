class Solution {
    public String solution(int n) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        if (n == 3) return "4";
        
        while (n >= 1) {
            if (n % 3 == 0) {
                sb.append("4");
                n = n / 3 - 1;
            } else {
                sb.append(n % 3);
                n = n / 3;    
            }
        }
        
        answer = sb.reverse().toString();
        return answer;
    }
}