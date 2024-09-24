import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        
        int bus = 540; // 첫차 09:00
        int max = bus + t * (n - 1); // 막차
        int cnt = 0; // 지금 버스에 몇명탔는지
        int before = 0; // 마지막에 탄 녀석의 time
        int idx = 0;
        
        while (n-- > 0) { // 버스 한대씩 보냄
            cnt = 0;
            while (idx < timetable.length) {
                String nextCrew = timetable[idx];
                if (convert(nextCrew) <= bus) {
                    idx++;
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