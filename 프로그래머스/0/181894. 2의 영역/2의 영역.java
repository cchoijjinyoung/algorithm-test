class Solution {
    public int[] solution(int[] arr) {
        int lt = -1;
        int rt = -1;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                lt = i;
                break;
            }
        }
        if (lt == -1) return new int[]{-1};
        
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 2) {
                rt = i;
                break;
            }
        }
        int[] ans = new int[rt - lt + 1];
        int idx = 0;
        for (int i = lt; i <= rt; i++) {
            ans[idx++] = arr[i];
        }
        
        return ans;
    }
}