import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Stack<Num> stack = new Stack<>();
        int[] answer = new int[numbers.length];
        
        Arrays.fill(answer, -1);
        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && stack.peek().value < numbers[i]) {
                int idx = stack.pop().idx;
                answer[idx] = numbers[i];
            }
            stack.push(new Num(i, numbers[i]));
        }
        
        return answer;
    }
    
    class Num {
        int idx;
        int value;
        
        public Num(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}