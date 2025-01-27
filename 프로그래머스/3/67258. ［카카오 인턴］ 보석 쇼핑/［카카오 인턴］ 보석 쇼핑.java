import java.util.*;

class Solution {
    static int gemCount;
    static int min = Integer.MAX_VALUE;
    static Map<String, Integer> map = new HashMap<>();
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        int[] arr = new int[gems.length];
        for (int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            arr[i] = map.size();
        }
        
        gemCount = map.size();
        
        int L = 0, R = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == gemCount) {
                R = i;
                break;
            }
        }
        
        map = new HashMap<>();
        for (int i = 0; i <= R; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
        }
        
        while (L <= R) {
            if (map.size() == gemCount) {
                map.put(gems[L], map.get(gems[L]) - 1);
                if (map.get(gems[L]) == 0) {
                    map.remove(gems[L]);
                    if (R - L + 1 < min) {
                        min = R - L + 1;
                        answer[0] = L;
                        answer[1] = R;
                    }
                }
                L++;
            } else {
                R++;
                if (R > gems.length - 1) break;
                if (map.containsKey(gems[R])) {
                    map.put(gems[R], map.get(gems[R]) + 1);
                } else {
                    map.put(gems[R], 1);
                }
            }
        }
        
        answer[0]++;
        answer[1]++;
        return answer;
    }
}