import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        int idx = 0;
        for (int i = 1; i <= order.length; i++) {
            // 못올리면 스택에 넣는다.
            stack.push(i);
            
            // 보조 컨테이너도 확인한다.
            while (!stack.isEmpty()) {
                if (stack.peek() == order[idx]) {
                    stack.pop();
                    idx++;
                    answer++;
                    continue;
                } else {
                    break;
                }
            }
        }
        
        // while (!stack.isEmpty()) {
        //     if (stack.peek() == order[idx]) {
        //         stack.pop();
        //         idx++;
        //         answer++;
        //     } else {
        //         break;
        //     }
        // }
        return answer;
    }
}