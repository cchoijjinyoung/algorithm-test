import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] temp = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            temp[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(temp, new Comparator<String>() {
            public int compare(String s1, String s2) {
                // 3, 30
                // 330, 303
                int sum1 = Integer.parseInt(s1 + s2);
                int sum2 = Integer.parseInt(s2 + s1);
                return sum2 - sum1;
            }
        });
        
        for (int i = 0; i < temp.length; i++) {
            answer += temp[i];
        }
        return answer.startsWith("0") ? "0" : answer;
    }
}