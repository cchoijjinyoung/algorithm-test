class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        String b = "";
        String part = "";
        for (int i = 0; i < numbers.length; i++) {
            
            if (numbers[i] == 0) {
                answer[i] = 1;
                continue;
            }
            
            if (numbers[i] == 1) {
                answer[i] = 2;
                continue;
            }
            
            b = Long.toBinaryString(numbers[i]);
            
            part = b.substring(b.length() - 2);
            
            if ("10".equals(part) || "00".equals(part) || "01".equals(part)) {
                answer[i] = numbers[i] + 1;
                continue;
            }
            
            // 비트문자열 10111 => 11011
            if ("11".equals(part)) {
                int idx = 0;
                for (int j = b.length() - 1; j >= 0; j--) {
                    if (b.charAt(j) == '0') {
                        break;
                    }
                    idx++;
                }
                
                if (idx != 0) {
                    answer[i] = numbers[i] + (long)(Math.pow(2, idx - 1));
                    continue;
                }
                answer[i] = numbers[i] + (long)Math.pow(2, b.length() - 1);
            }
        }
        return answer;
    }
}