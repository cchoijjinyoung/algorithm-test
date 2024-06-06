import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = false;
        char[] arr = s.toCharArray();
        // 스택에 하나를 넣고 시작한다.
        Stack<Character> stack = new Stack<>();
        if (arr[0] == ')') {
            return false;
        }
        stack.push(arr[0]);
        
        // arr[1]부터 스택에 넣으면서 괄호 검증
        // 현재들어갈 괄호와 안에 있는 괄호가 matches되는 지.
        // 혹은 현재 들어갈 괄호가 열림 괄호인지.
        for (int i = 1; i < arr.length; i++) {
            char cur = arr[i];
            
            if (stack.isEmpty()) {
                stack.push(cur);
                continue;
            }
            
            char compare = stack.peek();
            
            if (cur == '(') {
                stack.push(cur);
                continue;
            }
            
            if (cur == ')') {
                stack.pop();
                continue;
            }
        }
        if (stack.isEmpty()) {
            answer = true;
        }
        return answer;
    }
}