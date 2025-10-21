
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int h, w;
  static char[][] map;
  static boolean[][] visited;
  static boolean[] hasKey;
  static Queue<int[]> q;
  static Queue<int[]>[] doorList;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      h = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());

      map = new char[h + 2][w + 2];
      visited = new boolean[h + 2][w + 2];
      hasKey = new boolean[26];
      doorList = new LinkedList[26];
      for (int i = 0; i < 26; i++) {
        doorList[i] = new LinkedList<>();
      }
      
      for (int i = 0; i < h + 2; i++) {
        Arrays.fill(map[i], '.');
      }
      
      for (int i = 1; i <= h; i++) {
        String line = br.readLine();
        for (int j = 1; j <= w; j++) {
          map[i][j] = line.charAt(j - 1);
        }
      }
      
      String keys = br.readLine();
      if (!keys.equals("0")) {
        for (char c : keys.toCharArray()) {
          hasKey[c - 'a'] = true;
        }
      }
      sb.append(bfs()).append('\n');
    }
    System.out.println(sb);
  }

  static int bfs() {
    q = new LinkedList<>();
    q.add(new int[]{0, 0});
    visited[0][0] = true;
    int result = 0;

    while (!q.isEmpty()) {
      int[] cur = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cur[0] + dx[i];
        int ny = cur[1] + dy[i];

        if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        char c = map[nx][ny];
        if (c == '*') {
          continue;
        }
        visited[nx][ny] = true;

        // 문
        if (Character.isUpperCase(c)) {
          if (hasKey[c - 'A']) {
            q.add(new int[]{nx, ny});
          } else {
            doorList[c - 'A'].add(new int[]{nx, ny});
          }
          // 열쇠
        } else if (Character.isLowerCase(c)) {
          int key = c - 'a';
          if (!hasKey[key]) {
            hasKey[key] = true;

            while (!doorList[key].isEmpty()) {
              q.add(doorList[key].poll());
            }
          }
          q.add(new int[]{nx, ny});
          // 문서
        } else if (c == '$') {
          result++;
          q.add(new int[]{nx, ny});
          // 엠티
        } else {
          q.add(new int[]{nx, ny});
        }
      }
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}