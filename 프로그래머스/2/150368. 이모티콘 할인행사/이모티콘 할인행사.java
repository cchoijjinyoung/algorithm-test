class Solution {
    int max = 0;
    int maxPrice = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        // {10, 20, 30, 40}
        // dfs => 10 20 30 40에서 인덱스가 증가할 때마다 반복문을 통해 인덱스마다 10 20 30 40
        int[] percent = new int[]{10, 20, 30, 40};
        int[] discount = new int[emoticons.length];
        
        dfs(users, emoticons, discount, percent, 0);
        
        int[] answer = new int[]{max, maxPrice};
        return answer;
    }
    
    public void dfs(int[][] users, int[] emoticons, int[] discount, int[] percent, int depth) {
        if (depth == emoticons.length) {
            // 이모티콘별 할인율 모두 적용 완료
            // 계산
            calc(users, emoticons, discount, percent);
            return;
        }
        
        // 채워주기
        for (int i = 0; i < 4; i++) {
            discount[depth] = percent[i];
            dfs(users, emoticons, discount, percent, depth + 1);
        }
    }
    
    public void calc(int[][] users, int[] emoticons, int[] discount, int[] percent) {
        int cnt = 0;
        int totalPrice = 0;
        for (int i = 0; i < users.length; i++) {
            int price = 0;
            // user 별로 계산해서 최대금액이 넘어가는 지 확인해야해.
        
            for (int j = 0; j < emoticons.length; j++) {
                if (discount[j] >= users[i][0]) {
                    price += emoticons[j] / 100 * (100 - discount[j]);
                }
            }
            if (price >= users[i][1]) {
                cnt++;
            } else {
                totalPrice += price;
            }
        }
        if (cnt > max) {
            max = cnt;
            maxPrice = totalPrice;
        } else if (cnt == max && totalPrice > maxPrice) {
            maxPrice = totalPrice;
        }
    }
}