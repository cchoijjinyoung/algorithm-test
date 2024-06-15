class Solution {
    int[] rate = new int[]{10, 20, 30, 40};
    int max = Integer.MIN_VALUE;
    int maxPrice = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 서비스 가입자 늘리는 것이 최우선
        // 안된다면 판매액을 늘리자
        
        // m개의 이모티콘을 팔건데, 이모티콘마다 할인율이 붙는다.(10, 20, 30, 40)
        // 자신의 비율 이상 할인하는 이모티콘 모두 구매 시에, 그 비용이 내 기준 가격을 넘어선다면, 가입함.
        
        // 이모티콘마다 할인율 부여.
        
        int[] per = new int[emoticons.length];
        
        dfs(users, emoticons, per, 0);
        
        return new int[]{max, maxPrice};
    }
    
    public void dfs(int[][] users, int[] emoticons, int[] per, int depth) {
        if (depth == per.length) {
            // 이모티콘마다 할인율을 부여했으면, 유저별로 가입 or 돈 계산
            calc(users, emoticons, per);
            
            return;
        }
        
        for (int i = 0; i < rate.length; i++) {
            per[depth] = rate[i];
            dfs(users, emoticons, per, depth + 1);
        }
    }
    
    public void calc(int[][] users, int[] emoticons, int[] per) {
        int cnt = 0;
        int price = 0;
        
        for (int i = 0; i < users.length; i++) {
            int ur = users[i][0]; // 유저의 비율
            int up = users[i][1]; // 유저의 가격
            
            int total = 0;
            for (int j = 0; j < emoticons.length; j++) {
                int er = per[j]; // 이모티콘 할인율
                
                // 만약 유저의 비율보다 할인율이 작다면 continue;
                if (ur > er) {
                    continue;
                }
                
                // 할인율이 유저 비율 이상이라면,
                int ep = emoticons[j] / 100 * (100 - per[j]); // 할인된 이모티콘 가격
                total += ep;
            }
            // 만약 해당 유저의 total이 '유저의 가격'이상이라면, 가입을 한다.
            if (total >= up) {
                cnt++;
            } else { // 아니라면 판매액 += 유저 지불 금액
                price += total;
            }
        }
        if (max < cnt) {
            max = cnt;
            maxPrice = price;
        } else if (max == cnt) {
            if (maxPrice < price) {
                maxPrice = price;
            }
        }
    }
}