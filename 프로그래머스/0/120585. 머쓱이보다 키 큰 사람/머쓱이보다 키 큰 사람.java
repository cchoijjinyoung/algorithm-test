import java.util.*;

class Solution {
    public int solution(int[] array, int height) {
        int answer = 0;
        for (int tall : array) {
            if (tall > height) answer++;
        }
        return answer;
    }
}