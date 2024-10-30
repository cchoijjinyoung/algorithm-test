
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문자열 지옥에 빠진 호석, 골드4
 */
public class Main {
  static int N, M, K;
  static char[][] board;
  static String[] A;
  static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
  static Map<String, Integer> countMap;
  static Map<Character, List<int[]>> map;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    board = new char[N][M];
    A = new String[K];
    countMap = new HashMap<>();
    map = new HashMap<>();
    for (char c = 'a'; c <= 'z'; c++) {
      map.put(c, new ArrayList<>());
    }

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        char alpha = line.charAt(j);
        board[i][j] = alpha;
        map.get(alpha).add(new int[]{i, j});
      }
    }

    for (int i = 0; i < A.length; i++) {
      A[i] = br.readLine();
    }
  }

  static int bfs(String s, int len, int x, int y) {
    int result = 0;

    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{x, y, 1}); // 1은 현재까지 채운 알파벳

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int cx = cur[0];
      int cy = cur[1];
      int depth = cur[2]; // 현재 몇글자까지 채웠는가

      if (depth == len) {
        result++;
        countMap.put(s, result);
        continue;
      }

      for (int i = 0; i < 8; i++) {
        int nx = (cx + dx[i] + N) % N;
        int ny = (cy + dy[i] + M) % M;

        if (board[nx][ny] != s.charAt(depth)) {
          continue;
        }

        q.add(new int[]{nx, ny, depth + 1});
      }
    }
    return result;
  }

  static void pro(String s) {
    if (countMap.containsKey(s)) {
      System.out.println(countMap.get(s));
      return;
    }
    
    int answer = 0;
    // len 만큼만 이동할 수 있음(탈출 조건)
    int len = s.length();

    char first = s.charAt(0);

    for (int[] point : map.get(first)) {
      answer += bfs(s, len, point[0], point[1]);
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();

    for (int i = 0; i < A.length; i++) {
      pro(A[i]);
    }
  }
}