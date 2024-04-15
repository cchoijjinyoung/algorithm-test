import java.io.IOException;
import java.util.Scanner;

// 성 지키기
public class Main {

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    char[][] map = new char[N][M];
    for (int i = 0; i < N; i++) {
      map[i] = sc.next().toCharArray();
    }

    boolean[] existRow = new boolean[N];
    boolean[] existCol = new boolean[M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 'X') {
          existRow[i] = true;
          existCol[j] = true;
        }
      }
    }

    int needRowCount = N;
    int needColCount = M;

    for (int i = 0; i < N; i++) {
      if (existRow[i]) {
        needRowCount--;
      }
    }

    for (int j = 0; j < M; j++) {
      if (existCol[j]) {
        needColCount--;
      }
    }

    System.out.println(Math.max(needRowCount, needColCount));
  }
}
