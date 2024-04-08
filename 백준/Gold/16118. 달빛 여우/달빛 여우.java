import java.io.*;
import java.util.*;

public class Main{
	static int n, m;
	static long[] minDistFox;
	static long[][] minDistWolf;
	static boolean[] v;
	static List<int[]>[] graph;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new List[n + 1];
		minDistFox = new long[n + 1];
		minDistWolf = new long[2][n + 1];
		Arrays.fill(minDistFox, Long.MAX_VALUE);
		for (int i = 0; i < 2; i++) {
			Arrays.fill(minDistWolf[i], Long.MAX_VALUE);
		}
		for (int i = 1; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) * 2;
			graph[a].add(new int[] { b, d });
			graph[b].add(new int[] { a, d });
		}
		v = new boolean[n + 1];
		PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
		pq.add(new long[] { 1, 0 });
		int cnt = 0;
		while (!pq.isEmpty()) {
			long[] now = pq.poll();
			int pos = (int) now[0];
			long dist = now[1];
			if (v[pos])
				continue;
			v[pos] = true;
			minDistFox[pos] = dist;
			if (++cnt == n) {
				break;
			}
			for (int[] next : graph[pos]) {
				int np = next[0];
				int nd = next[1];
				if (minDistFox[np] > dist + nd) {
					minDistFox[np] = dist + nd;
					pq.add(new long[] { np, dist + nd });
				}
			}
		}
		pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
		pq.add(new long[] { 1, 0, 0 }); // 1번에 걸어서 들어온채로 시작
		// minDistWolf[0] = 걸어서 i번째에 오는 최솟값
		// minDistWolf[1] = 뛰어서 i번째에 오는 최솟값
		cnt = 0;
		long ndd = 0;
		while (!pq.isEmpty()) {
			long[] now = pq.poll();
			int pos = (int) now[0];
			long dist = now[1];
			int flag = (int) now[2];
			if(minDistWolf[flag][pos] < dist) continue;
			minDistWolf[flag][pos] = dist;
			if(++cnt == n*2) break; 
			for (int[] next : graph[pos]) {
				int np = next[0];
				int nd = next[1];
				if (flag == 0) {
					// 걸어옴 (이제 달려야됨)
					nd = nd/2;
					ndd = dist+nd;
					if (minDistWolf[1][np] > ndd) {
						// 다음 np까지 뛰어가는 거리 = 현재까지 걸어온거리 + 1/2
						minDistWolf[1][np] = ndd;
						pq.add(new long[] { np, ndd, 1 });
					}
				} else {
					// 뛰어옴 ( 이제 걸어야됨)
					nd = nd*2;
					ndd = dist+nd;
					if (minDistWolf[0][np] > ndd) {
						// 다음 np까지 걸어가는 거리  = 현재까지 뛰어온거리 + x2
						minDistWolf[0][np] = ndd;
						pq.add(new long[] { np, ndd, 0});
					}
				}
			}
		}
		int answer = 0;
//		System.out.println(Arrays.toString(minDistFox));
//		System.out.println("걸어서도착");
//		System.out.println(Arrays.toString(minDistWolf[0]));
//		System.out.println("뛰어서도착");
//		System.out.println(Arrays.toString(minDistWolf[1]));
		for (int i = 1; i < n + 1; i++) {
			if (minDistFox[i] < Math.min(minDistWolf[0][i], minDistWolf[1][i])) {
				answer++;
			}
		}
		System.out.println(answer);
		br.close();
	}
}
