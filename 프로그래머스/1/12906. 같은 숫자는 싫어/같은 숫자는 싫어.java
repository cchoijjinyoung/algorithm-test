import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack();
        
        for (int num : arr) {
            if (stack.isEmpty()) {
                stack.push(num);
                continue;
            }
            
            if (stack.peek() == num) {
                continue;
            } else {
                stack.push(num);
            }
        }
        
        int[] answer = new int[stack.size()];
        for (int i = answer.length - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }
        return answer;
    }
}