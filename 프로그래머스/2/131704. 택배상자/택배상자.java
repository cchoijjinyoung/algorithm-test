import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        // order 배열을 변환시켜줌(43125 -> 34215)
        int[] temp = new int[order.length];
        
        for (int i = 0; i < order.length; i++) {
            int idx = order[i] - 1;
            temp[idx] = i + 1;
        }
        // 컨테이너 벨트
        Queue<Integer> cb = new LinkedList<>();
        for (int i : temp) {
            cb.add(i);
        }
        
        // 보조 컨테이너 벨트
        Stack<Integer> sb = new Stack<>();
        
        // 기대하는 다음 택배상자 번호
        int expectNumber = 1;
        
        while (cb.size() > 0 || sb.size() > 0) {
            if (cb.size() == 0 && sb.peek() != expectNumber) {
                break;
            }
            // 보조 컨벨 확인
            if (sb.size() > 0 && sb.peek() == expectNumber) {
                sb.pop();
                answer++;
                expectNumber++;
                continue;
            }
            
            // 컨벨 확인
            if (cb.size() > 0) {
                if (cb.peek() == expectNumber) {
                    cb.poll();
                    answer++;
                    expectNumber++;
                } else {
                    sb.push(cb.poll());
                }
            }
        }
        
        return answer;
    }
}