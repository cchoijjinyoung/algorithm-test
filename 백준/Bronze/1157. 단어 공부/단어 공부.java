import java.util.Scanner;

// 단어 공부
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.next().toUpperCase();

    int[] count = new int[26];

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      count[ch - 'A']++;
    }

    int repeatCount = 0;
    int max = 0;
    int maxIdx = 0;
    for (int i = 0; i < count.length; i++) {
      if (count[i] > max) {
        max = count[i];
        maxIdx = i;
        repeatCount = 0;
      } else if (count[i] == max) {
        repeatCount++;
      }
    }
    if (repeatCount >= 1) {
      System.out.println("?");
    } else {
      System.out.println((char) ('A' + maxIdx));
    }
  }
}