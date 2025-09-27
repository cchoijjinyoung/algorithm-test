import java.util.*;

class Solution {
    static int n;
    static List<Integer> answer = new ArrayList<>();
    static long maxWin = -1;
    static int[][] dices;
    public int[] solution(int[][] dice) {
        n = dice.length;
        dices = dice;
        combination(0, new ArrayList<>());
        
        return answer.stream().mapToInt(i -> i + 1).toArray();
    }
    
    static void combination(int start, List<Integer> pick) {
        if (pick.size() == n / 2) {
            cal(pick);
            return;
        }
        
        for (int i = start; i < n; i++) {
            pick.add(i);
            combination(i + 1, pick);
            pick.remove(pick.size() - 1);
        }
    }
    
    static void cal(List<Integer> pick) {
        List<Integer> a = new ArrayList<>(pick);
        List<Integer> b = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (!a.contains(i)) b.add(i);
        }
        
        List<Integer> aSum = getSum(a);
        List<Integer> bSum = getSum(b);
        
        Collections.sort(bSum);
        
        long win = 0;
        for (int sum : aSum) {
            win += lowerBound(bSum, sum);
        }
        
        if (win > maxWin) {
            maxWin = win;
            answer = new ArrayList<>(a);
        }
    }
    
    static List<Integer> getSum(List<Integer> pick) {
        List<Integer> result = new ArrayList<>();
        // pick, depth, sum, 반환리스트
        dfs(pick, 0, 0, result);
        return result;
    }
    
    static void dfs(List<Integer> pick, int depth, int sum, List<Integer> result) {
        // 마지막 주사위까지 골랐다면,
        if (depth == pick.size()) {
            result.add(sum);
            return;
        }
        
        int idx = pick.get(depth);
        for (int num : dices[idx]) {
            dfs(pick, depth + 1, sum + num, result);
        }
    }
    
    static int lowerBound(List<Integer> list, int target) {
        int L = 0;
        int R = list.size();
        while (L < R) {
            int mid = (L + R) / 2;
            if (list.get(mid) < target) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }
        return L;
    }
}