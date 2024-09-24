import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        
        int curBus = 540; // 현재 버스 시간(첫 차 09:00)
        int endBus = curBus + t * (n - 1); // 마지막 차
        int rideCnt = 0; // 지금 버스에 몇명탔는지
        int lastCrew = 0; // 마지막에 탄 크루의 time
        int idx = 0; // 다음에 탈 크루의 포인터
        
        while (n-- > 0) { // 버스 한대씩 보냄
            rideCnt = 0;
            while (idx < timetable.length) { // 모든 크루 순회
                int nextCrew = convert(timetable[idx]);
                if (nextCrew <= curBus) { // 다음 순번의 크루가 현재 버스에 탈 수 있으면,
                    idx++;
                    rideCnt++;
                    lastCrew = nextCrew;
                } else { // 탈 수 없으면,
                    break;
                }
                
                if (rideCnt == m) { // 현재 버스가 꽉 찼으면,
                    break;
                }
            }
            curBus += t; // 다음 버스 시간으로 업데이트
        }
        // 마지막 차에 자리가 남으면, 마지막 차 시간에 오기. 꽉찼으면 마지막 탑승자보다 1분 일찍오기
        return rideCnt < m ? revert(endBus) : revert(lastCrew - 1);
    }
    
    public int convert(String time) {
        String[] splitedTime = time.split(":");
        int h = Integer.parseInt(splitedTime[0]);
        int m = Integer.parseInt(splitedTime[1]);
        return h * 60 + m;
    }
    
    public String revert(int time) {
        int h = time / 60;
        int m = time % 60;
        return String.format("%02d:%02d", h, m);
    }
}