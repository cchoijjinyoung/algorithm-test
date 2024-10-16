import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 탈출, 골드4
 * 알고리즘 : 그래프 탐색
 * 내 아이디어 : 멀티소스 BFS
 */
class Main {
  static int R;
  static int C;
  static int W;
  static int[] D;
  static int[] S;
  static int[][] water;
  static char[][] A;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    W = 0;
    A = new char[R][C];
    water = new int[R * C + 1][2];

    for (int i = 0; i < R; i++) {
      String s = br.readLine();
      for (int j = 0; j < C; j++) {
        A[i][j] = s.charAt(j);

        if (A[i][j] == 'D') {
          D = new int[]{i, j};
        } else if (A[i][j] == 'S') {
          S = new int[]{i, j};
        } else if (A[i][j] == '*') {
          water[W][0] = i;
          water[W][1] = j;
          W++;
        }
      }
    }
  }

  static int bfs() {
    boolean[][] visited = new boolean[R][C];

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < W; i++) {
      int x = water[i][0];
      int y = water[i][1];
      q.add(x);
      q.add(y);
      q.add(0);
      q.add(1); // 물이냐
    }

    q.add(S[0]);
    q.add(S[1]);
    q.add(0);
    q.add(0); // 고슴도치냐

    while (!q.isEmpty()) {
      int x = q.poll();
      int y = q.poll();
      int count = q.poll();
      int type = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 0 || ny < 0 || nx > R - 1 || ny > C - 1) {
          continue;
        }

        if (type == 0) { // 고슴도치
          if (A[nx][ny] == 'X' || A[nx][ny] == '*') {
            continue;
          }
        } else {
          if (A[nx][ny] == 'X' || A[nx][ny] == 'D') {
            continue;
          }
        }

        if (visited[nx][ny]) {
          continue;
        }

        if (type == 0) { // 고슴도치
          if (nx == D[0] && ny == D[1]) {
            return count + 1;
          }
        }

        visited[nx][ny] = true;
        q.add(nx);
        q.add(ny);
        q.add(count + 1);
        q.add(type);
      }
    }
    return 0;
  }

  static void pro() {
    int success = bfs();
    if (success == 0) {
      System.out.println("KAKTUS");
    } else {
      System.out.println(success);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}