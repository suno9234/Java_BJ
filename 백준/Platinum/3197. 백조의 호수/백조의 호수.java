import java.io.*;
import java.util.*;

public class Main {

	static int r, c, startX, startY, endX, endY;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] _map;
	static boolean [][] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		_map = new int[r][c];
		int cnt = 0;
		for (int i = 0; i < r; i++) {
			String nl = br.readLine();
			for (int j = 0; j < c; j++) {
				char now = nl.charAt(j);
				if (now == 'X') {
					_map[i][j] = -1;
				} else if (now == 'L') {
					if (cnt == 0) {
						startX = i;
						startY = j;
					} else {
						endX = i;
						endY = j;
					}
					cnt++;
				}
			}
		}
		ArrayDeque<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (_map[i][j] == 0) {
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx >= 0 && ny >= 0 && nx < r && ny < c && _map[nx][ny] == -1) {
							_map[nx][ny] = 1;
							q.add(new int[] { nx, ny, 1 });
						}
					}
				}
			}
		}
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int dist = now[2];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < r && ny < c && _map[nx][ny] == -1) {
					_map[nx][ny] = dist + 1;
					q.add(new int[] { nx, ny, dist + 1 });
				}
			}
		}
		int answer = 0;
		int start = 0;
		int end = 3000;
		while (start <= end) {
			int mid = (start + end) / 2;
			v = new boolean[r][c];
			if(dfs(startX,startY,mid)) {
				answer = mid;
				end = mid-1;
			}else {
				start = mid+1;
			}
			
		}
		System.out.println(answer);
	}

	static boolean dfs(int x, int y, int k) {
		v[x][y] = true;
		if (x == endX && y == endY) {
			return true;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < r && ny < c && !v[nx][ny] && _map[nx][ny] <= k) {
				if (dfs(nx, ny, k)) {
					return true;
				}
			}
		}
		return false;
	}

}