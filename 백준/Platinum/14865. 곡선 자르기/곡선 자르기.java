import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };

	static long[] dists;
	static int[] dirs; //
	static long[][] coors;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		n = Integer.parseInt(br.readLine());

		dirs = new int[n];
		dists = new long[n];
		coors = new long[n][2];
		for (int i = 0; i < n; i++) {
			tokens = br.readLine().split(" ");
			long x = Long.parseLong(tokens[0]);
			long y = Long.parseLong(tokens[1]);
			coors[i] = new long[] { x, y };
		}
		for (int i = 0; i < n; i++) {
			long[] now = coors[i];
			long[] next = coors[(i + 1) % n];
			if (now[0] == next[0]) {
				if (now[1] > next[1]) {
					dirs[i] = 0;
					dists[i] = now[1] - next[1];
				} else {
					dirs[i] = 2;
					dists[i] = next[1] - now[1];
				}
			} else {
				if (now[0] > next[0]) {
					dirs[i] = 1;
					dists[i] = now[0] - next[0];
				} else {
					dirs[i] = 3;
					dists[i] = next[0] - now[0];
				}
			}
		}
		ArrayList<long[]> tops = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			long[] now = coors[i];
			long x = now[0];
			long y = now[1];
			if (y < 0 && dirs[i] == 2) {
				// x축 아래에 있으면서 위로 올라오는 경우만 체크
				long endPoint = dfs(x + dx[dirs[i]] * dists[i], y + dy[dirs[i]] * dists[i], (i + 1)%n);
				if (x < endPoint) {
					tops.add(new long[] { x, endPoint});
				} else {
					tops.add(new long[] { endPoint, x});
				}
			}
		}
		int ans1 = 0;
		int ans2 = 0;
		// 다른 봉우리에 포함되지 않는 봉우리 개수
		for (int i = 0; i < tops.size(); i++) {
			long x = tops.get(i)[0];
			long y = tops.get(i)[1];
			int flag = 0;
			for (int j = 0; j < tops.size(); j++) {
				if (i != j) {
					long x1 = tops.get(j)[0];
					long y1 = tops.get(j)[1];
					// x1 y1 = 다른 놈의 왼오좌표
					if (x1 < x && y < y1)
					{   // 나보다 큰놈이 있으면 flag = 1
						// x1 < x, y < y1
						flag = 1;
						break;
					}
				}

			}
			if (flag == 0 ) {
				// 나보다 큰놈이 없으면
				ans1++;
			}

		}
		for (int i = 0; i < tops.size(); i++) {
			long x = tops.get(i)[0];
			long y = tops.get(i)[1];
			int flag = 0;
			for (int j = 0; j < tops.size(); j++) {
				if (i != j) {
					long x1 = tops.get(j)[0];
					long y1 = tops.get(j)[1];
					if (x < x1 && y1 < y) {
						// x < x1 y1 < y
						// 내가 다른 봉우리를 포함하고 있으면 flag = 0;
						flag = 1;
						break;
					}
				}

			}
			if (flag == 0) {
				ans2++;
			}

		}
		System.out.println(ans1 + " " + ans2);

	}

	static long dfs(long x, long y, int i) {
		if (y < 0) {
			return x;
		}
		int dir = dirs[i%n];
		long dist = dists[i%n];
		long nx = x + dx[dir] * dist;
		long ny = y + dy[dir] * dist;
		return dfs(nx, ny, i + 1);
	}

}
