import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int cost;
		
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int dX[] = {-1, 1, 0, 0};
	private static int dY[] = {0, 0, -1, 1};
	private static boolean visited[][];
	private static int map[][];
	private static int size[][];
	
	private static final int INF = Integer.MAX_VALUE;
	private static int n, nX, nY;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int cnt = 1;
		while(true) {
			n = Integer.parseInt(bf.readLine());
			if(n == 0) break;
			
			map = new int[n][n];
			visited = new boolean[n][n];
			size = new int[n][n];
			
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j = 0 ; j < n ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					size[i][j] = INF;
				}
			}
			
			BFS(0, 0, map[0][0]);
			bw.write("Problem " + cnt + ": " + size[n - 1][n - 1] + "\n");
			cnt++;
		}
		
		bf.close();
		bw.flush();
		bw.close();
	}
	
	private static void BFS(int x, int y, int roopy) {
		PriorityQueue<Node> Q = new PriorityQueue<>();
		visited[x][y] = true;
		Q.offer(new Node(x, y, roopy));
		while(!Q.isEmpty()) {
			Node node = Q.poll();
			for(int i = 0 ; i < 4 ; i++) {
				nX = node.x + dX[i];
				nY = node.y + dY[i];
				
				if(isValid() && !visited[nX][nY] && size[nX][nY] > (map[nX][nY] + node.cost)) {
					size[nX][nY] = map[nX][nY] + node.cost;
					visited[nX][nY] = true;
					Q.offer(new Node(nX, nY, size[nX][nY]));
				}
			}
		}
	}
	
	private static boolean isValid() {
		return (nX >= 0 && nY >= 0 && nX < n && nY < n);
	}
}