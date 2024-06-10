class Solution {
    int answer = 0;
    
    public int solution(int n, String[] data) {
        
        char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        boolean[] visited = new boolean[8];
        char[] seq = new char[8];
        
        dfs(n, data, seq, friends, visited, 0);
        
        return answer;
    }
    
    public void dfs(int n, String[] data, char[] seq, char[] friends, boolean[] visited, int depth) {
        if (depth == seq.length) {
            if (validation(n, data, seq)) {
                answer++;
            }
            return;
        }
        
        for (int i = 0; i < friends.length; i++) {
            if (visited[i]) {
                continue;
            }
            seq[depth] = friends[i];
            visited[i] = true;
            dfs(n, data, seq, friends, visited, depth + 1);
            visited[i] = false;
        }
    }
    
    public boolean validation(int n, String[] data, char[] seq) {
        String line = String.valueOf(seq);
        
        for (int i = 0; i < n; i++) {
            char[] fx = data[i].toCharArray();
            char f1 = fx[0]; // 친구1
            char f2 = fx[2]; // 친구2
            char op = fx[3]; // 기호
            int dist = (int)fx[4] - '0'; // 거리
            
            int f1i = line.indexOf(f1);
            int f2i = line.indexOf(f2);
            
            // 기호에 따라 조건식
            if (op == '=') { // 같으면
                if (Math.abs(f1i - f2i) - 1 != dist) {
                    return false;
                }
                continue;
            }
            
            if (op == '>') { // 초과
                if (Math.abs(f1i - f2i) - 1 <= dist) {
                    return false;
                }
                continue;
            }
            
            if (op == '<') { // 미만
                if (Math.abs(f1i - f2i) - 1 >= dist) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
}