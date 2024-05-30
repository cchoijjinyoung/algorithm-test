class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        // length만큼 순회해서 인덱스마다 answer에 채워줘
        for (int i = 0; i < places.length; i++) {
            answer[i] = calc(places[i]);
        }
        return answer;
    }
    
    public int calc(String[] places) {
        // places를 순회를 해서 'P'에 다다르면,
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (places[i].charAt(j) == 'P') {
                    if (!dintancing(i, j, places)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
    
    public boolean dintancing(int x, int y, String[] places) {
        if (validation(x + 1, y) && places[x + 1].charAt(y) == 'P') {
            return false;
        }
        
        if (validation(x, y + 1) && places[x].charAt(y + 1) == 'P') {
            return false;
        }
        
        if (validation(x + 2, y) && places[x + 2].charAt(y) == 'P') {
            if (places[x + 1].charAt(y) != 'X') {
                return false;
            }
        }
        
        if (validation(x, y + 2) && places[x].charAt(y + 2) == 'P') {
            if (places[x].charAt(y + 1) != 'X') {
                return false;
            }
        }
        
        if (validation(x + 1, y + 1) && places[x + 1].charAt(y + 1) == 'P') {
            if (places[x].charAt(y + 1) != 'X') {
                return false;
            }
            if (places[x + 1].charAt(y) != 'X') {
                return false;
            }
        }
        
        if (validation(x + 1, y - 1) && places[x + 1].charAt(y - 1) == 'P') {
            if (places[x].charAt(y - 1) != 'X') {
                return false;
            }
            if (places[x + 1].charAt(y) != 'X') {
                return false;
            }
        }
        return true;
    }
    
    public boolean validation(int x, int y) {
        if (x >= 5 || y >= 5 || x < 0 || y < 0) {
            return false;
        }
        return true;
    }
}