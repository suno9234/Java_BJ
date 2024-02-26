import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] minDist;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] _map;
	static boolean[][] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}
			_map = new int[n][n];
			for (int i = 0; i < n; i++) {
				tokens = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					_map[i][j] = Integer.parseInt(tokens[j]);
				}
			}
			v = new boolean[n][n];
			minDist = new int[n * n];
			Arrays.fill(minDist, Integer.MAX_VALUE);
			minDist[0] = _map[0][0];
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]);
			int answer = 0;
			pq.add(new int[] { 0, 0, _map[0][0] });
			int cnt = 0;
			while(!pq.isEmpty()) {
				int [] now = pq.poll();
				int x = now[0];
				int y = now[1];
				if(v[x][y]) continue;
				v[x][y] = true;
				int cost = now[2];
				if(x == n-1 && y == n-1) {
					break;
				}
				for (int j = 0; j < 4; j++) {
					int nx = x+dx[j];
					int ny = y+dy[j];
					if(nx>=0 && ny >= 0 && nx < n && ny < n && !v[nx][ny] &&minDist[nx*n+ny] > minDist[x*n+y]+_map[nx][ny] ) {
						minDist[nx*n+ny] = minDist[x*n+y]+_map[nx][ny];
						pq.add(new int[] {nx,ny,minDist[x*n+y],minDist[nx*n+ny]});
					}
				}

			}
			answer = minDist[n*n-1];
			sb.append("Problem ").append(tc).append(": ").append(answer).append("\n");
			tc++;
		}
		System.out.println(sb.toString());
		br.close();
	}
}
