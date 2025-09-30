import java.util.*;

class Solution {
    int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        
        combination(n, new int[5], 1, 0, q, ans);
        
        return answer;
    }
    
    public void combination(int n, int[] comb, int start, int depth, int[][] q, int[] ans) {
        if (depth == 5) {
            calc(comb, q, ans);
            return;
        }
        
        for (int i = start; i <= n; i++) {
            comb[depth] = i;
            combination(n, comb, i + 1, depth + 1, q, ans);
        }
    }
    
    public void calc(int[] comb, int[][] q, int[] ans) {
        Set<Integer> code = new HashSet<>();
        
        for (int num : comb) {
            code.add(num);
        }
        
        for (int i = 0; i < q.length; i++) {
            int match = 0;
            for (int num : q[i]) {
                if (code.contains(num)) {
                    match++;
                }
            }
            if (match != ans[i]) {
                return;
            }
        }
        answer++;
    }
}