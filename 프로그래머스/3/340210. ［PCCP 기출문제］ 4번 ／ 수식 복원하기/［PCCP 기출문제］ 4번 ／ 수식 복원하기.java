import java.util.*;

class Solution {
    public String[] solution(String[] expressions) throws Exception {
        int minEx = 2;
        // 수식들 순회
         for (String express : expressions) {
             // 문자 하나씩 보면서, 최소 진법 체크
             for (int i = 0; i < express.length(); i++) {
                 int c = express.charAt(i) - '0';
                 if (0 <= c && c <= 8) {
                     minEx = Math.max(minEx, c + 1);
                     if (c == 8) break;
                 }
             }
         }
        
        int count = 0;
        // 최소 진법을 구했으면, 수식을 하나씩 보면서, 진법 걸러내기
        Map<Integer, Integer> map = new HashMap<>();
        List<String> xExpress = new ArrayList<>();
        for (String express : expressions) {
            String[] info = express.split(" ");
            if (info[4].equals("X")) {
                xExpress.add(express);
                continue;
            }
            count++;
            for (int i = minEx; i <= 9; i++) {
                int[] converted = get(info, i);
                String op = info[1];
                if (check(converted, op)) {
                    map.put(i, map.getOrDefault(i, 0) + 1);
                } 
            }
        }

        List<Integer> candi = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == count) {
                candi.add(entry.getKey());
            }
        }
        
        if (candi.isEmpty()) {
            for (int i = minEx; i <= 9; i++) {
                candi.add(i);
            }
        }
        
        String[] answer = new String[xExpress.size()];
        
        for (int i = 0; i < xExpress.size(); i++) {
            String express = xExpress.get(i);
            String[] info = express.split(" ");
            
            Set<Integer> set = new HashSet<>();
            int value = 0;
            for (int radix : candi) {
                int a = convert(info[0], radix);
                int b = convert(info[2], radix);
                String op = info[1];
                
                int sum = a;
                if (op.equals("+")) {
                    sum += b;
                } else {
                    sum -= b;
                }
                sum = Integer.parseInt(Integer.toString(sum, radix));
                value = sum;
                set.add(sum);
            }
            if (set.size() > 1) {
                express = express.replace("X", "?");    
            } else {
                express = express.replace("X", String.valueOf(value));
            }
            
            answer[i] = express;
        }
        return answer;
    }
    
    public boolean check(int[] A, String op) {
        int a = A[0];
        int b = A[1];
        int result = A[2];
        if (op.equals("+")) {
            return a + b == result;
        } else {
            return a - b == result;
        }
    }
    
    public int[] get(String[] info, int radix) {
        String first = info[0];
        String second = info[2];
        String sum = info[4];
            
        return new int[]{convert(first, radix), convert(second, radix), convert(sum, radix)};
    }
    
    // 10진법으로 바꾸기
    public int convert(String s, int radix) {
        int element = 0;
        int multi = 1;
        for (int j = s.length() - 1; j >= 0; j--) {
            int number = s.charAt(j) - '0';
            element += number * multi;
            multi *= radix;
        }
        return element;
    }
}