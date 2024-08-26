import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int S = 0; // 공집합 - 21개의 비트로 1~20의 숫자가 현재 집합에 존재하는 지 파악할 예정(0번째 비트는 사용하지 X)
    int M = Integer.parseInt(br.readLine());
        
    StringTokenizer st;
    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      String op = st.nextToken();

      if ("all".equals(op)) {
        S = (1 << 21) - 1; // 21개의 1이 형성됨
      }

      else if ("empty".equals(op)) {
        S = 0;
      }
        
      else {

          int num = Integer.parseInt(st.nextToken());
          if ("add".equals(op)) {
            S |= (1 << num);
          } else if ("remove".equals(op)) {
            S &= ~(1 << num);
          } else if ("check".equals(op)) {
            sb.append((S & (1 << num)) != 0 ? 1 : 0).append("\n");
          } else if ("toggle".equals(op)) {
            S ^= (1 << num);
          }
        }
    }
    System.out.println(sb);
  }
}