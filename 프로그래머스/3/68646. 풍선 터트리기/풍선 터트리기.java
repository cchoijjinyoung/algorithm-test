class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int[] minLeft = new int[a.length];
        int[] minRight = new int[a.length];
        
        minLeft[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < minLeft[i - 1]) {
                minLeft[i] = a[i];
            } else {
                minLeft[i] = minLeft[i - 1];
            }
        }
        
        minRight[a.length - 1] = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < minRight[i + 1]) {
                minRight[i] = a[i];
            } else {
                minRight[i] = minRight[i + 1];
            }
        }
        
        for (int i = 0; i < a.length; i++) {
            if (a[i] > minLeft[i] && a[i] > minRight[i]) {
                continue;
            }
            answer++;
        }
        return answer;
    }
}