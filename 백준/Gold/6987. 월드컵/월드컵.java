import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int[] wins;
	static int[] draws;
	static int[] loses;
	static int[][] _map;
	static boolean isEnded = false;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		for (int tc = 0; tc < 4; tc++) {
			String[] tokens = br.readLine().split(" ");
			boolean isPossible = true;
			_map = new int[6][6];
			wins = new int[6];
			draws = new int[6];
			loses = new int[6];
			isEnded = false;
			for (int i = 0; i < 6; i++) {
				int w = Integer.parseInt(tokens[i * 3]);
				int d = Integer.parseInt(tokens[i * 3 + 1]);
				int l = Integer.parseInt(tokens[i * 3 + 2]);
				if (w + d + l != 5) {
					isPossible = false;
					break;
				}
				wins[i] = w;
				draws[i] = d;
				loses[i] = l;
			}
			if (isPossible) {
				solve(0);
				if (isEnded) {
					sb.append("1 ");
				} else {
					sb.append("0 ");
				}
			} else {
				sb.append("0 ");
			}
		}
		System.out.println(sb.toString());
	}

	/*
	 * A B C D E F A 0 1 1 1 1 1 B C D E F
	 */
	static void solve(int start) {
		if(isEnded) {
			return;
		}
		if (start == 36) {
			isEnded = true;
			return;
		}

		int x = start / 6;
		int y = start % 6;
		if (x != y && _map[x][y] == 0) {
			_map[x][y] = 1;
			_map[y][x] = 3;
			if (check()) {
				solve(start + 1);
			}
			_map[x][y] = 3;
			_map[y][x] = 1;
			if (check()) {
				solve(start + 1);
			}
			_map[x][y] = 2;
			_map[y][x] = 2;
			if (check()) {
				solve(start + 1);
			}
			_map[x][y] = 0;
			_map[y][x] = 0;
		} else {
			solve(start + 1);
		}

	}

	static boolean check() {
		for (int i = 0; i < 6; i++) {
			int w = 0;
			int d = 0;
			int l = 0;
			for (int j = 0; j < 6; j++) {
				switch (_map[i][j]) {
				case 1:
					w++;
					break;
				case 2:
					d++;
					break;
				case 3:
					l++;
					break;
				}
			}
			if (w > wins[i] || d > draws[i] || l > loses[i]) {
				return false;
			}
		}
		return true;
	}
}
