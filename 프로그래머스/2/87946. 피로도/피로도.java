class Solution {
    int max = 0;
    public int solution(int k, int[][] dungeons) {
        int[] seq = new int[dungeons.length];
        boolean[] visited = new boolean[dungeons.length];
        
        // 최소 필요도, 소모 피로도가 있다.
        // 최대한 많이 탐험하려한다.
        // 유저가 탐험할 수 있는 최대 던전 수는?
        
        // dfs로 모든 순서를 계산
        dfs(k, dungeons, seq, visited, 0);
        return max;
    }
    
    public void dfs(int k, int[][] dungeons, int[] seq, boolean[] visited, int depth) {
        if (depth == seq.length) {
            calc(k, dungeons, seq);
            return;
        }
        
        for (int i = 0; i < dungeons.length; i++) {
            // seq = {1, 0, 2};
            // visit [1, 1, 0];
            // 0, 1, 2
            // 0, 2, 1
            if (visited[i]) {
                continue;
            }
            seq[depth] = i;
            visited[i] = true;
            dfs(k, dungeons, seq, visited, depth + 1);
            visited[i] = false;
        }
    }
    
    public void calc(int k, int[][] dungeons, int[] seq) {
        int count = 0;
        // seq를 순회하면서 몇번째 idx까지 탐험할 수 있는 지,
        for (int i = 0; i < seq.length; i++) {
            // 현재피로도(k)가 최소피로도 dungeons[0]보다 작으면 break;
            if (k < dungeons[seq[i]][0]) {
                break;
            }
            
            // 현재피로도가 최소피로도보다 크거나 같으면 현재피로도 - dungeons[1];
            // count++;
            k -= dungeons[seq[i]][1];
            count++;
        }
        
        // max를 업데이트 시켜줘야함.
        if (count > max) {
            max = count;
        }
    }
}