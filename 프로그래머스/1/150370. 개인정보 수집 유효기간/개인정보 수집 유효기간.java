import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // terms[i] : A 5
        // privacies[i] : 2022.9.25 A
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        Map<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String term = terms[i];
            String[] splited = term.split(" ");
            
            String op = splited[0];
            int month = Integer.parseInt(splited[1]);
            
            hm.put(op, month);
        }
        
        int cur = expire(today, 0, 0);
        
        for (int i = 0; i < privacies.length; i++) {
            String s = privacies[i];
            String[] splited = s.split(" ");
            
            String day = splited[0];
            String op = splited[1];
            
            int month = hm.get(op);
            if (cur > expire(day, month, -1)) {
                pq.offer(i + 1);
            }
        }
        
        int[] answer = new int[pq.size()];
        
        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }
        
        return answer;
    }
    
    public int expire(String day, int month, int dd) {
        String[] s = day.split("\\.");

        int y = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int d = Integer.parseInt(s[2]);
        
        d += dd;
        if (d < 1) {
            m -= 1;
            d = 28;
        }
        
        m += month;
        if (m > 12) {
            int n = m / 12;
            y += n;
            m %= 12;
        }
        
        if (m == 0) {
            y -= 1;
            m = 12;
        }
        
        return y * 10000 + m * 100 + d;
    }
}