class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int Amax = arrayA[0]; // arrayA의 최대공약수를 담을 변수
        int Bmax = arrayB[0]; // arrayB의 최대공약수를 담을 변수
        
        for (int i = 1; i < arrayA.length; i++) {
            Amax = calc(Amax, arrayA[i]);
            Bmax = calc(Bmax, arrayB[i]);
        }
        
        boolean Aflag = false;
        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] % Bmax == 0) {
                Aflag = true;
                break;
            }
        }
        
        boolean Bflag = false;
        for (int i = 0; i < arrayB.length; i++) {
            if (arrayB[i] % Amax == 0) {
                Bflag = true;
                break;
            }
        }
        
        if (!Aflag && !Bflag) {
            answer = Math.max(Amax, Bmax);
        } else if (!Aflag) {
            answer = Bmax;
        } else if (!Bflag) {
            answer = Amax;
        }
        
        return answer;
    }
    
    public int calc(int a, int b) {
        
        int x = Math.max(a, b);
        int y = Math.min(a, b);
        
        if (x % y == 0) {
            return y;
        } else {
            return calc(x % y, y);
        }
    }
}