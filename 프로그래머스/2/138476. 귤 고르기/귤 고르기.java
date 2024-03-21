import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < tangerine.length; i++) {
            hm.put(tangerine[i], hm.getOrDefault(tangerine[i], 0) + 1);
        }
        
        int[] boxes = new int[hm.size()];
        int index = 0;
        
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            boxes[index] = entry.getValue();
            index++;
        }
        Arrays.sort(boxes);
        
        int removeBox = 0;
        int removeCnt = tangerine.length - k;
        for (int i = 0; i < boxes.length; i++) {
            removeCnt -= boxes[i];
            if (removeCnt < 0) {
                removeBox = i;
                break;
            }
        }
        answer = boxes.length - removeBox;
        return answer;
    }
}