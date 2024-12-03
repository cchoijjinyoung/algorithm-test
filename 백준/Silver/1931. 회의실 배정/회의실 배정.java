
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static Meeting[] meetings;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    meetings = new Meeting[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      meetings[i] = new Meeting(start, end);
    }
  }

  static void pro() {
    int answer = 1;
    Arrays.sort(meetings, (m1, m2) -> m1.end == m2.end ? m1.start - m2.start : m1.end - m2.end);

    Meeting first = meetings[0];
    int last_end = first.end;
    for (int i = 1; i < N; i++) {
      if (meetings[i].start < last_end) {
        continue;
      }
      answer++;
      last_end = meetings[i].end;
    }

    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Meeting {
  int start;
  int end;

  Meeting(int start, int end) {
    this.start = start;
    this.end = end;
  }
}