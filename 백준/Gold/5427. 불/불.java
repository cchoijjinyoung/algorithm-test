
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 불 : 골드4
 */
public class Main {
  static int T;
  static int W, H;
  static char[][] board;
  static int[] start;
  static int[][] sanggeun_root;
  static int[][] fire_root;
  static List<int[]> fire_starts;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static final int SANGGEUN = 0;
  static final int FIRE = 1;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    while (T-- > 0) {
      st = new StringTokenizer(br.readLine());
      W = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());

      board = new char[H][W];
      fire_starts = new ArrayList<>();
      for (int i = 0; i < H; i++) {
        String line = br.readLine();
        for (int j = 0; j < W; j++) {
          board[i][j] = line.charAt(j);
          if (board[i][j] == '@') {
            start = new int[]{i, j};
          } else if (board[i][j] == '*') {
            fire_starts.add(new int[]{i, j});
          }
        }
      }
      pro();
    }
  }

  static void bfs(int[][] root, int type) {
    Queue<int[]> q = new LinkedList<>();
    if (type == SANGGEUN) {
      q.add(new int[]{start[0], start[1], 1});
      sanggeun_root[start[0]][start[1]] = 1;
    } else {
      for (int[] fire_start : fire_starts) {
        q.add(new int[]{fire_start[0], fire_start[1], 1});
        fire_root[fire_start[0]][fire_start[1]] = 1;
      }
    }
    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cx = info[0];
      int cy = info[1];
      int move = info[2];
      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || ny < 0 || nx > H - 1 || ny > W - 1) {
          continue;
        }

        if (board[nx][ny] == '#') {
          continue;
        }

        if (type == SANGGEUN && board[nx][ny] == '*') {
          continue;
        }

        if (root[nx][ny] != Integer.MAX_VALUE) {
          continue;
        }
        root[nx][ny] = move + 1;
        q.add(new int[]{nx, ny, move + 1});
      }
    }
  }

  static void pro() {
    sanggeun_root = new int[H][W];
    for (int i = 0; i < H; i++) {
      Arrays.fill(sanggeun_root[i], Integer.MAX_VALUE);
    }
    fire_root = new int[H][W];
    for (int i = 0; i < H; i++) {
      Arrays.fill(fire_root[i], Integer.MAX_VALUE);
    }
    bfs(fire_root, FIRE);
    bfs(sanggeun_root, SANGGEUN);

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        if (i == 0 || j == 0 || i == H - 1 || j == W - 1) {
          if (sanggeun_root[i][j] < fire_root[i][j]) {
            min = Math.min(min, sanggeun_root[i][j]);
          }
        }
      }
    }
    System.out.println(min == Integer.MAX_VALUE ? "IMPOSSIBLE" : min);
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}