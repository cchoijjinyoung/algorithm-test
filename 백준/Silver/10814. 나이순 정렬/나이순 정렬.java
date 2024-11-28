
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 나이 순 정렬 : 실버5
 */
public class Main {
  static int N;
  static Member[] members;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    members = new Member[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int age = Integer.parseInt(st.nextToken());
      String name = st.nextToken();
      members[i] = new Member(age, name);
    }
  }

  static void pro() {
    Arrays.sort(members, (m1, m2) -> m1.age - m2.age);

    for (Member member : members) {
      System.out.println(member.age + " " + member.name);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Member {
  int age;
  String name;

  Member(int age, String name) {
    this.age = age;
    this.name = name;
  }
}