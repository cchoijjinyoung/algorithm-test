import java.util.*;

class Solution {
    Map<String, Integer> hm;
    List<Set<String>> list;
    int[] maxCounts;
    public String[] solution(String[] orders, int[] course) {
        // 브루트 포스로 풀어보자.
        // 모든 경우의 수를 map에 담는다.
        hm = new HashMap<>();
        
        // list.get(i) : i 갯수로 만들어진 코스들의 집합
        list = new ArrayList<>();
        
        // 갯수별 최대값 저장
        maxCounts = new int[11];
        
        // set리스트 초기화
        for (int i = 0; i <= 10; i++) {
            list.add(new HashSet<>());
        }
        
        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < orders.length; j++) {
                if (course[i] > orders[j].length()) {
                    continue;
                }
                boolean[] visited = new boolean[orders[j].length()];
                char[] order = orders[j].toCharArray();
                Arrays.sort(order);
                // orders[j]로 course[i] 갯수의 메뉴 조합 완성헤서 map에 담기
                dfs(course[i], order, visited, new char[course[i]], 0, 0);
            }
        }
        
        List<String> temp = new ArrayList<>();
        // map에 다 담았다면, 
        for (int i : course) {
            Set<String> set = list.get(i);
            for (String s : set) {
                int count = hm.get(s);
                if (count == 1) {
                    continue;
                }
                
                if (count == maxCounts[i]) {
                    temp.add(s);
                }
            }
        }
        
        Collections.sort(temp);
        
        String[] answer = new String[temp.size()];
        
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        
        return answer;
    }
    
    public void dfs(int course, char[] order, boolean[] visited, char[] arr, int idx, int depth) {
        if (depth == course) {
            String result = new String(arr);
            hm.put(result, hm.getOrDefault(result, 0) + 1);
            list.get(course).add(result);
            maxCounts[course] = Math.max(maxCounts[course], hm.get(result));
            return;
        }
        
        for (int i = idx; i < order.length; i++) {
            if (visited[i]) {
                continue;
            }
            arr[depth] = order[i];
            visited[i] = true;
            dfs(course, order, visited, arr, i + 1, depth + 1);
            visited[i] = false;
        }
    }
}