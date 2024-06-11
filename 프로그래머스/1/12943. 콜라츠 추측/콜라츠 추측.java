class Solution {
    public int solution(int num) {
        int answer = 0;
        
        if (num == 1) {
            return 0;
        }
        
        int cnt = 0;
        while (cnt++ < 500) {
            if (num % 2 == 0) {
                num = one(num);
            } else if (num % 2 == 1) {
                num = two(num);
            }
            if (num == 1) {
                return cnt;
            }
        }
        
        if (num != 1) {
            return -1;
        }
        return answer;
    }
    
    public int one(int num) {
        return num / 2;
    }
    
    public int two(int num) {
        return num * 3 + 1;
    }
}