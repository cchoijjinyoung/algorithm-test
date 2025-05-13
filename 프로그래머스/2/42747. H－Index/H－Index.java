import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        // 정렬해서, index + 1이 현재 value와 같거나 작으면 break 후 return
        Arrays.sort(citations);
        
        int count = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            int value = citations[i];
            count++;
            if (count > value) {
                return count - 1;
            }
        }
        return citations.length;
    }
}