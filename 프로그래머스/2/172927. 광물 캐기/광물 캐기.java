import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int[][] tired = new int[][]{
            {1, 1, 1},
            {5, 1, 1},
            {25, 5, 1}
        };
        
        int picksSize = Arrays.stream(picks).sum();
        List<Mineral> list = new ArrayList<>();
        
        for (int i = 0; i < minerals.length; i += 5) {
            if (picksSize == 0) {
                break;
            }
            
            // 곡괭이 별 피로도
            int diamond = 0;
            int iron = 0;
            int stone = 0;
            
            for (int j = i; j < i + 5; j++) {
                if (j == minerals.length) {
                    break;
                }
                
                int mineralId = 0;
                if ("iron".equals(minerals[j])) {
                    mineralId = 1;
                } else if ("stone".equals(minerals[j])) {
                    mineralId = 2;
                }
                
                diamond += tired[0][mineralId];
                iron += tired[1][mineralId];
                stone += tired[2][mineralId];
            }
            
            list.add(new Mineral(diamond, iron, stone));
            picksSize--;
        }
        
        list.sort((o1, o2) -> (o2.stone - o1.stone));
        
        for (Mineral m : list) {
            int diamond = m.diamond;
            int iron = m.iron;
            int stone = m.stone;
            
            if (picks[0] > 0) {
                answer += diamond;
                picks[0]--;
                continue;
            }
            
            if (picks[1] > 0) {
                answer += iron;
                picks[1]--;
                continue;
            }
            
            if (picks[2] > 0) {
                answer += stone;
                picks[2]--;
                continue;
            }
        }
        return answer;
    }
    
    class Mineral {
        private int diamond;
        private int iron;
        private int stone;
        
        public Mineral(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }
}