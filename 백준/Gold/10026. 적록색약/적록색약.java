import java.util.*;
import java.io.*;

public class Main{

	static int n;
	static char[][] _map;
	static int[][] group;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		_map = new char[n][n];
		group = new int[n][n];
		for (int i = 0; i < n; i++) {
			String nl = br.readLine();
			for (int j = 0; j < n; j++) {
				_map[i][j] = nl.charAt(j);
			}
		}
		int idx = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (group[i][j] == 0) {
					dfs(i, j, idx++, _map[i][j]);
				}
			}
		}

		sb.append(idx-1).append(" ");

		idx = 1;
		group = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (group[i][j] == 0) {
					dfsRG(i, j, idx++, _map[i][j]);
				}
			}
		}
		sb.append(idx-1);
		System.out.println(sb.toString());
	}

	static void dfs(int x, int y, int idx, char color) {
		group[x][y] = idx;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < n && ny < n && _map[nx][ny] == color && group[nx][ny] == 0) {
				dfs(nx, ny, idx, color);
			}
		}
	}

	static void dfsRG(int x, int y, int idx, char color) {
		group[x][y] = idx;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < n && ny < n && group[nx][ny] == 0) {
				if (color == 'B') {
					if (_map[nx][ny] == 'B') {
						dfsRG(nx, ny, idx, color);
					}
				} else if(_map[nx][ny]!= 'B'){
					dfsRG(nx,ny,idx,color);
				}

			}
		}
	}

}
