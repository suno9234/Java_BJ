import java.io.*;
import java.util.*;

public class Main {
	static int answer, n, m, maxRainbowSize, maxGroupR, maxGroupC, tempRainbowSize, tempGroupR, tempGroupC;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] _map;
	static boolean[][] v;
	static ArrayList<int[]> rainbows;
	static ArrayList<int[]> maxGroup;
	static ArrayList<int[]> tempGroup;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		_map = new int[n][n];
		for (int i = 0; i < n; i++) {
			tokens = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		while (true) {
			rainbows = new ArrayList<>();
			maxGroup = new ArrayList<>();
			maxRainbowSize = 0;
			v = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!v[i][j] && _map[i][j] > 0) {
						tempGroup = new ArrayList<>();
						tempGroupR = n;
						tempGroupC = n;
						tempRainbowSize = 0;
						dfs(i, j, _map[i][j]);
						for (int[] rainbow : rainbows) {
							v[rainbow[0]][rainbow[1]] = false;
						}
						if (maxGroup.size() < tempGroup.size()) {
							// 크기가 더 크면 가장 큰것
							insertInfo();
						} else if (maxGroup.size() == tempGroup.size()) {
							if (maxRainbowSize < tempRainbowSize) {
								// 무지개 블록 수가 많은 그룹
								insertInfo();
							} else if (maxRainbowSize == tempRainbowSize) {
								if (maxGroupR < tempGroupR) {
									// 행이 가장 큰것
									insertInfo();
								} else if (maxGroupR == tempGroupR) {
									if (maxGroupC < tempGroupC) {
										// 열이 가장 큰것
										insertInfo();
									}
								}
							}
						}
					}
				}
			}
			int mSize = maxGroup.size();
			if (mSize > 1) {
				for (int[] xy : maxGroup) {
					int x = xy[0];
					int y = xy[1];
					_map[x][y] = -2;
				}
			} else {
				break;
			}
			answer += mSize * mSize;
			gravity();
			turn();
			gravity();
		}
		System.out.println(answer);
		br.close();
	}

	static void insertInfo() {
		maxGroup = tempGroup;
		maxRainbowSize = tempRainbowSize;
		maxGroupR = tempGroupR;
		maxGroupC = tempGroupC;
	}

	static void printA() {
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(_map[i]));
		}
		System.out.println();
	}

	static void turn() {
		int[][] newMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newMap[i][j] = _map[j][n-i-1];
			}
		}
		_map = newMap;
	}

	static void gravity() {
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j >= 0; j--) {
				if (_map[j][i] >= 0) {
					int idx = j;
					for (int k = j + 1; k < n; k++) {
						if (_map[k][i] == -2) {
							idx = k;
						} else {
							break;
						}
					}
					if (idx != j) {
						_map[idx][i] = _map[j][i];
						_map[j][i] = -2;
					}
				}
			}
		}
	}

	static void dfs(int x, int y, int color) {
		v[x][y] = true;
		if (_map[x][y] == color) {
			if (tempGroupR > x || (tempGroupR == x && tempGroupC > y)) {
				tempGroupR = x;
				tempGroupC = y;
			}
		} else {
			rainbows.add(new int[] { x, y });
			tempRainbowSize++;
		}
		tempGroup.add(new int[] { x, y });
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < n && ny < n && !v[nx][ny] && (_map[nx][ny] == color || _map[nx][ny] == 0)) {
				dfs(nx, ny, color);
			}
		}
	}
}
