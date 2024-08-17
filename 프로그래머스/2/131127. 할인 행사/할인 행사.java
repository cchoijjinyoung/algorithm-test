import java.util.*;

class Solution {
    int answer = 0;
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < want.length; i++) {
            hm.put(want[i], i);
        }
        
        int[] counts = Arrays.copyOf(number, number.length);
        for (int i = 0; i < 10; i++) {
            String s = discount[i];
            
            if (hm.containsKey(s)) {
                int idx = hm.get(s);
                counts[idx]--;
            }
        }
        
        calc(counts);
        
        int lt = 0;
        int rt = 10;
        while (rt < discount.length) {
            String pre = discount[lt];
            String next = discount[rt];
            
            if (hm.containsKey(pre)) {
                int preIdx = hm.get(pre);    
                counts[preIdx]++;
            }
            
            boolean flag = false;
            if (hm.containsKey(next)) {
                int nextIdx = hm.get(next);    
                counts[nextIdx]--;
                
                if (counts[nextIdx] == 0) {
                    flag = true;
                }
            }
            
            if (flag) {
                calc(counts);
            }
            lt++;
            rt++;
        }
        
        return answer;
    }
    
    public void calc(int[] counts) {
        boolean flag = true;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            answer++;
        }
    }
}