class Solution {
    public String solution(int a, int b) {
        String answer = "";
        
        String[] days = new String[]{"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        
        int[] day = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int d = 0;
        for (int i = 1; i < a; i++) {
            d += day[i];
        }
        
        d += b - 1;
        System.out.print(d);
        
        answer = days[d % 7];
        
        return answer;
    }
}