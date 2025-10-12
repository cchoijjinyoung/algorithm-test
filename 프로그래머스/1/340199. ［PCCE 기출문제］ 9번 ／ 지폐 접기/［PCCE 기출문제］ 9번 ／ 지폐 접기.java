class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int max = Math.max(wallet[0], wallet[1]);
        int min = Math.min(wallet[0], wallet[1]);
        
        while (true) {
            int b_max = Math.max(bill[0], bill[1]);
            int b_min = Math.min(bill[0], bill[1]);
            
            if (b_max > max || b_min > min) {
                if (bill[0] > bill[1]) {
                    bill[0] /= 2;
                } else {
                    bill[1] /= 2;
                }
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
}