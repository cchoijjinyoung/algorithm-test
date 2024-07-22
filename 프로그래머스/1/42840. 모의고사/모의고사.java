import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] one = new int[]{1, 2, 3, 4, 5};
        int[] two = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] points = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == one[i % one.length]) {
                points[0]++;
            }
            
            if (answers[i] == two[i % two.length]) {
                points[1]++;
            }
                
            if (answers[i] == three[i % three.length]) {
                points[2]++;
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            max = Math.max(max, points[i]);
        }
        
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == max) {
                temp.add(i + 1);
            }
        }
        
        int[] answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        
        return answer;
    }
}