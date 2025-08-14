
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] board;
  static int[] shark;
  static int sharkSize = 2;
  static int result = 0;
  static int exp; // 경험치
  static int[] dx = {-1, 0, 0, 1}; // 상 좌 우 하
  static int[] dy = {0, -1, 1, 0};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    board = new int[N][N];
    StringTokenizer st;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int num = Integer.parseInt(st.nextToken());
        board[i][j] = num;
        if (num == 9) {
          shark = new int[]{i, j};
          board[i][j] = 0;
        }
      }
    }
  }

  static void pro() {
    // bfs로 (상, 좌, 우, 하) 순이 좋을 듯
    // 총 400칸 = 물고기를 먹을 때마다 전체 순회 400 * 400 = 충분
    // 최적화 방법(고려): 매번 순회를 하는 것이 아닌, 현재 위치에서 상어 크기만큼의 물고기 개수를 찾는다.

    // 상어 위치에서 bfs를 돌려서, 현재 먹을 수 있는 물고기를 만나면 바로 거기로 간다.(상좌우하) eatCount++;
    // 만약 q가 비어서 while 문을 나가게 되면 eatCount 출력 후  return
    while (true) {
      if (bfs()) {
        System.out.println(result);
        return;
      }
    }
  }

  static boolean bfs() {
    boolean flag = false;

    PriorityQueue<int[]> q = new PriorityQueue<>((e1, e2) -> {
      int dist1 = e1[2];
      int dist2 = e2[2];
      int x1 = e1[0];
      int x2 = e2[0];
      int y1 = e1[1];
      int y2 = e2[1];
      if (dist1 == dist2) {
        if (x1 == x2) {
          return y1 - y2;
        }
        return x1 - x2;
      }
      return dist1 - dist2;
    });

    boolean[][] visited = new boolean[N][N];
    // 상어 위치에서 시작
    q.add(new int[]{shark[0], shark[1], 0});
    visited[shark[0]][shark[1]] = true;

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int cx = cur[0];
      int cy = cur[1];

      if (board[cx][cy] != 0 && board[cx][cy] < sharkSize) {
        board[cx][cy] = 0;
        result += cur[2];
        exp++;
        if (exp == sharkSize) {
          sharkSize++;
          exp = 0;
        }
        shark[0] = cx;
        shark[1] = cy;
        return flag;
      }

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        if (board[nx][ny] > sharkSize) {
          continue;
        }

        q.add(new int[]{nx, ny, cur[2] + 1});
        visited[nx][ny] = true;
      }
    }
    flag = true;
    return flag;
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}