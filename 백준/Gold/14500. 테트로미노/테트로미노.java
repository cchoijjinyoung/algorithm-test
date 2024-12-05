
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 테트로미노 : 골드4
 */
public class Main {
  static int N, M, answer;
  static int[][] board;
  static List<int[][]> initPolys;
  static Set<int[][]> polys;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];

    initPolys = new ArrayList<>();
    polys = new HashSet<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  static void initializePoly() {
    initPolys.add(new int[][]{
        {1, 1, 1, 1}
    });

    initPolys.add(new int[][]{
        {1, 1},
        {1, 1}
    });

    initPolys.add(new int[][]{
        {1, 0},
        {1, 0},
        {1, 1}
    });

    initPolys.add(new int[][]{
        {1, 0},
        {1, 1},
        {0, 1}
    });

    initPolys.add(new int[][]{
        {1, 1, 1},
        {0, 1, 0}
    });
  }

  // 도형 뒤집기(위 - 아래)
  static int[][] reverse(int[][] poly) {
    int x = poly.length;
    int y = poly[0].length;
    int[][] result = new int[x][y];

    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        result[i][j] = poly[x - i - 1][j];
      }
    }
    return result;
  }

  // 도형 회전(시계 방향)
  static int[][] turn(int[][] poly) {
    int x = poly.length;
    int y = poly[0].length;
    int[][] result = new int[y][x];

    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        result[j][i] = poly[x - i - 1][j];
      }
    }
    return result;
  }

  static void pro() {
    // 도형 초기화
    initializePoly();

    // 모든 도형(대칭 + 회전)에 대해 순회
    for (int[][] poly : initPolys) {
      for (int i = 0; i < 2; i++) {
        poly = reverse(poly);
        polys.add(poly);
        for (int j = 0; j < 4; j++) {
          poly = turn(poly);
          polys.add(poly);
        }
      }
    }

    for (int[][] poly : polys) {
      // 현재 도형을 모든 격자에 대입
      int poly_x = poly.length;
      int poly_y = poly[0].length;

      for (int x = 0; x < N; x++) {
        for (int y = 0; y < M; y++) {
          int sum = 0;

          // 현재 도형이 오른쪽 혹은 아래 밖으로 나가는 경우
          if (x + poly_x > N) {
            continue;
          }

          if (y + poly_y > M) {
            continue;
          }

          for (int xx = 0; xx < poly_x; xx++) {
            for (int yy = 0; yy < poly_y; yy++) {
              if (poly[xx][yy] == 1) {
                sum += board[x + xx][y + yy];
              }
            }
          }
          answer = Math.max(answer, sum);
        }
      }
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}