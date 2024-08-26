import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        int possible = 1;
        
        int count = 1;
        while (true) {
            if (N <= possible) {
                break;
            }
            possible += count * 6;
            count++;
        }
        System.out.println(count);
    }
}