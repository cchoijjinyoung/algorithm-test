import java.util.ArrayList;
class Solution {
    public int[] solution(String myString) {
        String[] arr = myString.split("x", -2);
        
        int[] answer = new int[arr.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = arr[i].length();
        }
        
        return answer;
    }
}