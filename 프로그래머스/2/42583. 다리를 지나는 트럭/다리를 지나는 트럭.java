import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> waiting = new LinkedList<>();
        Queue<int[]> bridge = new LinkedList<>();
        
        // 트럭을 줄 세운다.
        for (int truck : truck_weights) {
            waiting.add(truck);
        }
        int curWeight = 0;
        int curTime = 0;
        
        // 트럭이 모두 지나갈 때까지
        while (!waiting.isEmpty()) {
            // 1초 후
            curTime++;
            
            // 1초 후 나가는 트럭 poll
            if (!bridge.isEmpty()) {
                if (bridge.peek()[1] == curTime) {
                    int[] truck = bridge.poll();
                    curWeight -= truck[0];
                }
            }
            
            // 대기 중인 트럭을 다리 위에 올라갈 수 있으면, 다리 위에 올린다.
            int waitTruck = waiting.peek();
            if (curWeight + waitTruck <= weight && bridge.size() < bridge_length) {
                waiting.poll();
                bridge.add(new int[]{waitTruck, curTime + bridge_length});
                curWeight += waitTruck;
            }
        }
        return curTime + bridge_length;
    }
                    
}