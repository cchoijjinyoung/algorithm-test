
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
  static int N;
  static PriorityQueue<Building> pq;
  static TreeMap<Integer, Integer> tm = new TreeMap<>(Collections.reverseOrder());
  static StringBuilder sb = new StringBuilder();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    pq = new PriorityQueue<>((b1, b2) -> {
      if (b1.x == b2.x) {
        return b2.h - b1.h;
      }
      return b1.x - b2.x;
    });

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int L = Integer.parseInt(st.nextToken());
      int H = Integer.parseInt(st.nextToken());
      int R = Integer.parseInt(st.nextToken());
      pq.add(new Building(L, H));
      pq.add(new Building(R, -H));
    }
  }

  static void pro() {
    int maxX = 0, maxH = 0;
    tm.put(0, 1);
    while (!pq.isEmpty()) {
      Building building = pq.poll();
      if (building.h > 0) {
        tm.put(building.h, tm.getOrDefault(building.h, 0) + 1);
      } else {
        int val = tm.get(-building.h);
        if (val == 1) {
          tm.remove(-building.h);
        } else {
          tm.replace(-building.h, val - 1);
        }
      }

      int height = tm.firstKey();
      if (maxX != building.x && maxH != height) {
        sb.append(building.x).append(" ").append(height).append(" ");
        maxX = building.x;
        maxH = height;
      }
    }
    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Building {
  int x;
  int h;
  
  Building(int x, int h) {
    this.x = x;
    this.h = h;
  }
}