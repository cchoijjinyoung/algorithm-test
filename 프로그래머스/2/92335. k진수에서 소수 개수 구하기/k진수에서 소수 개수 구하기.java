import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String s = Integer.toString(n, k);
        
        String[] splited = s.split("0+");
        
        long[] arr = new long[splited.length];
        for (int i = 0; i < splited.length; i++) {
            arr[i] = Long.parseLong(splited[i]);
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (isPrime(arr[i])) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean isPrime(long n) {
        if (n == 0 || n == 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}