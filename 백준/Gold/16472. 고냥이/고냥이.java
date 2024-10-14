
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static int N;
  static char[] A; // 문자
  static int[] visited;
  static int count = 0;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    visited = new int[26]; // 0이 a

    // abbcaccba
    String s = br.readLine();
    A = new char[s.length() + 1];
    for (int i = 1; i <= s.length(); i++) {
      A[i] = s.charAt(i - 1); // A[1];
    }
  }

  static boolean visited(int idx) {
    return visited[A[idx] - 'a'] > 0;
  }

  static void visit(int idx) {
    visited[A[idx] - 'a']++;
    if (visited[A[idx] - 'a'] == 1) {
      count++;
    }
  }

  static void leave(int idx) {
    visited[A[idx] - 'a']--;
    if (visited[A[idx] - 'a'] == 0) {
      count--;
    }
  }

  static void pro() {
    int answer = 0;
    int R = 0;
    for (int L = 1; L < A.length; L++) {
      while (R + 1 < A.length) {
        // 다음 알파벳이 처음보는 문자면,
        if (!visited(R + 1)) {
          if (count < N) { // 현재 인식 갯수가 N보다 작으면 인식해도됨
            R++;
            visit(R);
          } else {
            break;
          }
        } else { // 다음 알파벳을 인식했었으면,
          R++;
          visit(R);
        }
      }
      answer = Math.max(answer, R - L + 1);
      leave(L);
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}