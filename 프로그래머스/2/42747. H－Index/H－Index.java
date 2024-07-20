import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        int len = citations.length;
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= len) {
                answer = len;
                break;
            }
            len--;
        }
        return answer;
    }
}