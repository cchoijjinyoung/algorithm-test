
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] board;
  static int[][] queries;
  static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
  static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
  static List<Cloud> clouds;
  static Set<Cloud> cloudSet;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int amount = Integer.parseInt(st.nextToken());
        board[i][j] = amount;
      }
    }

    queries = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken()) - 1;
      int s = Integer.parseInt(st.nextToken());
      queries[i] = new int[]{d, s};
    }

    clouds = new ArrayList<>();
    clouds.add(new Cloud(N - 1 ,0));
    clouds.add(new Cloud(N - 1 ,1));
    clouds.add(new Cloud(N - 2 ,0));
    clouds.add(new Cloud(N - 2 ,1));
  }

  static void pro() {
    // 쿼리 반복
    for (int[] query : queries) {
      int d = query[0];
      int s = query[1];
      // 구름 이동
      move(d, s % N);
      // 이동 후 위치에서 물의 양 + 1
      rain();
      // 물 복사 버그
      waterCopyBug();
      // 새로운 구름 생성하기
      createNextCloudSet();
    }
    // 전체 물의 양 출력
    print();
  }

  static void print() {
    int result = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        result += board[i][j];
      }
    }
    System.out.println(result);
  }

  static void createNextCloudSet() {
    clouds = new ArrayList<>();
    // board 를 순회하면서 물의 양이 2 이상이고, cloudSet 에 포함하지 않는 곳만 새로운 구름 리스트에 추가
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (board[i][j] >= 2) {
          Cloud cloud = new Cloud(i, j);
          if (cloudSet.contains(cloud)) {
            continue;
          }
          clouds.add(cloud);
          board[i][j] -= 2;
        }
      }
    }
  }

  static void waterCopyBug() {
    for (Cloud cloud : cloudSet) {
      // 대각선 확인 d_idx (1, 3, 5, 7)
      int cx = cloud.x;
      int cy = cloud.y;

      int count = 0;
      for (int d = 1; d < 8; d += 2) {
        int nx = cx + dx[d];
        int ny = cy + dy[d];

        if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
          continue;
        }

        // 대각선에 물이 있으면
        if (board[nx][ny] > 0) {
          count++;
        }
      }
      board[cx][cy] += count;
    }
  }

  static void rain() {
    for (Cloud cloud : cloudSet) {
      board[cloud.x][cloud.y]++;
    }
  }

  static void move(int d, int s) {
    cloudSet = new HashSet<>();
    for (Cloud cloud : clouds) {
      int cx = cloud.x;
      int cy = cloud.y;

      int nx = (cx + (dx[d] * s) + N) % N;
      int ny = (cy + (dy[d] * s) + N) % N;
      cloudSet.add(new Cloud(nx, ny));
    }
  }

  static class Cloud {
    int x;
    int y;

    Cloud(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      Cloud cloud = (Cloud) o;
      return x == cloud.x && y == cloud.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}