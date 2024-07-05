import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < elements.length; i++) {
            set.add(elements[i]);
        }
        
        for (int lt = 0; lt < elements.length; lt++) {
            int rt = lt + 1;
            int sum = elements[lt];
            
            while (lt != rt % elements.length) {
                sum += elements[rt % elements.length];
                set.add(sum);
                rt++;
            }
        }
        
        for (int lt = 0; lt < elements.length; lt++) {
            int rt = lt - 1;
            int sum = elements[lt];
            
            while (lt != ((rt + elements.length) % elements.length)) {
                sum += elements[(rt + elements.length) % elements.length];
                set.add(sum);
                rt--;
            }
        }
        
        answer = set.size();
        return answer;
    }
}