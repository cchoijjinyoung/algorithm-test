class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            answer[i] = solve(numbers[i]);
        }
        // 2 + 4 + 1 = 0111
        // 42 = 32 + 0 + 8 + 0 + 2 + 0 = 0101010
        // 4 + 0 + 1 = 101
        // 63 = 32 + 16 + 8 + 4 + 2 + 1 = 0. 1 1. 1 1. 1 1.
        
        // 1글자 3, 7, 15, 31, 63, ..
        // 10^15 = (10^3)^5 = (2^10)^5 = 2^50 = 50비트 = 최대 약 50개의 노드 = 최대 depth는 6
        // 리프노드가 아닌 노드가 0일수 없다.
        // 중위순회
        // 0001011 = 11
        return answer;
    }
    
    public int solve(long number) {
        String result = "";
        String bs = Long.toBinaryString(number);
        int len = bs.length();
        for (int i = 1; i <= 6; i++) {
            int expect = (int)Math.pow(2, i) - 1;
            if (len <= expect) {
                result = "0".repeat(expect - len) + bs;
                break;
            }
        }
        
        return check(result) ? 1 : 0;
    }
    
    public boolean check(String s) {
        if (s.length() == 1) {
            return true;
        }
        
        int rootIdx = s.length() / 2;
        String leftTree = s.substring(0, rootIdx);
        String rightTree = s.substring(rootIdx + 1);
        
        // 만약 루트가 1이라면,
        if (s.charAt(rootIdx) == '1') {
            boolean f1 = check(leftTree);
            boolean f2 = check(rightTree);
            if (f1 && f2) {
                return true;
            } else {
                return false;
            }
        // 만약 루트가 0이라면,
        } else {
            boolean f1 = leftTree.contains("1");
            boolean f2 = rightTree.contains("1");
            if (f1 || f2) {
                return false;
            } else {
                return true;
            }
        }
    }
}