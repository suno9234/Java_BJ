import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static char[][] _map;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);

		_map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				_map[i][j] = s.charAt(j);
			}
		}
		int answer = dfs(_map,1);
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
		br.close();
	}

	static int dfs(char[][] _map, int cnt) {
		char[][] lm = left(mapcopy(_map));
		char[][] rm = right(mapcopy(_map));
		char[][] um = up(mapcopy(_map));
		char[][] dm = down(mapcopy(_map));
//		for(int i = 0 ; i < lm.length ; i++) {
//			System.out.println(Arrays.toString(lm[i]));
//		}
//		System.out.println();
//		for(int i = 0 ; i < rm.length ; i++) {
//			System.out.println(Arrays.toString(rm[i]));
//		}
//		System.out.println();
//		for(int i = 0 ; i < um.length ; i++) {
//			System.out.println(Arrays.toString(um[i]));
//		}
//		System.out.println();
//		for(int i = 0 ; i < dm.length ; i++) {
//			System.out.println(Arrays.toString(dm[i]));
//		}
//		System.out.println();
		if (lm[0][0] == 'A' || rm[0][0] == 'A' || um[0][0] == 'A' || dm[0][0] == 'A') {
			return cnt;
		}
		if (cnt >= 10) {
			return Integer.MAX_VALUE;
		}
		int dlm = Integer.MAX_VALUE;
		if (lm[0][0] != 'E')
			dlm = dfs(lm, cnt + 1);
		int drm = Integer.MAX_VALUE;
		if (rm[0][0] != 'E')
			drm = dfs(rm, cnt + 1);
		int dum = Integer.MAX_VALUE;
		if (um[0][0] != 'E')
			dum = dfs(um, cnt + 1);
		int ddm = Integer.MAX_VALUE;
		if (dm[0][0] != 'E')
			ddm = dfs(dm, cnt + 1);
		return Math.min(Math.min(dlm, drm), Math.min(dum, ddm));
	}

	static char[][] down(char[][] map) {
		int flag = 0;
		for (int j = 1; j < m - 1; j++) {
			for (int i = n - 2; i >= 1; i--) {
				if (map[i][j] == 'B' || map[i][j] == 'R') {
					int dIdx = i;
					while (dIdx < n - 1) {
						dIdx++;
						if (map[dIdx][j] == '#' || map[dIdx][j] == 'B' || map[dIdx][j] == 'R') {
							if (dIdx - 1 != i) {
								map[dIdx - 1][j] = map[i][j];
								map[i][j] = '.';
							}
							break;
						} else if (map[dIdx][j] == 'O') {
							if (map[i][j] == 'B') {
								return new char[][] { { 'E' } };
							} else {
								flag = 1;
							}
							map[i][j] = '.';
							break;
						}
					}
				}
			}
			if (flag == 1) {
				return new char[][] { { 'A' } };
			}
		}
		return map;
	}

	static char[][] up(char[][] map) {
		int flag = 0;
		for (int j = 1; j < m - 1; j++) {
			for (int i = 1; i < n - 1; i++) {
				if (map[i][j] == 'B' || map[i][j] == 'R') {
					int uIdx = i;
					while (uIdx > 0) {
						uIdx--;
						if (map[uIdx][j] == '#' || map[uIdx][j] == 'B' || map[uIdx][j] == 'R') {
							if (uIdx + 1 != i) {
								map[uIdx + 1][j] = map[i][j];
								map[i][j] = '.';
							}
							break;
						} else if (map[uIdx][j] == 'O') {
							if (map[i][j] == 'B') {
								return new char[][] { { 'E' } };
							} else {
								flag = 1;
							}
							map[i][j] = '.';
							break;
						}
					}
				}
			}
			if (flag == 1) {
				return new char[][] { { 'A' } };
			}
		}
		return map;
	}

	static char[][] right(char[][] map) {
		int flag = 0;
		for (int i = 1; i < n - 1; i++) {
			for (int j = m - 2; j >= 1; j--) {
				if (map[i][j] == 'B' || map[i][j] == 'R') {
					int rIdx = j;
					while (rIdx < m - 1) {
						rIdx++;
						if (map[i][rIdx] == '#' || map[i][rIdx] == 'B' || map[i][rIdx] == 'R') {
							if (rIdx - 1 != j) {
								map[i][rIdx - 1] = map[i][j];
								map[i][j] = '.';
							}
							break;
						} else if (map[i][rIdx] == 'O') {
							if (map[i][j] == 'B') {
								return new char[][] { { 'E' } };
							} else {
								flag = 1;
							}
							map[i][j] = '.';
							break;
						}
					}
				}
			}
			if (flag == 1) {
				return new char[][] { { 'A' } };
			}
		}
		return map;
	}

	static char[][] left(char[][] map) {
		int flag = 0;
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				if (map[i][j] == 'B' || map[i][j] == 'R') {
					int lIdx = j;
					while (lIdx > 0) {
						lIdx--;
						if (map[i][lIdx] == '#' || map[i][lIdx] == 'B' || map[i][lIdx] == 'R') {
							if (lIdx + 1 != j) {
								map[i][lIdx + 1] = map[i][j];
								map[i][j] = '.';
							}
							break;
						} else if (map[i][lIdx] == 'O') {
							if (map[i][j] == 'B') {
								return new char[][] { { 'E' } };
							} else {
								flag = 1;
							}
							map[i][j] = '.';
							break;
						}
					}
				}
			}
			if (flag == 1) {
				return new char[][] { { 'A' } };
			}
		}
		return map;
	}

	static char[][] mapcopy(char[][] map) {
		char[][] newMap = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}
}
