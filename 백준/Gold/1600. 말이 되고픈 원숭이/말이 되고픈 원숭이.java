import java.io.*;
import java.util.*;

public class Main{
	static int answer = Integer.MAX_VALUE, k, w, h;
	static int[][] _map;
	static int[][][] dist;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[] dxH = { 2, 2, -2, -2, 1, -1, 1, -1 };
	static int[] dyH = { 1, -1, 1, -1, 2, -2, -2, 2 };
	static boolean isEnd;
	static boolean[][][] v;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();

		k = Integer.parseInt(br.readLine());
		tokens = br.readLine().split(" ");
		w = Integer.parseInt(tokens[0]);
		h = Integer.parseInt(tokens[1]);
		v = new boolean[h][w][31];
		dist = new int[h][w][31];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				for (int l = 0; l <= k; l++) {
					dist[i][j][l] = Integer.MAX_VALUE;
				}
			}
		}
		_map = new int[h][w];

		for (int i = 0; i < h; i++) {
			tokens = br.readLine().split(" ");
			for (int j = 0; j < w; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
			}
		}

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		v[0][0][0] = true; // 능력을 cnt번만큼 쓰고 x,y에 방문했는가?
		queue.add(new int[] { 0, 0, 0, 0 });
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			int cnt = now[2];
			int _dist = now[3];
			dist[x][y][cnt] = _dist;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < h && ny < w && !v[nx][ny][cnt] && _map[nx][ny] == 0) {
					queue.add(new int[] { nx, ny, cnt, _dist + 1 });
					v[nx][ny][cnt] = true;
				}
			}
			for (int i = 0; i < 8; i++) {
				int nx = x + dxH[i];
				int ny = y + dyH[i];
				if (nx >= 0 && ny >= 0 && nx < h && ny < w && cnt + 1 <= k && !v[nx][ny][cnt + 1]
						&& _map[nx][ny] == 0) {
					queue.add(new int[] { nx, ny, cnt + 1, _dist + 1 });
					v[nx][ny][cnt + 1] = true;
				}
			}

		}
		// System.out.println(Arrays.toString(dist[h-1][w-1]));
		for (int i = 0; i <= k; i++) {
			answer = Math.min(answer, dist[h - 1][w - 1][i]);
		}

		// dfs(0,0,0,k);
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
		br.close();
	}
//	static void dfs(int x , int y,int cnt, int k) {
//		if(cnt >= answer) {
//			return;
//		}
//		v[x][y] = true;
//		if(k < 0 ) {
//			return;
//		}
//		if(x == h-1 && y == w-1) {
//			answer = Math.min(answer, cnt);
//			isEnd = true;
//			return;
//		}
//		for(int i = 0 ; i < 8 ; i++) {
//			int nx = x+dxH[i];
//			int ny = y+dyH[i];
//			if(nx>=0 && ny >=0 && nx < h && ny < w &&!v[nx][ny] && k > 0 && _map[nx][ny] == 0) {
//				dfs(nx,ny,cnt+1,k-1);
//				v[nx][ny] = false;
//			}
//		}
//		for(int i = 0 ; i < 4 ; i++) {
//			int nx = x+dx[i];
//			int ny = y+dy[i];
//			if(nx>=0 && ny >=0 && nx < h && ny < w && !v[nx][ny] && _map[nx][ny] == 0) {
//				dfs(nx,ny,cnt+1,k);
//				v[nx][ny] = false;
//			}
//		}
//	}
}
