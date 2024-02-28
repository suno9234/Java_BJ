import java.util.*;
import java.io.*;

public class Solution{

	static int answer, n, startX, startY, endX, endY;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] _map;
	static boolean[][] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine()) + 1;
		for (int tc = 1; tc < test_case; tc++) {
			answer = -1;
			n = Integer.parseInt(br.readLine());
			_map = new int[n][n];
			v = new boolean[n][n];
			String[] tokens;
			for (int i = 0; i < n; i++) {
				tokens = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					_map[i][j] = Integer.parseInt(tokens[j]);
				}
			}

			tokens = br.readLine().split(" ");
			startX = Integer.parseInt(tokens[0]);
			startY = Integer.parseInt(tokens[1]);

			tokens = br.readLine().split(" ");
			endX = Integer.parseInt(tokens[0]);
			endY = Integer.parseInt(tokens[1]);

			ArrayDeque<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] { startX, startY, 0, 0 });
			v[startX][startY] = true;
			int time = 0;
			while (!queue.isEmpty()) {
				if (answer > 0) {
					break;
				}

				ArrayDeque<int[]> nextQueue = new ArrayDeque<>();
				while (!queue.isEmpty()) {
					int[] now = queue.poll();
					int x = now[0];
					int y = now[1];
					int _type = now[2];
					if (x == endX && y == endY) {
						answer = time;
						break;
					}
					if (_type == 0 || (_type == 2 && time % 3 == 0)) {
						for (int i = 0; i < 4; i++) {
							int nx = x + dx[i];
							int ny = y + dy[i];
							if (nx >= 0 && ny >= 0 && nx < n && ny < n && !v[nx][ny] && _map[nx][ny] != 1) {
								v[nx][ny] = true;
								nextQueue.add(new int[] { nx, ny, _map[nx][ny], time % 3 });
							}
						}
					} else {
						nextQueue.add(now);

					}
				}

				queue = nextQueue;
				time++;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
