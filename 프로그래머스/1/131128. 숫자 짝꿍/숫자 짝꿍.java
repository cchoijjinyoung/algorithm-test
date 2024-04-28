import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();
        // 인덱스에 해당하는 숫자의 개수를 담을 배열
        int[] arrX = new int[10];
        int[] arrY = new int[10];
        
        // X와 Y에 등장하는 숫자의 개수를 arrX, arrY 배열에 저장
        countNumInArr(X, arrX);
        countNumInArr(Y, arrY);
        
        // 역순으로 순회하여 공통으로 존재하는 숫자를 answer에 추가
        for (int i = arrX.length - 1; i >= 0; i--) {
            while (arrX[i] >= 1 && arrY[i] >= 1) {
                arrX[i]--;
                arrY[i]--;
                
                answer.append(i);
            }
        }
        
        if (answer.toString().equals("")) {
            return "-1";
        } else if (answer.toString().startsWith("0")) {
            return "0";
        } else {
            return answer.toString();
        }
    }
    
    // 문자열에 있는 숫자 개수를 배열에 저장하는 메서드
    private void countNumInArr(String str, int[] arr) {
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - '0';
            arr[idx]++;
        }
    }
}