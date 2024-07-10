import java.util.*;

class Solution {
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
    
    List<int[]> points;
    public String[] solution(int[][] line) {
        points = new ArrayList<>();
        
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i; j < line.length; j++) {
                calc(line[i], line[j]);
            }
        }
        
        char[][] temp = new char[maxY - minY + 1][maxX - minX + 1];
        
        for (int i = 0; i < temp.length; i++) {
            Arrays.fill(temp[i], '.');
        }
        
        int dx = 0 - minX;
        int dy = 0 - minY;
        
        for (int i = 0; i < points.size(); i++) {
            int[] point = points.get(i);
            int x = point[0];
            int y = point[1];
            temp[temp.length - 1 - (y + dy)][x + dx] = '*';
        }
        
        return convertStringArray(temp);
    }
    
    public int[] calc(int[] line1, int[] line2) {
        int A = line1[0];
        int B = line1[1];
        int E = line1[2];
        
        int C = line2[0];
        int D = line2[1];
        int F = line2[2];
        
        long AD = (long)A * D;
        long BC = (long)B * C;
        long BF = (long)B * F;
        long ED = (long)E * D;
        long EC = (long)E * C;
        long AF = (long)A * F;
        
        // 평행
        if (AD - BC == 0) {
            return null;
        }
        
        if ((BF - ED) % (AD - BC) != 0) {
            return null;
        }
        
        if ((EC - AF) % (AD - BC) != 0) {
            return null;
        }
        
        int x = (int)((BF - ED) / (AD - BC));
        int y = (int)((EC - AF) / (AD - BC));
        
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);

        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
        
        int[] point = new int[]{x, y};
        points.add(point);
        
        return point;
    }
    
    public String[] convertStringArray(char[][] temp) {
        String[] answer = new String[temp.length];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = new String(temp[i]);
        }
        
        return answer;
    }
}