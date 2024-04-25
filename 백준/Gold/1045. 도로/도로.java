import java.io.*;
import java.util.*;

public class Main{
	static int n, m;
	static int[] answer;
	static boolean[] v;
	static boolean[][] map, v2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		answer = new int[n];
		map = new boolean[n][n];
		v2 = new boolean[n][n];
		v = new boolean[n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			int o1M = Math.min(o1[0], o1[1]);
			int o1L = Math.max(o1[0], o1[1]);
			int o2M = Math.min(o2[0], o2[1]);
			int o2L = Math.max(o2[0], o2[1]);
			if (o1M == o2M) {
				return Integer.compare(o1L, o2L);
			}
			return Integer.compare(o1M, o2M);
		});
		PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> {
			int o1M = Math.min(o1[0], o1[1]);
			int o1L = Math.max(o1[0], o1[1]);
			int o2M = Math.min(o2[0], o2[1]);
			int o2L = Math.max(o2[0], o2[1]);
			if (o1M == o2M) {
				return Integer.compare(o1L, o2L);
			}
			return Integer.compare(o1M, o2M);
		});
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				if (s.charAt(j) == 'Y') {
					map[i][j] = true;
					pq2.add(new int[] { i, j });
				}
			}
		}
		pq.add(new int[] { 0, 0 });
		int cnt = -1;
		answer[0] = -2;
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int start = now[0];
			int next = now[1];
			if (v[next])
				continue;

			v[next] = true;
			cnt++;
			answer[start]++;
			answer[next]++;
			v2[start][next] = true;
			v2[next][start] = true;

			for (int i = 0; i < n; i++) {
				if (!v[i] && map[next][i]) {
					pq.add(new int[] { next, i });
				}
			}
		}
		if (cnt < n - 1) {
			System.out.println(-1);
			return;
		}

		while (cnt < m && !pq2.isEmpty()) {
			int[] now = pq2.poll();
			int start = now[0];
			int next = now[1];
			if (!v2[start][next]) {
				v2[start][next] = true;
				v2[next][start] = true;
				cnt++;
				pq.add(new int[] { start, next });
			}
		}

		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int start = now[0];
			int end = now[1];
			answer[start]++;
			answer[end]++;
		}
		if (cnt < m) {
			System.out.println(-1);
			return;
		}
		for (int i = 0; i < n; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
