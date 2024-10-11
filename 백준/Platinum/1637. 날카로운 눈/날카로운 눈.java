
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static int N;
  static Pocket[] pockets;
  static StringBuilder sb = new StringBuilder();

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    pockets = new Pocket[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int A = Integer.parseInt(st.nextToken());
      int C = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());

      pockets[i] = new Pocket();
      pockets[i].A = A;
      pockets[i].B = B;
      pockets[i].C = C;
    }
  }

  static long calc(long x) {
    // 모든 포켓마다 x 이하의 숫자가 몇개인지 센다.
    long cnt = 0;
    for (int i = 0; i < N; i++) {
      int A = pockets[i].A;
      int B = pockets[i].B;
      int C = pockets[i].C;
      // 포켓의 최솟값이 value 보다 크면 다음 포켓으로.
      if (A > x) continue;
      if (C < x) cnt += (C - A) / B + 1;
      else cnt += (x - A) / B + 1;
    }
    return cnt;
  }

  public static void main(String[] args) throws Exception {
    input();

    long answer = 0;
    long count = 0;

    long L = 1; long R = Integer.MAX_VALUE;
    while (L <= R) {
      long mid = (L + R) / 2;
      long result = calc((int) mid);
      if (result % 2 == 1) {
        answer = mid;
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }

    if (answer == 0) {
      System.out.println("NOTHING");
    } else {
      // 갯수 구하기
      for (int i = 0; i < N; i++) {
        int A = pockets[i].A;
        int B = pockets[i].B;
        int C = pockets[i].C;

        if (A > L) continue;
        if (C < L) continue;

        if ((L - A) % B == 0) {
          count++;
        }
      }
      System.out.println(answer + " " + count);
    }
  }

  static class Pocket {
    int A;
    int B;
    int C;
  }
}