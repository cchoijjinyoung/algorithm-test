
import java.util.Scanner;

// 사탕 게임
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    while (T-- > 0) {
      int H = sc.nextInt();
      int W = sc.nextInt();
      int N = sc.nextInt();

      int floor = (N - 1) % H + 1;
      int distance = (N - 1) / H + 1;

      System.out.printf("%d%02d\n", floor, distance);
    }
  }
}
