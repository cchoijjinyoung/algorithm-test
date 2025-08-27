
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator.OfInt;
import java.util.StringTokenizer;

public class Main {
  static int N, M, K;
  static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
  static Queue<int[]> q = new LinkedList<>();
  static Map<Point, List<Fireball>> map;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      q.add(new int[]{r, c, m, s, d});
    }
  }

  static void pro() {
    for (int i = 0; i < K; i++) {
      // 파이어볼 움직이기
      map = new HashMap<>();
      move();
      // 파이어볼 합치기
      q = new LinkedList<>();
      union();
    }

    int result = 0;
    while (!q.isEmpty()) {
      int m = q.poll()[2];
      result += m;
    }

    System.out.println(result);
  }

  static void union() {
    for (Map.Entry<Point, List<Fireball>> entry : map.entrySet()) {
      Point point = entry.getKey();
      List<Fireball> fireballList = entry.getValue();

      // 파이어볼이 현재 위치에 2개 이상인 경우
      if (fireballList.size() >= 2) {
        int mSum = 0;
        int sSum = 0;
        boolean isExistOdd = false;
        boolean isExistEven = false;
        for (Fireball fireball : fireballList) {
          mSum += fireball.m;
          sSum += fireball.s;
          if (fireball.d % 2 == 0) {
            isExistEven = true;
          } else {
            isExistOdd = true;
          }
        }
        int newM = mSum / 5;
        int newS = sSum / fireballList.size();

        if (newM == 0) {
          continue;
        }

        if (isExistOdd && isExistEven) {
          q.add(new int[]{point.x, point.y, newM, newS, 1});
          q.add(new int[]{point.x, point.y, newM, newS, 3});
          q.add(new int[]{point.x, point.y, newM, newS, 5});
          q.add(new int[]{point.x, point.y, newM, newS, 7});
        } else {
          q.add(new int[]{point.x, point.y, newM, newS, 0});
          q.add(new int[]{point.x, point.y, newM, newS, 2});
          q.add(new int[]{point.x, point.y, newM, newS, 4});
          q.add(new int[]{point.x, point.y, newM, newS, 6});
        }
      }
      // 파이어볼이 현재 위치에 한 개인 경우
      else if (fireballList.size() == 1) {
        Fireball fireball = fireballList.get(0);
        q.add(new int[]{point.x, point.y, fireball.m, fireball.s, fireball.d});
      }
    }
  }

  static void move() {
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int r = cur[0];
      int c = cur[1];
      int m = cur[2];
      int s = cur[3];
      int d = cur[4];

      int nr = (r + dx[d] * s) % N;
      int nc = (c + dy[d] * s) % N;
      if (nr < 0) nr += N;
      if (nc < 0) nc += N;
      Point point = new Point(nr, nc);
      Fireball fireball = new Fireball(m, s, d);
      List<Fireball> fireballList = map.getOrDefault(point, new ArrayList<Fireball>());
      fireballList.add(fireball);
      map.put(point, fireballList);
    }
  }

  static class Fireball {
    int m;
    int s;
    int d;

    Fireball(int m, int s, int d) {
      this.m = m;
      this.s = s;
      this.d = d;
    }

    @Override
    public boolean equals(Object o) {
      Fireball fireball = (Fireball) o;
      return m == fireball.m && s == fireball.s && d == fireball.d;
    }

    @Override
    public int hashCode() {
      return Objects.hash(m, s, d);
    }
  }

  static class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      Point point = (Point) o;
      return x == point.x && y == point.y;
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