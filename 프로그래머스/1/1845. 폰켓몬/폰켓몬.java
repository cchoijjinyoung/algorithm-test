import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        Set<Integer> hs = new HashSet<>();
        
        for (int num : nums) {
            hs.add(num);
        }
        
        int div = nums.length / 2 - hs.size();
        
        if (div > 0) {
            answer = nums.length / 2 - div;
        } else {
            answer = nums.length / 2;
        }
        return answer;
    }
}