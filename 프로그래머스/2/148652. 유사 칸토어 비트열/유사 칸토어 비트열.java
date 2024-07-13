class Solution {
    public int solution(int n, long l, long r) {
        long ln = 0;
        long rn = 0;
        for(int i=0 ; i<= 20; i++){
            if(Math.pow(5,i) <= l-1 && l-1 <= Math.pow(5,i+1)){
                ln = i;
            }
            if(Math.pow(5,i) <= r && r <= Math.pow(5,i+1)){
                rn = i;
                break;
            }
        }
        return (int)(re(r,rn+1) - re(l-1,ln+1));
    }
    long re(long l, long n){
        if(n<1) return 0;
        if(l == 0) return 0;
        
        long m =l/(long)Math.pow(5,n-1);
        if(m==2) return m*(long)Math.pow(4,n-1);
        if(m>2) m-=1;
        return m*(long)Math.pow(4,n-1) + re(l%(long)Math.pow(5,n-1), n-1);
    }
}