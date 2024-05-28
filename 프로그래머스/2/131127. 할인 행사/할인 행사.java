import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int[] correct = new int[want.length];
        
        // 맵에
        Map<String, Integer> hm = new HashMap<>();
        
        // 재료의 인덱스를 담고있어.
        Map<String, Integer> idx = new HashMap<>();
        
        // 재료와 필요한 갯수를 담아
        for (int i = 0; i < want.length; i++) {
            hm.put(want[i], number[i]);
        }
        
        // 담아주고,
        for (int i = 0; i < want.length; i++) {
            idx.put(want[i], i);
        }
        
        // discount 순회하면서 갯수에서 한개씩 빼, 10개
        for (int i = 0; i < 10; i++) {
            hm.put(discount[i], hm.getOrDefault(discount[i], 0) - 1);
            // want에 없으면 0으로 들어간다.
            if (idx.containsKey(discount[i])) {
                if (hm.get(discount[i]) <= 0) {
                    correct[idx.get(discount[i])] = 1;
                } else {
                    correct[idx.get(discount[i])] = 0;
                }
            }
            
            if (Arrays.stream(correct).sum() == want.length) {
                answer++;
            }
        }
        
        // 앞에꺼는 재료 + 1, 뒤에꺼는 재료 - 1
        for (int i = 10; i < discount.length; i++) {
            hm.put(discount[i - 10], hm.getOrDefault(discount[i - 10], 0) + 1);
            
            if (idx.containsKey(discount[i - 10])) {
                if (hm.get(discount[i - 10]) <= 0) {
                    correct[idx.get(discount[i - 10])] = 1;
                } else {
                    correct[idx.get(discount[i - 10])] = 0;
                }
            }
            
            hm.put(discount[i], hm.getOrDefault(discount[i], 0) - 1);
            if (idx.containsKey(discount[i])) {
                if (hm.get(discount[i]) <= 0) {
                    correct[idx.get(discount[i])] = 1;
                } else {
                    correct[idx.get(discount[i])] = 0;
                }
            }
            
            if (Arrays.stream(correct).sum() == want.length) {
                answer++;
            }
        }
        
        return answer;
    }
}