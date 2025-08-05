
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N, C;
  static List<Integer> weight1, weight2;
  static List<Integer> sum1, sum2;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    weight1 = new ArrayList<>();
    weight2 = new ArrayList<>();
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      if (i < N / 2) {
        weight1.add(Integer.parseInt(st.nextToken()));
      } else {
        weight2.add(Integer.parseInt(st.nextToken()));
      }
    }

    sum1 = new ArrayList<>();
    sum2 = new ArrayList<>();
  }

  static void pro() {
    dfs(0, 0, weight1, sum1);
    dfs(0, 0, weight2, sum2);
    Collections.sort(sum2);
    int answer = 0;
    for (int i = 0; i < sum1.size(); i++) {
      int searchValue = C - sum1.get(i);
      answer += binarySearch(sum2, searchValue) + 1;
    }

    System.out.println(answer);
  }

  static int binarySearch(List<Integer> sum, int target) {
    int L = 0, R = sum.size() - 1, mid, answer = -1;
    while (L <= R) {
      mid = (L + R) / 2;
      if (sum.get(mid) <= target) {
        answer = mid;
        L = mid + 1;
      } else {
        R = mid - 1;
      }
    }
    return answer;
  }

  static void dfs(int idx, int sum, List<Integer> weight, List<Integer> answer) {
    if (sum > C) return;
    if (idx == weight.size()) {
      answer.add(sum);
      return;
    }

    // 물건을 넣는 경우
    dfs(idx + 1, sum + weight.get(idx), weight, answer);
    // 안 넣는 경우
    dfs(idx + 1, sum, weight, answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}