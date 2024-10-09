import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 국영수 실버4
 * 알고리즘 : 정렬
 */
class Main {
  static int N;
  static Student[] S;
  static StringBuilder sb = new StringBuilder();
    
  static class Student implements Comparable<Student> {
    String name;
    int k;
    int e;
    int m;

    public Student(String name, int k, int e, int m) {
      this.name = name;
      this.k = k;
      this.e = e;
      this.m = m;
    }


    @Override
    public int compareTo(Student o) {
      // 1. 국어 점수가 감소하는 순서 - 국어 점수로 내림차순
      // 2. 국어 점수가 같으면 영어 점수가 증가하는 순 - 영어 점수 오름차순
      // 3. 수학 점수 감소하는 순 - 수학 내림차순
      // 4. 사전 순 증가 - 사전 오름차순(대문자가 소문자보다 앞)
      if (this.k != o.k) return o.k - this.k;
      if (this.e != o.e) return this.e - o.e;
      if (this.m != o.m) return o.m - this.m;
      return this.name.compareTo(o.name);
    }
  }

  static void input() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    S = new Student[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      String name = st.nextToken();
      int k = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      S[i] = new Student(name, k, e, m);
    }
  }

  public static void main(String[] args) throws Exception {
    input();

    Arrays.sort(S);

    for (Student s : S) {
      sb.append(s.name).append('\n');
    }
    System.out.println(sb.toString());
  }
}