import java.io.*;
import java.util.*;

public class Main{
	static int n, m, t;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][] v;
	static int[][] onepan;
	static int flag = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		t = Integer.parseInt(tokens[2]);
		onepan = new int[n][m];
		for (int i = 0; i < n; i++) {
			tokens = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				onepan[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		for (int i = 0; i < t; i++) {
			tokens = br.readLine().split(" ");
			int x = Integer.parseInt(tokens[0]);
			int d = Integer.parseInt(tokens[1]);
			int k = Integer.parseInt(tokens[2]);
			if (d == 1) {
				// 0 = 시계
				// 1 = 반시계
				k = m - k; // 오1 = 왼3 오3 = 왼1 
			}
			for (int j = 0; j < n; j++) {
				if ((j + 1) % x == 0) {
					// 번호가 x의 배수인 원판들
					onepan[j] = turnR(onepan[j], k);
				}
			}
			v = new boolean[n][m];
			flag = 0;
			for (int j = 0; j < n; j++) {
				for (int l = 0; l < m; l++) {
					if (!v[j][l] && onepan[j][l] > 0) {
						// 아직 방문x
						dfs(j, l, 0);
					}
				}
			}
			if (flag == 0) {
				int cnt = 0;
				int sum = 0;
				for (int j = 0; j < n; j++) {
					for (int l = 0; l < m; l++) {
						if (onepan[j][l] > 0) {
							cnt++;
							sum += onepan[j][l];
						}
					}
				}
				if (cnt != 0) {
					double avg = 1.0*sum / cnt;
					for (int j = 0; j < n; j++) {
						for (int l = 0; l < m; l++) {
							if (onepan[j][l] == 0)
								continue;
							if (onepan[j][l] > avg) {
								onepan[j][l]--;
							} else if (onepan[j][l] < avg) {
								onepan[j][l]++;
							}
						}
					}
				}

			}
		}
		int answer = 0;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				answer+=onepan[i][j];
			}
		}
		System.out.println(answer);
		br.close();
	}

	static void dfs(int x, int y, int cnt) {
		int value = onepan[x][y];
		if (cnt > 0) {
			flag = 1;
			v[x][y] = true;
			onepan[x][y] = 0;
		}
		for (int i = 0; i < 4; i++) {
			int nx = (x + dx[i]);
			int ny = (y + dy[i] + m) % m;
			if (nx>=0 && nx < n && value == onepan[nx][ny])
				dfs(nx, ny, cnt + 1);
		}

	}

	static int[] turnR(int[] arr, int k) {
		int[] result = new int[m];
		for (int i = 0; i < m; i++) {
			result[(i + k) % m] = arr[i];
		}
		return result;
	}
}
