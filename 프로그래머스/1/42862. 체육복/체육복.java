import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] arr = new int[n + 1];
        Arrays.fill(arr, 1);
        
        for (int i = 0; i < lost.length; i++) {
            arr[lost[i]]--;
        }
        
        for (int i = 0; i < reserve.length; i++) {
            arr[reserve[i]]++;
        }
        
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] == 2) {
                if (arr[i - 1] == 0) {
                    arr[i]--;
                    arr[i - 1]++;
                } else if (arr[i + 1] == 0) {
                    arr[i]--;
                    arr[i + 1]++;
                }
            }
        }
        
        if (arr[arr.length - 1] == 2 && arr[arr.length - 2] == 0) {
            arr[arr.length - 1]--;
            arr[arr.length - 2]++;
        } 
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= 1) {
                answer++;
            }
        }
        return answer;
    }
}