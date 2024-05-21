import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt();
        int[] answer;
        if (arr.length > 1) {
            answer = new int[arr.length - 1];
            int idx = 0;
            for (int i : arr) {
                if (i != min) {
                    answer[idx++] = i;
                }
            }
        } else {
            answer = new int[]{-1};
        }
        return answer;
    }
}