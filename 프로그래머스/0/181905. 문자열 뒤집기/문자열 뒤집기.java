class Solution {
    public String solution(String my_string, int s, int e) {
        String answer = "";
        
        String reverseString = "";
        for (int i = 0; i <= e - s; i++) {
            reverseString += my_string.charAt(e - i);
        }
        answer = my_string.substring(0, s) + reverseString + my_string.substring(e + 1);
        return answer;
    }
}