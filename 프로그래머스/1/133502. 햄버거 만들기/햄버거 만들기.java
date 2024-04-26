import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int in : ingredient) {
            stack.push(in);
            
            if (stack.size() >= 4 && in == 1) {
                if (check(stack)) {
                    answer++;
                    
                    int cnt = 4;
                    while (cnt-- > 0) {
                        stack.pop();
                    }
                }
            }
        }
        return answer;
    }
    
    public boolean check(Stack<Integer> stack) {
        if (stack.get(stack.size() - 4) != 1
           || stack.get(stack.size() - 3) != 2
           || stack.get(stack.size() - 2) != 3
           || stack.get(stack.size() - 1) != 1) {
            return false;
        }
        return true;
    }
}