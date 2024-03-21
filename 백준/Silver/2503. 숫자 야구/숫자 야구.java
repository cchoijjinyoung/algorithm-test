import java.util.Scanner;

// 슛자야구
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    int[][] list = new int[N][3];

    for (int i = 0; i < N; i++) {
      list[i][0] = sc.nextInt();
      list[i][1] = sc.nextInt();
      list[i][2] = sc.nextInt();
    }

    int cnt = 0;

    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= 9; j++) {
        for (int k = 1; k <= 9; k++) {
          // 111, 112 배제
          if (i == j || j == k || k == i) {
            continue;
          }

          boolean possible = true;

          for (int l = 0; l < list.length; l++) {
            int x = list[l][0] / 100;
            int y = (list[l][0] / 10) % 10;
            int z = list[l][0] % 10;

            int s = 0, b = 0;
            if (x == i) {
              s++;
            } else if (x == j || x == k) {
              b++;
            }
            if (y == j) {
              s++;
            } else if (y == k || y == i) {
              b++;
            }
            if (z == k) {
              s++;
            } else if (z == i || z == j) {
              b++;
            }

            if (s != list[l][1] || b != list[l][2]) {
              possible = false;
              break;
            }
          }
          if (possible) cnt++;
        }
      }
    }
    System.out.println(cnt);
  }
}
