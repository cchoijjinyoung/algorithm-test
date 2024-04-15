import java.util.Scanner;

// 소금 폭탄
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s1 = sc.nextLine();
    String[] now = s1.split(":");
    int hour1 = Integer.parseInt(now[0]);
    int minute1 = Integer.parseInt(now[1]);
    int second1 = Integer.parseInt(now[2]);

    String s2 = sc.nextLine();
    String[] time = s2.split(":");
    int hour2 = Integer.parseInt(time[0]);
    int minute2 = Integer.parseInt(time[1]);
    int second2 = Integer.parseInt(time[2]);

    int current = hour1 * 3600 + minute1 * 60 + second1;
    int drop = hour2 * 3600 + minute2 * 60 + second2;

    int result = drop - current;
    if (result <= 0) {
      result += 24 * 3600;
    }

    int resultHour = result / 3600;
    int resultMinute = (result % 3600) / 60;
    int resultSecond = result % 60;

    System.out.printf("%02d:%02d:%02d", resultHour, resultMinute, resultSecond);
  }
}