
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  static long N;

  static void input() throws IOException {
    Scanner sc = new Scanner(System.in);
    N = sc.nextLong();
  }

  static void pro() {
    if (N % 7 == 0 || N % 7 == 2) {
      System.out.println("CY");
    } else {
      System.out.println("SK");
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}