import java.io.*;
import java.util.*;

public class Main{
	static int n, answer, mid;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][][] v;
	static int[][] _map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		v = new boolean[n][n][n * 2];
		_map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String nl = br.readLine();
			for (int j = 0; j < n; j++) {
				_map[i][j] = nl.charAt(j) - '0';
			}
		}
		for (int i = 0; i < n * 2; i++) {
			dfs(0, 0,i, i);
		}
		System.out.println(answer);
		br.close();
	}

	static void dfs(int x, int y, int startCnt,int cnt) {
		boolean result = false;
		v[x][y][cnt] = true;
		if(x == n-1 && y == n-1) {
			System.out.println(startCnt);
			System.exit(0);
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < n && ny < n && !v[nx][ny][cnt]) {
				if (_map[nx][ny] == 1) {
					// 흰방이면
					if (v[nx][ny][cnt]) {
						// 이미 갈 수 있으면
						result = true;
					} else {
						dfs(nx, ny, startCnt,cnt);
					}
				} else {
					// 아직 방문하지 않은 검은방
					if (v[nx][ny][cnt]) {
						result = true;
					} else {
						if (cnt > 0) {
							dfs(nx, ny, startCnt,cnt - 1);
						}
					}
				}
			}
			if (result) {
				v[x][y][cnt] = true;
			}
		}
	}
}
