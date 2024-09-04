class Solution {
    public int[] solution(String my_string) {
        int[] answer = new int[52];
        
        for (int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                answer[c - 'A']++;
            } else {
                answer[26 + c - 'a']++;
            }
        }
        System.out.println((int)'A');
        System.out.println((int)'a');
        return answer;
    }
}