
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  static int[][] board;
  static boolean[][] row;
  static boolean[][] col;
  static boolean[][] square;
  static List<int[]> blanks = new ArrayList<>();
  static boolean solved = false;
  static void input() throws IOException {
    board = new int[9][9];
    row = new boolean[9][10];
    col = new boolean[9][10];
    square = new boolean[9][10];
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < 9; i++) {
      String line = br.readLine();
      for (int j = 0; j < 9; j++) {
        int num = line.charAt(j) - '0';
        if (num != 0) {
          board[i][j] = num;
          row[i][num] = true;
          col[j][num] = true;
          square[(i / 3) * 3 + j / 3][num] = true;
        } else {
          blanks.add(new int[]{i, j});
        }
      }
    }
  }

  static void pro() {
    dfs(0);
  }

  static void dfs(int idx) {
    if (solved) return; // 이미 정답 찾았으면 더 이상 탐색 X

    if (idx == blanks.size()) {
      printBoard();
      solved = true;
      return;
    }

    int[] pos = blanks.get(idx);
    int x = pos[0], y = pos[1];

    for (int num = 1; num <= 9; num++) {
      int squareIdx = getSquareIndex(x, y);
      if (row[x][num] || col[y][num] || square[squareIdx][num]) continue;

      // 값 선택
      board[x][y] = num;
      row[x][num] = true;
      col[y][num] = true;
      square[squareIdx][num] = true;

      // 다음 칸 탐색
      dfs(idx + 1);

      // 복구 (백트래킹)
      board[x][y] = 0;
      row[x][num] = false;
      col[y][num] = false;
      square[squareIdx][num] = false;
    }
  }

  static int getSquareIndex(int x, int y) {
    return (x / 3) * 3 + (y / 3);
  }

  static void printBoard() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        sb.append(board[i][j]);
      }
      sb.append("\n");
    }
    System.out.print(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}