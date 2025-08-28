
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static List<List<Integer>> graph = new ArrayList<>();
  static List<Integer> firstList = new ArrayList<>();
  static boolean[] visited;
  static int[] count;
  static int[] result;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int from = 1; from <= N; from++) {
      st = new StringTokenizer(br.readLine());
      while (true) {
        int to = Integer.parseInt(st.nextToken());
        if (to == 0) {
          break;
        }
        graph.get(from).add(to);
        graph.get(to).add(from);  
      }
    }

    int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      int first = Integer.parseInt(st.nextToken());
      firstList.add(first);
    }

    // 방문 배열(루머를 믿는 순간 체크)
    visited = new boolean[N + 1];
    // 현재 본인의 주변인이 몇명이나 루머를 믿는지
    count = new int[N + 1];

    result = new int[N + 1];
    Arrays.fill(result, -1);
  }

  static void pro() {
    Queue<int[]> q = new LinkedList<>();
    for (int first : firstList) {
      visited[first] = true;
      result[first] = 0;
      q.add(new int[]{first, 0});
    }

    while (!q.isEmpty()) {
      int[] cur = q.poll();

      // 루머 퍼뜨리기
      for (int next : graph.get(cur[0])) {
        if (visited[next]) continue;
        count[next]++;
        int size = graph.get(next).size() % 2 == 0 ? graph.get(next).size() / 2 : graph.get(next).size() / 2 + 1;
        if (count[next] >= size) {
          visited[next] = true;
          result[next] = cur[1] + 1;
          q.add(new int[]{next, cur[1] + 1});
        }
      }
    }
    for (int i = 1; i <= N; i++) {
      System.out.println(result[i]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}