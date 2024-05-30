import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        List<Stack<Reserve>> list = new ArrayList<>();
        PriorityQueue<Reserve> pq = new PriorityQueue<>(
            (r1, r2) -> r1.in - r2.in
        );
        
        // 북타임을 입실시간 기준 오름차순으로 정렬
        for (int i = 0; i < book_time.length; i++) {
            String[] time = book_time[i];
            
            int in = transfer(time[0]);
            int out = transfer(time[1]);

            pq.add(new Reserve(in, out));
        
        }
        
        while (!pq.isEmpty()) {
            // 북타임 순회
            Reserve cur = pq.poll();
    
            int minIdx = -1;
            int minDiff = 0;        
            // 리스트에 스택이 존재하면, 리스트를 순회해서,
            for (int i = 0; i < list.size(); i++) {
                // 스택의 peek을 확인해서 peek.out이 cur.in과 가장 가까운 스택을 찾아준다.
                Stack<Reserve> stack = list.get(i);
                Reserve peek = stack.peek();
                if (peek.out + 10 > cur.in) {
                    continue;
                }
                
                int diff = peek.out - cur.in;
                if (diff < minDiff) {
                    minDiff = diff;
                    minIdx = i;
                }
            }
            
            // 들어갈 스택이 없다면
            if (minIdx == -1 || list.isEmpty()) {
                // 새로운 스택을 생성해서 넣어준다.
                Stack<Reserve> stack = new Stack<>();
                stack.push(cur);
                list.add(stack);
                continue;
            }
            
            Stack stack = list.get(minIdx);
            stack.push(cur);
        }
        answer = list.size();
        for (int i = 0; i < list.size(); i++) {
            Stack<Reserve> stack = list.get(i);
            while (!stack.isEmpty()) {
                Reserve reserve = stack.pop();
                System.out.print(reserve.in + "~" + reserve.out + " ");
            }
            System.out.println();
        }
        return answer;
    }
    
    public int transfer(String time) {
        int result = 0;
        String[] arr = time.split(":");
        result = (Integer.parseInt(arr[0]) * 60) + Integer.parseInt(arr[1]);
        return result;
    }
    
    class Reserve {
        int in;
        int out;
        
        public Reserve(int in, int out) {
            this.in = in;
            this.out = out;
        }
    }
}