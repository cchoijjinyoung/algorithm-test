class Solution {
    static int di;
    static int pi;
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        di = n - 1;
        pi = n - 1;
        long answer = 0;
        
        while (true) {
            // 뒤에서 부터 amount가 0보다 큰 지점을 찾는다.
            int d = findDeli(deliveries);
            int p = findPick(pickups);
            
            int move = Math.max(d, p);
            answer += move * 2;
            
            if (move == 0) {
                break;
            }
            
            updateDeli(cap, deliveries);
            updatePick(cap, pickups);
        }
        return answer;
    }
    
    public void updatePick(int cap, int[] pickups) {
        int temp = 0;
        // di 업데이트 및 배달 반영
        for (int i = pi; i >= 0; i--) {
            int amount = pickups[i];
            if (amount - cap >= 0) {
                pickups[i] -= cap;
                temp = i;
                break;
            } else {
                cap -= pickups[i];
                pickups[i] = 0;
                temp = i;
            }
        }
        pi = temp;
    }
    
    public void updateDeli(int cap, int[] deliveries) {
        int temp = 0;
        // di 업데이트 및 배달 반영
        for (int i = di; i >= 0; i--) {
            int amount = deliveries[i];
            if (amount - cap >= 0) {
                deliveries[i] -= cap;
                temp = i;
                break;
            } else {
                cap -= deliveries[i];
                deliveries[i] = 0;
                temp = i;
            }
        }
        di = temp;
    }
    
    
    public int findPick(int[] pickups) {
        for (int i = pi; i >= 0; i--) {
            if (pickups[i] > 0) {
                return i + 1;
            }
        }
        return 0;
    }
    
    public int findDeli(int[] deliveries) {
        for (int i = di; i >= 0; i--) {
            if (deliveries[i] > 0) {
                return i + 1;
            }
        }
        return 0;
    }
}