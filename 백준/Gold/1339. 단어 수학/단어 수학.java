
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] alpha = new int[26];
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      char[] arr = line.toCharArray();
      for (int j = 0; j < arr.length; j++) {
        alpha[arr[j] - 'A'] += (int) Math.pow(10, arr.length - 1 - j);
      }
    }
  }

  static void pro() {
    int answer = 0;
    int multi = 9;
    Queue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
    for (int i = 0; i < 26; i++) {
      if (alpha[i] == 0) {
        continue;
      }
      pq.add(alpha[i]);
    }

    while (!pq.isEmpty()) {
      int sum = pq.poll();
      sum *= multi--;
      answer += sum;
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}