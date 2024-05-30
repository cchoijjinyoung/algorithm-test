import java.util.*;

class Solution {
    public int solution(String s) {
        int X = 0;
        
        // 회전
        for (int i = 0; i < s.length(); i++) {
            Stack<Character> stack = new Stack();
            
            for (int j = i; j < i + s.length(); j++) {
                int idx = j % s.length();
                char cur = s.charAt(idx);
                
                if (!stack.isEmpty()) {
                    if (isClose(stack.peek())) {
                        break;
                    }
                    
                    if (isSet(stack.peek(), cur)) {
                        stack.pop();
                    } else {
                        stack.push(cur);
                    }
                    continue;
                }
                stack.push(cur);
            }
            if (stack.size() > 0) {
                X++;
            }
        }
        return s.length() - X;
    }
    
    public boolean isClose(char c) {
        if (c == ')' || c == ']' || c == '}') {
            return true;
        }
        return false;
    }
    
    public boolean isSet(char c1, char c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        }
        
        if (c1 == '{' && c2 == '}') {
            return true;
        }
        
        if (c1 == '[' && c2 == ']') {
            return true;
        }
        return false;
    }
}