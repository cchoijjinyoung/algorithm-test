import java.util.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        boolean answer = true;
        
        Arrays.sort(phoneBook);
        
        if (phoneBook.length == 1) {
            return true;
        }
        
        for (int i = 0; i < phoneBook.length - 1; i++) {
            int len = phoneBook[i].trim().length();
            String sub = phoneBook[i + 1].trim();
            if (sub.startsWith(phoneBook[i])) {
                answer = false;
                break;
            }
            continue;
        }
        
        return answer;
    }
}