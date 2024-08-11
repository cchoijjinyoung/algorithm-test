import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        // Stack 사용
        // 스택에 numbers[i]를 넣으면서, 다음 numbers[i]와 stack의 peek()을 비교, 
        // peek이 더 작으면 answer의 해당 인덱스 위치를 numbers[i]로 바꾼다.
        // 그러려면 Stack에 인덱스도 같이 push 해줘야한다.
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{numbers[0], 0}); // 숫자와 인덱스
        
        for (int i = 1; i < numbers.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < numbers[i]) {
                int[] cur = stack.pop();
                answer[cur[1]] = numbers[i];
            }
            stack.push(new int[]{numbers[i], i});
        }
        
        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            answer[cur[1]] = -1;
        }
        return answer;
    }
}