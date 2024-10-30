
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

  static void bfs(int x, int y) {
    Queue<Word> q = new LinkedList<>();
    q.add(new Word(x, y, String.valueOf(board[x][y])));

    while (!q.isEmpty()) {
      Word word = q.poll();
      int cx = word.x;
      int cy = word.y;
      String cs = word.s;

      countMap.put(cs, countMap.getOrDefault(cs, 0) + 1);

      if (cs.length() == 5) {
        continue;
      }

      for (int i = 0; i < 8; i++) {
        int nx = (cx + dx[i] + N) % N;
        int ny = (cy + dy[i] + M) % M;

        q.add(new Word(nx, ny, cs + board[nx][ny]));
      }
    }
  }

  static void pro() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        bfs(i, j);
      }
    }

    for (int i = 0; i < A.length; i++) {
      if (countMap.containsKey(A[i])) {
        System.out.println(countMap.get(A[i]));
      } else {
        System.out.println(0);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Word {
  int x;
  int y;
  String s;

  Word(int x, int y, String s) {
    this.x = x;
    this.y = y;
    this.s = s;
  }
}