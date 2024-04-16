import java.util.Scanner;

// 진법 변환 2
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int B = sc.nextInt();

    String ans = "";
    while (N > 0) {
      int balance = N % B;
      if (balance < 10) {
        ans += balance;
      } else {
        ans += (char)(balance - 10 + 'A');
      }
      N = N / B;
    }
    for (int i = ans.length() - 1; i >= 0; i--) {
      System.out.print(ans.charAt(i));
    }
    System.out.println();
  }
}
