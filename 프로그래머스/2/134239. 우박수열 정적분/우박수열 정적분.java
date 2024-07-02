import java.util.*;

class Solution {
    List<Integer> list;
    public double[] solution(int k, int[][] ranges) {
        list = new ArrayList<>();
        
        resolve(k);
        
        double[] areas = new double[list.size() - 1];
        
        for (int i = 0; i < list.size() - 1; i++) {
            int y1 = list.get(i);
            int y2 = list.get(i + 1);
            
            double big = Math.max(y1, y2);
            double small = Math.min(y1, y2);
            
            areas[i] = big - ((big - small) / 2);
        }
        
        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int x1 = ranges[i][0];
            int x2 = ranges[i][1];
            
            if (x2 <= 0) {
                x2 += list.size() - 1;
            }
            
            if (x1 > x2) {
                answer[i] = -1;
                continue;
            }
            
            double result = 0;
            for (int j = x1; j < x2; j++) {
                result += areas[j];
            }
            answer[i] = result;
        }
        
        
        return answer;
    }
    
    public void resolve(int y) {
        if (y <= 1) {
            list.add(y);
            return;
        }
        
        list.add(y);
        
        if (y % 2 == 0) {
            y /= 2;
        } else {
            y = y * 3 + 1;
        }
        resolve(y);
    }
}