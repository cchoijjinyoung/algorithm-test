class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        
        int length = s.length();
        
        if (length != 4 && length != 6) {
            answer = false;
        }
        
        if (answer) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    answer = false;
                    break;
                }
            }
        }
        
        return answer;
    }
}