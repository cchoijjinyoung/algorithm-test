import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[][] tired = new int[][]{
            {1, 1, 1},
            {5, 1, 1},
            {25, 5, 1}
        };
        
        List<Mineral> list = new ArrayList<>();
        int size = Arrays.stream(picks).sum();
        
        for (int i = 0; i < size; i++) {
            int diamond = 0;
            int iron = 0;
            int stone = 0;
            
            for (int j = i * 5; j < i * 5 + 5; j++) {
                if (j >= minerals.length) break;
                
                if (minerals[j].startsWith("d")) {
                    diamond += tired[0][0];
                    iron += tired[1][0];
                    stone += tired[2][0];
                } else if (minerals[j].startsWith("i")) {
                    diamond += tired[0][1];
                    iron += tired[1][1];
                    stone += tired[2][1];
                } else if (minerals[j].startsWith("s")) {
                    diamond += tired[0][2];
                    iron += tired[1][2];
                    stone += tired[2][2];
                }
            }
            list.add(new Mineral(diamond, iron, stone));
        }
        
        list.sort((a, b) -> b.stone - a.stone);

        for (int i = 0; i < list.size(); i++) {
            if (picks[0] > 0) {
                answer += list.get(i).diamond;
                picks[0]--;
            } else if (picks[1] > 0) {
                answer += list.get(i).iron;
                picks[1]--;
            } else if (picks[2] > 0) {
                answer += list.get(i).stone;
                picks[2]--;
            }
        }
        return answer;
    }
        
    class Mineral {
        int diamond;
        int iron;
        int stone;
        
        public Mineral(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }
}