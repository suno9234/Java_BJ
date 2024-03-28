import java.io.*;
import java.util.*;

public class Main{
	static int n, m;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][][] dist;
	static char[][] _map;
	static int answer;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		int startX = 0, startY = 0;
		_map = new char[n][m];
		dist = new int[n][m][(1 << 7) + 1];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				_map[i][j] = str.charAt(j);
				if (_map[i][j] == '0') {
					startX = i;
					startY = j;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < ((1 << 7)); k++) {
					dist[i][j][k] = -1;
				}
			}
		}
		// bfs
		bfs(startX, startY);
		if (answer == 0) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
		br.close();
	}

	static void bfs(int startX, int startY) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { startX, startY, 0, 0 });
		dist[startX][startY][0] = 0;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			int v = now[2];
			int d = now[3];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < n && ny < m && _map[nx][ny] != '#') {
					switch (_map[nx][ny]) {
					case 'a': {
						if (dist[nx][ny][v | (1 << 1)] == -1) {
							dist[nx][ny][v | (1 << 1)] = d+1;
							queue.add(new int[] { nx, ny, v | (1 << 1), d + 1 });
						}
						break;
					}
					case 'b': {
						if (dist[nx][ny][v | (1 << 2)] == -1) {
							dist[nx][ny][v | (1 << 2)] = d+1;
							queue.add(new int[] { nx, ny, v | (1 << 2), d + 1 });
						}
						break;
					}
					case 'c': {
						if (dist[nx][ny][v | (1 << 3)] == -1) {
							dist[nx][ny][v | (1 << 3)] = d+1;
							queue.add(new int[] { nx, ny, v | (1 << 3), d + 1 });
						}
						break;
					}
					case 'd': {
						if (dist[nx][ny][v | (1 << 4)] == -1) {
							dist[nx][ny][v | (1 << 4)] = d+1;
							queue.add(new int[] { nx, ny, v | (1 << 4), d + 1 });
						}
						break;
					}
					case 'e': {
						if (dist[nx][ny][v | (1 << 5)] == -1) {
							dist[nx][ny][v | (1 << 5)] = d+1;
							queue.add(new int[] { nx, ny, v | (1 << 5), d + 1 });
						}
						break;
					}
					case 'f': {
						if (dist[nx][ny][v | (1 << 6)] == -1) {
							dist[nx][ny][v | (1 << 6)] = d+1;
							queue.add(new int[] { nx, ny, v | (1 << 6), d + 1 });
						}
						break;
					}
					case '0': {
						if (dist[nx][ny][v] == -1) {
							dist[nx][ny][v] = d+1;
							queue.add(new int[] { nx, ny, v, d + 1 });
						}
						break;
					}
					case '.': {
						if (dist[nx][ny][v] == -1) {
							dist[nx][ny][v] = d+1;
							queue.add(new int[] { nx, ny, v, d + 1 });
						}
						break;
					}
					case 'A': {
						if ((v & (1 << 1)) != 0 && dist[nx][ny][v] == -1) {
							dist[nx][ny][v] = d+1;
							queue.add(new int[] { nx, ny, v, d + 1 });
						}
						break;
					}
					case 'B': {
						if ((v & (1 << 2)) != 0 && dist[nx][ny][v] == -1) {
							dist[nx][ny][v] = d+1;
							queue.add(new int[] { nx, ny, v, d + 1 });
						}
						break;
					}
					case 'C': {
						if ((v & (1 << 3)) != 0 && dist[nx][ny][v] == -1) {
							dist[nx][ny][v] = d+1;
							queue.add(new int[] { nx, ny, v, d + 1 });
						}
						break;
					}
					case 'D': {
						if ((v & (1 << 4)) != 0 && dist[nx][ny][v] == -1) {
							dist[nx][ny][v] = d+1;
							queue.add(new int[] { nx, ny, v, d + 1 });
						}
						break;
					}
					case 'E': {
						if ((v & (1 << 5)) != 0 && dist[nx][ny][v] == -1) {
							dist[nx][ny][v] = d+1;
							queue.add(new int[] { nx, ny, v, d + 1 });
						}
						break;
					}
					case 'F': {
						if ((v & (1 << 6)) != 0 && dist[nx][ny][v] == -1) {
							dist[nx][ny][v] = d+1;
							queue.add(new int[] { nx, ny, v, d + 1 });
						}
						break;
					}
					case '1': {
						answer = d + 1;
						return;
					}
					}
				}
			}
		}
	}
}
