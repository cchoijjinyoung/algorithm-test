
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 줄 세우기
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());

    int[] cnt = new int[10001];
    for (int i = 0; i < N; i++) {
      cnt[Integer.parseInt(br.readLine())]++;
    }

    for (int i = 0; i < 10000; i++) {
      while (cnt[i + 1]-- > 0) {
        bw.write(i + 1 + "\n");
      }
    }
    bw.flush();
    bw.close();
    br.close();
  }
}
