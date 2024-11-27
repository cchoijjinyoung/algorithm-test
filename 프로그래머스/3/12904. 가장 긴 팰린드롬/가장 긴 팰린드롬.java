class Solution {
    int max = 0;
    int len;
    public int solution(String s) {
        len = s.length();
        char[] A = s.toCharArray();
        
        for (int L = 0; L < len; L++) {
            for (int R = L; R <= Math.min(len - 1, L + 1); R++) {
                if (A[L] == A[R]) {
                    check(A, L, R);    
                }
            }
        }

        return max;
    }
    
    public void check(char[] A, int L, int R) {
        int result = R - L + 1;
        L--;
        R++;
        while (0 <= L && R < len) {
            if (A[L] != A[R]) {
                break;
            } else {
                result += 2;
                L--;
                R++;
            }
        }
        max = Math.max(max, result);
    }
}