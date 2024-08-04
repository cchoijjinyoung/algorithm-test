import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int len = 0;
        for (int pick : picks) {
            len += pick;
        }
        
        // 5개 단위로 끊어서 돌 곡괭이로 캤을 때, 점수를 센다.
        // 1~5 -> 20, 6~9, 10 -> 10~14 -> 40
        // 0
        // 40, 10
        // {20, 25, 30};
        // 내림차순 정렬해서 다이아 곡괭이가 있으면 다이아로, 철 곡괭이가 있으면 철 곡괭이로 캔다. 돌 ~
        PriorityQueue<Mineral> pq = new PriorityQueue<>((m1, m2) -> m2.sum - m1.sum);
        
        int sum = 0;
        
        int diaCnt = 0;
        int ironCnt = 0;
        int stoneCnt = 0;
        
        int min = Math.min(len * 5, minerals.length);
        int count = 0;
        for (int i = 0; i < min; i++) {
            count++;
            if ("diamond".equals(minerals[i])) {
                diaCnt++;
                sum += 25;
            } else if ("iron".equals(minerals[i])) {
                ironCnt++;
                sum += 5;
            } else {
                stoneCnt++;
                sum += 1;
            }
            
            if (count % 5 == 0 || i == min - 1) {
                pq.offer(new Mineral(diaCnt, ironCnt, stoneCnt, sum));
                diaCnt = 0; ironCnt = 0; stoneCnt = 0;
                sum = 0; // 합산 초기화
            }
        }
        
        while (!pq.isEmpty()) {
            // pq에서 꺼낸다.
            Mineral cur = pq.poll();
            int dia = cur.dia;
            int iron = cur.iron;
            int stone = cur.stone;
            
            if (picks[0] != 0) {
                answer += dia + iron + stone;
                picks[0]--;
            } else if (picks[1] != 0) {
                answer += dia * 5 + iron + stone;
                picks[1]--;
            } else {
                answer += dia * 25 + iron * 5 + stone;
                picks[2]--;
            }
        }
        
        return answer;
    }
    
    public class Mineral {
        int dia;
        int iron;
        int stone;
        int sum;
        
        public Mineral(int dia, int iron, int stone, int sum) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
            this.sum = sum;
        }
    }
}