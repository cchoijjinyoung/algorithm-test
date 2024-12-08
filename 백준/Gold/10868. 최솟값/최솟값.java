
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 최솟값 : 골드1
 * 세그먼트 트리
 * 1. 트리 크기 초기화
 * 2. 독립노드 선택
 */
public class Main {
  static int N, M;
  static int k, original_start, size;
  static int[] tree;
  static int[][] queries;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    while (!(Math.pow(2, k) >= N)) {
      k++;
    }
    original_start = (int) Math.pow(2, k);
    size = original_start * 2;
    tree = new int[size];
    Arrays.fill(tree, Integer.MAX_VALUE);

    for (int i = 0; i < N; i++) {
      tree[original_start + i] = Integer.parseInt(br.readLine());
    }

    queries = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      queries[i] = new int[]{start, end};
    }

    // 업데이트
    for (int i = original_start; i < original_start + N; i++) {
      int parent_idx = i / 2;
      while (true) {
        if (tree[parent_idx] > tree[i]) {
          tree[parent_idx] = tree[i];
        } else {
          break;
        }
        parent_idx /= 2;
      }
    }
  }

  static void min_search(int start, int end) {
    List<Integer> selected_idx = new ArrayList<>();
    // 트리에 맞게끔 idx 변형
    int start_idx = start + original_start - 1;
    int end_idx = end + original_start - 1;

    // 독립 노드(start_idx, end_idx) 선택
    while (start_idx <= end_idx) {
      if (start_idx % 2 == 1) {
        selected_idx.add(start_idx);
      }
      if (end_idx % 2 == 0) {
        selected_idx.add(end_idx);
      }
      start_idx = (start_idx + 1) / 2;
      end_idx = (end_idx - 1) / 2;
    }
    int min = Integer.MAX_VALUE;
    for (int idx : selected_idx) {
      min = Math.min(min, tree[idx]);
    }
     System.out.println(min);
  }

  static void pro() {
    for (int[] query : queries) {
      int start = query[0];
      int end = query[1];
      min_search(start, end);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}