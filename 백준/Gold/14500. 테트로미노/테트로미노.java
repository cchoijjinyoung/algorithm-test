
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
  static List<Poly> initPolys;
  static Set<Poly> polys;
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
    initPolys.add(new Poly(new int[][]{
        {1, 1, 1, 1}
    }));

    initPolys.add(new Poly(new int[][]{
        {1, 1},
        {1, 1}
    }));

    initPolys.add(new Poly(new int[][]{
        {1, 0},
        {1, 0},
        {1, 1}
    }));

    initPolys.add(new Poly(new int[][]{
        {1, 0},
        {1, 1},
        {0, 1}
    }));

    initPolys.add(new Poly(new int[][]{
        {1, 1, 1},
        {0, 1, 0}
    }));
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
    // 초기 5개의 도형 초기화
    initializePoly();

    // 초기 도형 5개로 만들 수 있는 모든 도형(대칭 + 회전) 만들기
    for (Poly p : initPolys) {
      int[][] poly = p.poly;
      for (int i = 0; i < 2; i++) {
        poly = reverse(poly);
        polys.add(new Poly(poly));
        for (int j = 0; j < 4; j++) {
          poly = turn(poly);
          polys.add(new Poly(poly));
        }
      }
    }

    // 모든 도형을 보드에 매칭시켜서 최대값을 찾는다.
    for (Poly p : polys) {
      int[][] poly = p.poly;
      int poly_x = poly.length;
      int poly_y = poly[0].length;

      for (int x = 0; x < N; x++) {
        for (int y = 0; y < M; y++) {
          int sum = 0;

          // 현재 도형이 아래 밖으로 나가는 경우
          if (x + poly_x > N) {
            continue;
          }
          // 오른쪽 밖으로 나가는 경우
          if (y + poly_y > M) {
            continue;
          }

          // 도형이 격자와 닿는 부분 sum++
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

class Poly {
  int[][] poly;

  Poly(int[][] poly) {
    this.poly = poly;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Poly poly1 = (Poly) o;
    return Arrays.deepEquals(poly, poly1.poly);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(poly);
  }
}