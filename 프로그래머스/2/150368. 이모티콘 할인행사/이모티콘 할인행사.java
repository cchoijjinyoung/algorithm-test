class Solution {
    static int maxSub = Integer.MIN_VALUE;
    static int maxTotal = Integer.MIN_VALUE;
    static int[] discount = new int[]{40, 30, 20, 10};
    public int[] solution(int[][] users, int[] emoticons) {
        // 이모티콘 <= 7
        // 40, 30, 20, 10 = 4가지
        // 4^7 = 2^10 * 2^4 = 16000
        // 유저 <= 100
        // 100 * 7 * 16000 = 7 * 16 * 100000 = 1억 안됨
        // dfs로 결정
        
        // 이모티콘 length 만큼의 할인 배열 생성(dfs)
        // 배열이 만들어지면 계산
        // for(사용자) - for(생성된 배열) - 총 금액 계산, 이모티콘 구매 기준 확인 -> 최대값 반영
        dfs(users, emoticons, 0, new int[emoticons.length]);
        int[] answer = new int[]{maxSub, maxTotal};
        return answer;
    }
    
    public void dfs(int[][] users, int[] emoticons, int depth, int[] arr) {
        if (depth == emoticons.length) {
            calc(users, emoticons, arr);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            arr[depth] = discount[i];
            dfs(users, emoticons, depth + 1, arr);
        }
    }
    
    public void calc(int[][] users, int[] emoticons, int[] arr) {
        int totalPrice = 0;
        int sub = 0;
        for (int i = 0; i < users.length; i++) {
            int[] user = users[i];
            int rate = user[0];
            int price = user[1];
            int sum = 0;
            for (int j = 0; j < arr.length; j++) {
                int cur = arr[j];
                // 만약 지금 이모티콘 할인율이 유저 기준 이상이라면
                if (cur >= rate) {
                    // 구매
                    sum += emoticons[j] * (100 - cur) / 100;
                }
            }
            // 이번 유저가 든 비용이 기준보다 이상이라면 구독
            if (sum >= price) {
                sub++;
                sum = 0;
            }
            totalPrice += sum;
        }
        
        // 구독 최대값 업데이트
        if (sub > maxSub) {
            maxSub = sub;
            maxTotal = totalPrice;
        } else if (sub == maxSub && totalPrice > maxTotal) {
            maxTotal = totalPrice;
        }
    }
    
}