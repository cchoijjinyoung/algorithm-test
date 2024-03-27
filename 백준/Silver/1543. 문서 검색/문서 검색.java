import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(br.readLine());
        String find = br.readLine();

        while (sb.length() >= find.length()) {
            int idx = sb.indexOf(find);
            if (idx != -1) {
                sb.replace(0, idx + find.length(), "");
                // System.out.println(sb.toString());
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
    }
}