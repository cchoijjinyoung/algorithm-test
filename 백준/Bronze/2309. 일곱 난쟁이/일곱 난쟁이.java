import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

// 일곱 난쟁이
public class Main {

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    int[] h = new int[9];
    int sum = 0;

    for (int i = 0; i < 9; i++) {
      h[i] = sc.nextInt();
      sum += h[i];
    }

    int need = sum - 100;

    loop:
    for (int i = 0; i < h.length; i++) {
      for (int j = 1; j < h.length; j++) {
        if (h[i] + h[j] == need) {
          h[i] = -1;
          h[j] = -1;
          break loop;
        }
      }
    }
    Arrays.sort(h);
    for (int i = 0; i < h.length; i++) {
      if (h[i] > 0) {
        System.out.println(h[i]);
      }
    }
  }
}
