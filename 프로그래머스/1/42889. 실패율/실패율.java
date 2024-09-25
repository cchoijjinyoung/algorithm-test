import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        PriorityQueue<double[]> pq = new PriorityQueue<>(
            (o1, o2) -> o1[1] == o2[1] ? Double.compare(o1[0], o2[0]) : Double.compare(o2[1], o1[1])
        );
        
        int userCnt = stages.length;
        int[] stay = new int[N + 2];
        for (int i = 0; i < userCnt; i++) {
            stay[stages[i]]++;
        }
        
        for (int i = 1; i < stay.length - 1; i++) {
            if (stay[i] == 0) {
                pq.offer(new double[]{i, 0});
                continue;
            }
            double failRate = (double)stay[i] / userCnt;
            pq.offer(new double[]{i, failRate});
            userCnt -= stay[i];
        }
        
        int idx = 0;
        while (!pq.isEmpty()) {
            double[] d = pq.poll();
            answer[idx++] = (int)d[0];
        }
        
        return answer;
    }
}