import java.util.*;
import java.io.*;

public class Main{
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static List<int[]> toDelete;
	static char[][] _map;
	static boolean[][] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		_map = new char[12][6];

		for (int i = 0; i < 12; i++) {
			String nl = br.readLine();
			for (int j = 0; j < 6; j++) {
				_map[i][j] = nl.charAt(j);
			}
		}
		int answer = 0;
		while (true) {
			v = new boolean[12][6];
			boolean isCombo = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (_map[i][j] != '.') {
						// 구슬이 있으면
						toDelete = new ArrayList<>();
						explode(i, j, _map[i][j]);
						if (toDelete.size() >= 4) {
							isCombo = true;
							for (int[] xy : toDelete) {
								_map[xy[0]][xy[1]] = '.';
							}
						}
					}
				}
			}
			if (isCombo) {
				answer++;
			} else {
				break;
			}
			for (int i = 0; i < 6; i++) {
				int last = 11;
				for (int j = 10; j >= 0; j--) {
					if (_map[j][i] != '.') {
						while (last >= 0 && last > j) {
							if (_map[last][i] == '.') {
								_map[last][i] = _map[j][i];
								_map[j][i] = '.';
								last-=1;
								break;
							}
							last -= 1;
						}
					}
				}
			}
		}
		System.out.println(answer);
	}

	static void explode(int x, int y, char _type) {
		v[x][y] = true;
		toDelete.add(new int[] { x, y });
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < 12 && ny < 6 && !v[nx][ny] && _map[nx][ny] == _type) {
				explode(nx, ny, _type);
			}
		}
	}

}
