import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        int answer = 0;
        
        // 문자열을 리스트로
        List<String> list1 = convertString(str1);
        List<String> list2 = convertString(str2);
        
        // map에 String, integer로 갯수 체크
        Map<String, Integer> hm1 = new HashMap<>();
        Map<String, Integer> hm2 = new HashMap<>();
        
        for (int i = 0; i < list1.size(); i++) {
            hm1.put(list1.get(i), hm1.getOrDefault(list1.get(i), 0) + 1);
        }
        
        for (int i = 0; i < list2.size(); i++) {
            hm2.put(list2.get(i), hm2.getOrDefault(list2.get(i), 0) + 1);
        }
        
        double same = 0; // 교집합 갯수
        double sum = list1.size() + list2.size(); // 합집합 갯수
        
        for (Map.Entry<String, Integer> entry : hm1.entrySet()) {
            String key = entry.getKey();
            if (hm2.containsKey(key)) {
                same += Math.min(hm1.get(key), hm2.get(key));
            }
        }
        
        if (sum == 0) {
            return 65536;
        }
        
        sum -= same;
        double temp = same / sum * 65536;
        answer = (int)temp;

        return answer;
    }
    
    public List<String> convertString(String str) {
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            
            if (!validate(c1) || !validate(c2)) {
                continue;
            }
            
            String sub = String.valueOf(new char[]{c1, c2});
            result.add(sub);
        }
        return result;
    }
    
    public boolean validate(char c) {
        if ('A' <= c && c <= 'Z') {
            return true;
        }
        return false;
    }
}