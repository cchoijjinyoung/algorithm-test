import java.util.*;

class Solution {
    static int[] pointer;
    static String[] graph;
    static List<String> result = new ArrayList<>();
    public String[] solution(String[] commands) {
        // 포인터 생성: 각 셀이 어느 셀을 바라보고 있는지
        pointer = new int[2501];
        for (int i = 1; i <= 2500; i++) {
            pointer[i] = i;
        }
        
        // 값을 담을 배열 생성(초기에는 비어있다)
        graph = new String[2501];
        
        for (String command : commands) {
            input(command);
        }
        
        String[] answer = new String[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    public void input(String command) {
        String[] split = command.split(" ");
        String op = split[0];
        if ("UPDATE".equals(op)) {
            if (split.length == 4) { // r, c 위치의 셀을 value로 변경
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                String value = split[3];
                update(r, c, value);
            } else { // value1을 값으로 가지고 있는 모든 셀을 value2로 변경
                String value1 = split[1];
                String value2 = split[2];
                updateAll(value1, value2);
            }
        } else if ("MERGE".equals(op)) {
            int r1 = Integer.parseInt(split[1]);
            int c1 = Integer.parseInt(split[2]);
            int r2 = Integer.parseInt(split[3]);
            int c2 = Integer.parseInt(split[4]);
            merge(r1, c1, r2, c2);
        } else if ("UNMERGE".equals(op)) {
            int r = Integer.parseInt(split[1]);
            int c = Integer.parseInt(split[2]);
            unmerge(r, c);
        } else {
            int r = Integer.parseInt(split[1]);
            int c = Integer.parseInt(split[2]);
            print(r, c);
        }
    }
    
    public void update(int r, int c, String value) {
        int target = find(calcIdx(r, c));
        graph[target] = value;
    }
    
    public void updateAll(String value1, String value2) {
        for (int i = 1; i <= 2500; i++) {
            int target = find(i);
            if (graph[target] != null && graph[target].equals(value1)) {
                graph[target] = value2;
            }
        }
    }
    
    public void merge(int r1, int c1, int r2, int c2) {
        int a = find(calcIdx(r1, c1));
        int b = find(calcIdx(r2, c2));
        if (a == b) return; // 같은 셀이면 무시
        
        String value1 = graph[a];
        String value2 = graph[b];
        if (value1 == null) {
            pointer[a] = b;
        } else {
            pointer[b] = a;
        }
    }
    
    public void unmerge(int r, int c) {
        int target = find(calcIdx(r, c));
        String value = graph[target];
        List<Integer> members = new ArrayList<>();
        for (int i = 1; i <= 2500; i++) {
            if (find(i) == target) {
                members.add(i);
            }
        }
        
        for (int member : members) {
            pointer[member] = member;
            graph[member] = null;
        }
        graph[calcIdx(r, c)] = value;
    }
    
    public void print(int r, int c) {
        String value = graph[find(calcIdx(r, c))];
        if (value == null) {
            value = "EMPTY";
        }
        result.add(value);
    }
    
    public int find(int idx) {
        if (pointer[idx] == idx) {
            return idx;
        }
        return pointer[idx] = find(pointer[idx]);
    }
    
    public int calcIdx(int r, int c) {
        int idx = 50 * (r - 1) + c;
        return idx;
    }
}