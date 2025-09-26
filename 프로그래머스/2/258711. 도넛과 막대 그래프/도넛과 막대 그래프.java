import java.util.*;

class Solution {
    static int N = 1_000_000;
    static int[] out;
    static int[] in;
    public int[] solution(int[][] edges) {
        out = new int[N + 1];
        in = new int[N + 1];
        
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            out[a]++;
            in[b]++;
        }
        
        // 만들어진 정점 찾기
        int createdNode = -1;
        for (int node = 1; node <= N; node++) {
            if (out[node] >= 2 && in[node] == 0) {
                createdNode = node;
                break;
            }
        }
        
        int donut = 0;
        int eight = 0;
        int stick = 0;
        for (int i = 1; i <= N; i++) {
            if (i == createdNode) {
                continue;
            }
            
            if (out[i] >= 2) {
                eight++;
            } else if (out[i] == 0 && in[i] > 0) {
                stick++;
            }
        }
        
        donut = out[createdNode] - eight - stick;
        
        return new int[]{createdNode, donut, stick, eight};
    }
}