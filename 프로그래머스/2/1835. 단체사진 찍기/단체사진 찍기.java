class Solution {
    int answer = 0;
    
    public int solution(int n, String[] data) {
        // dfs를 돌려서 줄을 세운다.
        // 모든 경우의 줄이 data의 조건들을 만족하는 지 확인.
        char[] names = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        char[] line = new char[8];
        boolean[] visited = new boolean[8];
        
        dfs(data, names, line, visited, 0);
        
        
        return answer;
    }
    
    public void dfs(String[] data, char[] names, char[] line, boolean[] visited, int depth) {
        if (depth == line.length) {
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
            dfs(data, names, line, visited, depth + 1);
            visited[i] = false;
        }
    }
    
    public boolean validation(String[] data, char[] line) {
        // 조건을 꺼내서 현재 line에 성립하는 지 확인
        for (int i = 0; i < data.length; i++) {
            char[] arr = data[i].toCharArray();
            char f1 = arr[0];
            char f2 = arr[2];
            char op = arr[3];
            int dist = (int)(arr[4] - '0');
            
            String s = String.valueOf(line);
            int f1i = s.indexOf(f1);
            int f2i = s.indexOf(f2);
            
            int want = Math.abs(f1i - f2i) - 1;
            
            // 서로의 거리가 dist보다 크기를 원함
            if (op == '>') {
                if (want <= dist) {
                    return false;
                }
                continue;
            }
            
            // 서로의 거리가 dist보다 작기를 원함
            if (op == '<') {
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