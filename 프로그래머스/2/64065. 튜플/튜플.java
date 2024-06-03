import java.util.*;

class Solution {
    public int[] solution(String s) {
        String temp = s.substring(2, s.length() - 2);
        String target = temp.replace("},{", "-");
        boolean[] visited = new boolean[100001];
        
        String[] elements = target.split("-");
        // {2}, {2,1}, {2,1,3}, {2,1,3,4};
        
        PriorityQueue<String> pq = new PriorityQueue<>(
            (s1, s2) -> s1.length() - s2.length()
        );
        
        for (String element : elements) {
            pq.add(element);
        }
        
        int[] answer = new int[pq.size()];
        
        for (int i = 0; i < answer.length; i++) {
            String[] arr = pq.poll().split(",");
            
            for (String c : arr) {
                int a = Integer.parseInt(c);
                if (visited[a]) {
                    continue;
                }
                answer[i] = a;
                visited[a] = true;
            }
        }
        
        return answer;
    }
}