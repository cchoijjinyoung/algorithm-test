import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 애너그램 만들기
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int answer = 0;

    String str1 = sc.nextLine();
    String str2 = sc.nextLine();

    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < str1.length(); i++) {
      char ch = str1.charAt(i);
      map.put(ch, map.getOrDefault(ch, 0) + 1);
      
    }
    
    for (int i = 0; i < str2.length(); i++) {
      char ch = str2.charAt(i);
      map.put(ch, map.getOrDefault(ch, 0) - 1);
    }

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() != 0) {
        answer += Math.abs(entry.getValue());
      }
    }
    System.out.println(answer);
  }
}