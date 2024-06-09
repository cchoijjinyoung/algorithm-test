import java.util.LinkedList;

class Solution {
    public int solution(int[] priorities, int location) {
       int answer = 0;

        LinkedList<Integer> processes =  new LinkedList<>();
        LinkedList<Integer> locations = new LinkedList<>();

        // list에 배열을 담는다.
        for (int i = 0; i < priorities.length; i++) {
            processes.add(priorities[i]);
            locations.add(i);
        }

        while (!processes.isEmpty()) {
            boolean isPriority = true;
            int curPrio = processes.poll();
            int curIdx  = locations.poll();
            for (int prio : processes) {
                if (prio > curPrio) {
                    isPriority = false;
                    break;
                }
            }

            if (isPriority) {
                answer++;
                if (curIdx == location) {
                    break;
                }
            } else {
                processes.add(curPrio);
                locations.add(curIdx);
            }
        }
        System.out.println(answer);
        return answer;
    }
}