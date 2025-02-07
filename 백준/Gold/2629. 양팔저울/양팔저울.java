
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int chuLength, beadLength;
  static int[] chu, bead;
  static boolean[][] result;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    chuLength = Integer.parseInt(br.readLine());
    chu = new int[chuLength];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < chuLength; i++) {
      chu[i] = Integer.parseInt(st.nextToken());
    }

    beadLength = Integer.parseInt(br.readLine());
    bead = new int[beadLength];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < beadLength; i++) {
      bead[i] = Integer.parseInt(st.nextToken());
    }

    result = new boolean[chuLength + 1][40001];
  }

  static void pro() {
    dp(0, 0);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bead.length; i++) {
      if (result[chuLength][bead[i]]) {
        sb.append("Y ");
      } else {
        sb.append("N ");
      }
    }
    System.out.println(sb.toString());
  }
  
  static void dp(int cnt, int num) {
    if (result[cnt][num]) {
      return;
    }
    
    result[cnt][num] = true;

    if (cnt == chuLength) {
      return;
    }

    dp(cnt + 1, num);
    dp(cnt + 1, num + chu[cnt]);
    dp(cnt + 1, Math.abs(num - chu[cnt]));
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}