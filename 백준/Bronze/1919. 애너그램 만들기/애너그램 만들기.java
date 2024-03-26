import java.util.Scanner;

// 애너그램 만들기
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String a = sc.nextLine();
    String b = sc.nextLine();

    int[] countA = new int[26];
    int[] countB = new int[26];
    
    for (int i = 0; i < a.length(); i++) {
      countA[a.charAt(i) - 'a']++;
    }
    
    for (int i = 0; i < b.length(); i++) {
      countB[b.charAt(i) - 'a']++;
    }
    
    int answer = 0;
    for (int i = 0; i < 26; i++) {
      if (countA[i] > countB[i]) {
        answer += countA[i] - countB[i];
      } else if (countB[i] > countA[i]) {
        answer += countB[i] - countA[i];
      }
    }
    System.out.println(answer);
  }
}