
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 인내의 도미노 장인 호석
 */
public class Main {
  static int N, M, R;
  static int[][] board;
  static char[][] answer;
  static int[][] attack, defense;
  static int[] dx = {0, 0, 1, -1}; // 동서남북
  static int[] dy = {1, -1, 0, 0};
  static int attackSuccessCount = 0;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    board = new int[N + 1][M + 1];
    answer = new char[N + 1][M + 1];
    attack = new int[R][3];
    defense = new int[R][2];

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < R; i++) {
      st = new StringTokenizer(br.readLine());
      int attackX = Integer.parseInt(st.nextToken());
      int attackY = Integer.parseInt(st.nextToken());
      int dir = parseDirect(st.nextToken());
      attack[i] = new int[]{attackX, attackY, dir};

      st = new StringTokenizer(br.readLine());
      int defenseX = Integer.parseInt(st.nextToken());
      int defenseY = Integer.parseInt(st.nextToken());
      defense[i] = new int[]{defenseX, defenseY};
    }
  }

  static int parseDirect(String s) {
    if (s.equals("E")) return 0;
    else if (s.equals("W")) return 1;
    else if (s.equals("S")) return 2;
    else return 3;
  }

  static void initialize() {
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        answer[i][j] = 'S';
      }
    }
  }

  static void pro() {
    // answer 배열 'S'로 초기화
    initialize();

    // 라운드 진행
    for (int i = 0; i < R; i++) {
      int x = attack[i][0];
      int y = attack[i][1];
      int dir = attack[i][2];

      // 이미 넘어진 블록은 아무 일도 일어나지 않는다.
      if (answer[x][y] == 'F') {
        answer[defense[i][0]][defense[i][1]] = 'S';
        continue;
      }

      // 넘어진 블록이 아니라면,
      attackSuccessCount++;
      answer[x][y] = 'F';
      int nx = x;
      int ny = y;
      int count = board[x][y] - 1;
      // 도미노 높이 만큼 이동해가며 넘어뜨린다.
      for (int j = 1; j <= count; j++) {
        nx = nx + dx[dir];
        ny = ny + dy[dir];

        // 다음 위치가 밖이면,
        if (nx < 1 || ny < 1 || nx > N || ny > M) {
          continue;
        }

        // 다음 도미노가 넘어져있으면,
        if (answer[nx][ny] == 'F') continue;

        // 넘어져있지 않으면,
        attackSuccessCount++;
        answer[nx][ny] = 'F';

        // 가야할 거리 업데이트
        int newHeight = board[nx][ny];
        if (count - j < newHeight - 1) {
          j = 0;
          count = newHeight - 1;
        }
      }

      // 수비
      answer[defense[i][0]][defense[i][1]] = 'S';
    }

    StringBuilder sb = new StringBuilder();
    sb.append(attackSuccessCount).append('\n');
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        sb.append(answer[i][j]).append(' ');
      }
      sb.append('\n');
    }
    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}