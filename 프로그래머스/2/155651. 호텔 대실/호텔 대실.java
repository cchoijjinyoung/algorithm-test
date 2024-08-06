import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        // 시작 시간으로 정렬
        Arrays.sort(book_time, (b1, b2) -> b1[0].compareTo(b2[0]));
        
        // 방의 끝시간을 담을 리스트
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < book_time.length; i++) {
            int start = calc(book_time[i][0]);
            int end = calc(book_time[i][1]);
            
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                // 이미 있는 방에 들어가면,
                if (list.get(j) + 10 <= start) {
                    list.set(j, end);
                    flag = true;
                    break;
                }
            }
            // 새로운 방을 배정받으면,
            if (!flag) {
                list.add(end);
            }
            
            Collections.sort(list, Collections.reverseOrder());
        }
        
        // 하나씩 꺼내면서, 큐에 넣을건데, 입실 가능한 큐이면서, 청소를 가장 마지막에 한 곳
        
        return list.size();
    }
    
    public int calc(String timeStr) {
        String[] splits = timeStr.split(":");
        return Integer.parseInt(splits[0]) * 60 + Integer.parseInt(splits[1]);
    }
}