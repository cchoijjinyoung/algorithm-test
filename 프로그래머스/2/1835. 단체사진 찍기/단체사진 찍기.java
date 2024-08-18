class Solution {
    boolean[] visited;
    int answer = 0;
    public int solution(int n, String[] data) {
        
        char[] name = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        
        char[] seq = new char[8];
        
        visited = new boolean[8];
        
        dfs(data, name, seq, 0);
        
        return answer;
    }
    
    public void dfs(String[] data, char[] name, char[] seq, int depth) {
        if (depth == seq.length) {
            if (validate(seq, data)) {
                answer++;
            }
            return;
        }
        
        for (int i = 0; i < name.length; i++) {
            if (visited[i]) {
                continue;
            }
            seq[depth] = name[i];
            visited[i] = true;
            dfs(data, name, seq, depth + 1);
            visited[i] = false;
        }
    }
    
    public boolean validate(char[] seq, String[] data) {
        for (int i = 0; i < data.length; i++) {
            String s = data[i];
            char f1 = s.charAt(0);
            char f2 = s.charAt(2);
            char op = s.charAt(3);
            int want = s.charAt(4) - '0';
            
            String seqStr = String.valueOf(seq);
            int f1Idx = seqStr.indexOf(f1);
            int f2Idx = seqStr.indexOf(f2);
            
            int dist = Math.abs(f1Idx - f2Idx) - 1;
            
            if (op == '=') { // 같음
                if (want == dist) {
                    continue;
                }
                return false;
            }
            
            if (op == '<') { // 미만
                if (dist < want) {
                    continue;
                }
                return false;
            }
            
            if (op == '>') { // 초과
                if (dist > want) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}