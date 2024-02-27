import java.util.*;
import java.io.*;

public class Main {

	static int n, m, h, answer = -1;
	static int[][] _map;
	static boolean finish;
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = bufferedReader.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		h = Integer.parseInt(tokens[2]);
		_map = new int[h + 1][n];

		for (int i = 0; i < m; i++) {
			tokens = bufferedReader.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]) - 1;
			int b = Integer.parseInt(tokens[1]) - 1;
			_map[a][b] = 1;
			_map[a][b + 1] = -1;
			// 1 = 사다리 왼쪽 -1 = 사다리 오른쪽
		}
		for (int i = 0; i <= 3; i++) {
			comb(0, 0, i);
			if(finish) break;
		}
		System.out.println(answer);
	}

	static void comb(int cnt, int start, int endPoint) {
		if(finish) {
			return;
		}
		if (cnt == endPoint) {
			if (check()) {
				finish = true;
				answer = cnt;
			}
			return;
		}
		for (int i = start; i < h * n; i++) {
			if (_map[i / n][i % n] == 0 && i % n < n - 1) {
				// 현재 놓으려는 곳에 사다리가 없어야 하고 j의 범위는 가장 끝칸보다는 한칸 적어야 한다
				if (i % n < n - 2 && _map[i / n][i % n + 1] != 0)
					continue;
				// 사다리를 놓아 연결되는 칸에 사다리가 있는 경우는 제외해야 한다
				_map[i / n][i % n] = 1;
				_map[i / n][i % n + 1] = -1;
				comb(cnt + 1, i + 1, endPoint);
				_map[i / n][i % n] = 0;
				_map[i / n][i % n + 1] = 0;
			}

		}
	}

	static boolean check() {
		for (int i = 0; i < n; i++) {
			int x = 0, y = i;
			while (x < h + 1) {
				if (_map[x][y] > 0) {
					y++;
				} else if (_map[x][y] < 0) {
					y--;
				}
				x++;
			}
			if (y != i) {
				return false;
			}
		}
		return true;
	}
}
