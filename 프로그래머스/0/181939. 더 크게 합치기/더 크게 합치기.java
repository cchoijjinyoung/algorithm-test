class Solution {
    public int solution(int a, int b) {
        int i1 = Integer.parseInt(String.valueOf(a) + String.valueOf(b));
        int i2 = Integer.parseInt(String.valueOf(b) + String.valueOf(a));
        
        return Math.max(i1, i2);
    }
}