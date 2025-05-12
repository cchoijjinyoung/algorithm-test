import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        
        int sum = elements[0];
        set.add(sum);
        for (int i = 1; i < elements.length; i++) {
            set.add(elements[i]);
            sum += elements[i];
            set.add(sum);
        }
        
        for (int i = 1; i < elements.length; i++) {
            int start = elements[i];
            for (int j = i + 1; j < elements.length; j++) {
                int add = elements[j];
                start += add;
                set.add(start);
            }
            for (int j = 0; j < i; j++) {
                start += elements[j];
                set.add(start);
            }
        }
        
        return set.size();
    }
}