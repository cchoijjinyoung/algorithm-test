import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < timetable.length; i++) {
            pq.offer(timetable[i]);
        }
        
        int bus = 540;
        int max = bus + t * (n - 1);
        int cnt = 0;
        int before = 0;
        
        while (n-- > 0) {
            cnt = 0;
            while (!pq.isEmpty()) {
                String nextCrew = pq.peek();
                if (convert(nextCrew) <= bus) {
                    pq.poll();
                    cnt++;
                    before = convert(nextCrew);
                } else {
                    break;
                }
                
                if (cnt == m) {
                    break;
                }
            }
            bus += t;
        }
        return cnt < m ? revert(max) : revert(before - 1);
    }
    
    public int convert(String time) {
        String[] splitedTime = time.split(":");
        String h = splitedTime[0];
        String m = splitedTime[1];
        return Integer.parseInt(h) * 60 + Integer.parseInt(m);
    }
    
    public String revert(int time) {
        int h = time / 60;
        int m = time % 60;
        return String.format("%02d:%02d", h, m);
    }
}