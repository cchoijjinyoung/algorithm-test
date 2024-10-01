import java.util.*;

class Solution {
    Map<String, ArrayList<Integer>> hm;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        hm = new HashMap<>();
        
        for (int i = 0; i < info.length; i++) {
            dfs(info[i].split(" "), "", 0);
        }
        
        // 리스트에 저장되어있는 score들을 이분탐색으로 찾기 위해 오름차순 정렬
        for (List<Integer> list : hm.values()) {
            Collections.sort(list);
        }
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = BinarySearch(query[i]);
        }
        
        return answer;
    }
    
    public void dfs(String[] info, String result, int depth) {
        if (depth == 4) {
            int score = Integer.parseInt(info[4]);
            
            if (hm.containsKey(result)) {
                hm.get(result).add(score);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(score);
                hm.put(result, list);
            }
            return;
        }
        
        dfs(info, result + "-", depth + 1);
        dfs(info, result + info[depth], depth + 1);
    }
    
    public int BinarySearch(String query) {
        String[] arr = query.split(" and ");
        int score = Integer.parseInt(arr[3].split(" ")[1]);
        
        query = arr[0] + arr[1] + arr[2] + arr[3].split(" ")[0];
        
        if (!hm.containsKey(query)) {
            return 0;
        }
        
        List<Integer> list = hm.get(query);
        int lt = 0;
        int rt = list.size();
        
        while (lt < rt) {
            int mid = (lt + rt) / 2;
            
            if (list.get(mid) >= score) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }
        
        return list.size() - rt;
    }
}