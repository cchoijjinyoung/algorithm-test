import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Map<Character, Integer> hm = new HashMap<>();
        // 스킬, 순서
        
        for (int i = 0; i < skill.length(); i++) {
            hm.put(skill.charAt(i), i);
        }
        
        for (String tree : skill_trees) {
            int seq = 0;
            int status = 0;
            for (char cur : tree.toCharArray()) {
                if (!hm.containsKey(cur)) {
                    continue;
                }
                if (hm.get(cur) != seq) {
                    status = 1;
                    break;
                } else {
                    status = 2;
                    seq++;
                }
            }
            if (status != 1) answer++;
        }
        return answer;
    }
}