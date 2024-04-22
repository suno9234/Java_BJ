import java.util.*;
import java.io.*;

public class Main {

	static int n, answer, bx, by, ex, ey, bd, ed;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static char[][] board;
	static boolean[][][] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		board = new char[n][n];
		v = new boolean[n][n][2];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		label: for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'B') {
					if (j + 1 < n && board[i][j + 1] == 'B') {
						bx = i;
						by = j + 1;
						bd = 0;
					} else {
						bx = i + 1;
						by = j;
						bd = 1;
					}
					break label;
				}
			}
		}
		label2: for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'E') {
					if (j + 1 < n && board[i][j + 1] == 'E') {
						ex = i;
						ey = j + 1;
						ed = 0;
					} else {
						ex = i + 1;
						ey = j;
						ed = 1;
					}
					break label2;
				}
			}
		}
		v[bx][by][bd] = true;
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { bx, by, bd, 0 });
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			int d = now[2];
			int dist = now[3];
			if(x == ex && y == ey && d == ed) {
				answer = dist;
				break;
			}
			if (d == 0) {
				// 현재 상태 가로
				if (y + 2 < n && board[x][y + 2] != '1' && !v[x][y + 1][d]) {
					// 우
					v[x][y + 1][d] = true;
					queue.add(new int[] { x, y + 1, d, dist + 1 });
				}
				if (y - 2 >= 0 && board[x][y - 2] != '1' && !v[x][y - 1][d]) {
					// 좌
					v[x][y - 1][d] = true;
					queue.add(new int[] { x, y - 1, d, dist + 1 });
				}
				if (x - 1 >= 0 && board[x - 1][y] != '1' && board[x - 1][y - 1] != '1' && board[x - 1][y + 1] != '1'
						&& !v[x - 1][y][d]) {
					// 상
					v[x - 1][y][d] = true;
					queue.add(new int[] { x - 1, y, d, dist + 1 });
				}
				if (x + 1 < n && board[x + 1][y] != '1' && board[x + 1][y - 1] != '1' && board[x + 1][y + 1] != '1'
						&& !v[x + 1][y][d]) {
					// 하
					v[x + 1][y][d] = true;
					queue.add(new int[] { x + 1, y, d, dist + 1 });
				}
				if (x - 1 >= 0 && y - 1 >= 0 && x + 1 < n && y + 1 < n && board[x - 1][y - 1] != '1'
						&& board[x - 1][y] != '1' && board[x - 1][y + 1] != '1' && board[x + 1][y - 1] != '1'
						&& board[x + 1][y] != '1' && board[x + 1][y + 1] != '1' && !v[x][y][1]) {
					v[x][y][1] = true;
					queue.add(new int[] { x, y, 1, dist + 1 });
				}
			} else {
				// 현재 상태 세로
				if (x + 2 < n && board[x + 2][y] != '1' && !v[x + 1][y][d]) {
					// 하
					v[x + 1][y][d] = true;
					queue.add(new int[] { x + 1, y, d, dist + 1 });
				}
				if (x - 2 >= 0 && board[x - 2][y] != '1' && !v[x - 1][y][d]) {
					// 상
					v[x - 1][y][d] = true;
					queue.add(new int[] { x - 1, y, d, dist + 1 });
				}
				if (y - 1 >= 0 && board[x][y - 1] !='1' && board[x - 1][y - 1] !='1' && board[x + 1][y - 1] != '1'
						&& !v[x][y - 1][d]) {
					// 좌
					v[x - 1][y][d] = true;
					queue.add(new int[] { x, y - 1, d, dist + 1 });
				}
				if (y + 1 < n && board[x][y + 1] != '1'&& board[x - 1][y + 1] != '1' && board[x + 1][y + 1] != '1'
						&& !v[x][y + 1][d]) {
					// 우
					v[x][y + 1][d] = true;
					queue.add(new int[] { x, y + 1, d, dist + 1 });
				}
				if (x - 1 >= 0 && y - 1 >= 0 && x + 1 < n && y + 1 < n && board[x - 1][y - 1] != '1'
						&& board[x][y - 1] != '1' && board[x + 1][y - 1] != '1' && board[x - 1][y + 1] != '1'
						&& board[x][y + 1] != '1' && board[x + 1][y + 1] != '1' && !v[x][y][0]) {
					v[x][y][1] = true;
					queue.add(new int[] { x, y, 0, dist + 1 });
				}
			}
		}
		System.out.println(answer);
	}
}
