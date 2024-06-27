import java.util.*;

class Solution {
    StringBuilder sb;
    public String solution(String p) {
        String answer = "";
        
        sb = new StringBuilder();
        
        answer = calc(p);
        
        return answer;
    }
    
    public String calc(String p) {
        if ("".equals(p)) {
            return "";
        }
        
        // p를 순회하면서 균형잡힌 괄호가 되는 순간 break;
        // u와 v를 분리
        String u = "";
        String v = "";
        int open = 0;
        int close = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            
            if (open == close) {
                u = p.substring(0, i + 1);
                v = p.substring(i + 1);
                break;
            }
        }
        
        // u가 [균형/올바른] 중에 무엇인지 체크
        boolean correct = false;
        Stack<Character> stack = new Stack();
        
        // 만약 ')' 가 맨앞이라면 올바르지 않으므로 조건문을 타지않음.
        if (u.charAt(0) == '(') {
            stack.push(u.charAt(0));
            
            for (int i = 1; i < u.length(); i++) {
                if (u.charAt(i) == '(') {
                    stack.push(u.charAt(i));
                    continue;
                }
                
                if (stack.isEmpty()) {
                    stack.push(u.charAt(i));
                    break;
                }
                
                if (stack.peek() == '(') {
                    stack.pop();
                }
            }
            
            if (stack.isEmpty()) {
                correct = true;
            }
        }
        
        // 만약 올바른이라면,
        if (correct) {
            sb.append(u);
            calc(v);
            return sb.toString();
        }
        
        // 만약 균형이라면,
        sb.append("(");
        calc(v);
        sb.append(")");
        u = u.substring(1, u.length() - 1);

        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                sb.append(")");
            } else {
                sb.append("(");
            }
        }
        
        return sb.toString();
    }
}