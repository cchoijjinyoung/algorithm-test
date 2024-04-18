class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        
        int height = park.length;
        int weight = park[0].length();
        
        // 강아지의 최종 위치를 구하시오.
        // park[] : 공원 배열
        // routes[] : 강아지의 이동방향 및 거리 (E 2)
        
        // park[]에서 S를 먼저 찾아야함.
        int sx = 0;
        int sy = 0;
        
        loop:
        for (int i = 0; i < height; i++) {
            char[] row = park[i].toCharArray();
            for (int j = 0; j < weight; j++) {
                if (row[j] == 'S') {
                    sx = i;
                    sy = j;
                    break loop;
                }
            }
        }
        // S 좌표를 찾았으면, 강아지를 움직여야하는데, 벽이면 못가야함
        // 공원을 벗어나거나, X 거나
        for (int i = 0; i < routes.length; i++) {
            char[] route = routes[i].toCharArray();
            char d = route[0]; // 이동 방향
            int m = route[2] - '0'; // 이동 거리
            
            int rx = sx;
            int ry = sy;
            for (int j = 0; j < m; j++) {
                if (d == 'E') {
                    ry += 1;
                    System.out.println("r : " + rx + " " + ry);
                } else if (d == 'W') {
                    ry += -1;
                } else if (d == 'S') {
                    rx += 1;
                } else if (d == 'N') {
                    rx += -1;
                }
            // 목적지가 벗어난 위치면 다음 명령 수행
                if (rx < 0 || ry < 0 || rx >= height || ry >= weight || park[rx].charAt(ry) == 'X') {
                    rx = sx;
                    ry = sy;
                    break;
                }
            }   
            sx = rx;
            sy = ry;
        }
        
        answer[0] = sx;
        answer[1] = sy;
        
        return answer;
    }
}