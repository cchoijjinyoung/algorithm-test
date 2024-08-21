import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int K;
  static int L;
  static char[][] board;
  static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
  static int[] dy = {1, 0, -1, 0};
  static int second = 0;
  static int direct = 0; // 우측 방향으로 시작

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    board = new char[N][N];
    for (int i = 0; i < N; i++) {
      Arrays.fill(board[i], '-');
    }

    K = Integer.parseInt(br.readLine());
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      board[row][col] = 'A'; // apple
    }

    L = Integer.parseInt(br.readLine());
    Queue<DirectTransferNotification> notiQ = new LinkedList<>();
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int afterSecond = Integer.parseInt(st.nextToken());
      String nextDirectStr = st.nextToken();
      int nextDirect;
      if ("D".equals(nextDirectStr)) {
        nextDirect = 1;
      } else {
        nextDirect = -1;
      }

      notiQ.offer(new DirectTransferNotification(afterSecond, nextDirect));
    }

    DirectTransferNotification nextNoti = notiQ.poll();
    int nextTransferTime = nextNoti.afterSecond;
    int nextDirect = nextNoti.nextDirect;

    Queue<int[]> q = new LinkedList<>(); // 꼬리 위치를 저장할 collection
    int[] cur = {0, 0};
    q.offer(new int[]{0, 0});
    board[0][0] = 'V'; // visited;

    while (true) {
      second++;
      int nx = cur[0] + dx[direct];
      int ny = cur[1] + dy[direct];

      if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) {
        break;
      }

      if (board[nx][ny] == 'V') {
        break;
      }

      // 다음 위치가 사과라면,
      if (board[nx][ny] == 'A') {
        q.offer(new int[]{nx, ny});
      } else { // 다음 위치가 사과가 아니라면,
        q.offer(new int[]{nx, ny});
        if (!q.isEmpty()) {
          int[] tailPoint = q.poll();
          board[tailPoint[0]][tailPoint[1]] = '-';
        }
      }

      cur[0] = nx;
      cur[1] = ny;
      board[nx][ny] = 'V';

      if (second == nextTransferTime) {
        direct = (direct + nextDirect + 4) % 4;
        if (!notiQ.isEmpty()) {
          nextNoti = notiQ.poll();
          nextTransferTime = nextNoti.afterSecond;
          nextDirect = nextNoti.nextDirect;
        }
      }
    }
    System.out.println(second);
  }
}

class DirectTransferNotification {

  int afterSecond;
  int nextDirect;

  DirectTransferNotification(int afterSecond, int nextDirect) {
    this.afterSecond = afterSecond;
    this.nextDirect = nextDirect;
  }
}