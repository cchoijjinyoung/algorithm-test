class Solution {
    int[] percent;
    int maxPlus = Integer.MIN_VALUE;
    int maxAmount = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        int[] per = new int[emoticons.length];
        
        percent = new int[]{10, 20, 30, 40};
        
        dfs(users, emoticons, per, 0);
        
        answer[0] = maxPlus; answer[1] = maxAmount;
        return answer;
    }
    
    public void dfs(int[][] users, int[] emoticons, int[] per, int depth) {
        if (depth == per.length) {
            // 계산 로직
            calc(users, emoticons, per);
            return;
        }
        
        for (int i = 0; i < percent.length; i++) {
            per[depth] = percent[i];
            dfs(users, emoticons, per, depth + 1);
        }
    }
    
    public void calc(int[][] users, int[] emoticons, int[] per) {
        int plus = 0;
        int amount = 0;
        // 유저마다 per배열을 보면서 가입하는 지, 안한다면 가격은 얼마가 나오는 지 계산
        for (int i = 0; i < users.length; i++) {
            int discount = users[i][0];
            int limit = users[i][1];
            int userAmount = 0;
            for (int j = 0; j < per.length; j++) {
                // 본인의 기준할인율보다 높으면 구매
                if (per[j] >= discount) {
                    userAmount += emoticons[j] / 100 * (100 - per[j]);
                }
            }
            if (userAmount >= limit) {
                userAmount = 0;
                plus++;
            }
            amount += userAmount;
        }
        if (plus > maxPlus) {
            maxPlus = plus;
            maxAmount = amount;
        } else if (plus == maxPlus && amount > maxAmount) {
            maxAmount = amount;
        }
    }
}