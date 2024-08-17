class Solution {
    int answer = 0;
    boolean[] visited = new boolean[8];
    
    public int solution(int n, String[] data) {
        // dfs를 돌려서 줄을 세운다.
        // 모든 경우의 줄이 data의 조건들을 만족하는 지 확인.
        char[] names = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        char[] line = new char[8];
        
        dfs(data, names, line, 0);
        
        return answer;
    }
    
    public void dfs(String[] data, char[] names, char[] line, int depth) {
        // 8명이 모두 줄을 섰으면,
        if (depth == line.length) {
            // 조건을 충족하는 지 검증
            if (validation(data, line)) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < names.length; i++) {
            if (visited[i]) {
                continue;
            }
            line[depth] = names[i];
            visited[i] = true;
            dfs(data, names, line, depth + 1);
            visited[i] = false;
        }
    }
    
    public boolean validation(String[] data, char[] line) {
        // 조건을 꺼내서 현재 line에 성립하는 지 확인
        for (int i = 0; i < data.length; i++) {
            // 앞 친구
            char f1 = data[i].charAt(0);
            // 뒷 친구
            char f2 = data[i].charAt(2);
            // 부호
            char op = data[i].charAt(3);
            // 거리
            int dist = data[i].charAt(4) - '0';
            
            String s = String.valueOf(line);
            
            int f1i = s.indexOf(f1);
            int f2i = s.indexOf(f2);
            
            // 현재 친구들의 거리
            int want = Math.abs(f1i - f2i) - 1;
            
            // 서로의 거리가 dist보다 크기를 원함
            if (op == '>') {
                // 서로의 거리가 dist보다 작거나 같으면 안됨.
                if (want <= dist) {
                    return false;
                }
                continue;
            }
            
            // 서로의 거리가 dist보다 작기를 원함
            if (op == '<') {
                // 서로의 거리가 dist보다 크거나 같으면 안됨.
                if (want >= dist) {
                    return false;
                }
                continue;
            }
            
            // 서로의 거리가 dist와 같기를 원함
            if (op == '=') {
                if (want != dist) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
}