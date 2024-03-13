import java.io.*;
import java.util.*;

public class Main{
	static int n, m, answer;
	static int initDist;
	static int[] parent;
	static List<int[]>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		graph = new List[n + 1];
		for (int i = 1; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			int t = Integer.parseInt(tokens[2]);
			graph[a].add(new int[] { b, t });
			graph[b].add(new int[] { a, t });
		}
		parent = new int[n + 1];
		initDist = dijkstra(new int[] { -1, -1 });
		int node = n;
		while(node > 1) {
			int result = dijkstra(new int[] {node,parent[node]});
			if(result == -1) {
				// 끝까지 못가는 경우
				System.out.println(-1);
				return;
			}else {
				answer = Math.max(answer, result-initDist);
			}
			node = parent[node];
		}
		System.out.println(answer);
		
	}

	static int dijkstra(int[] deleted) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		boolean[] v = new boolean[n + 1];
		int[] minDist = new int[n + 1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		minDist[1] = 0;
		pq.add(new int[] { 1, 0,1 });
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int pos = now[0];
			int dist = now[1];
			if (v[pos])
				continue;
			if (pos == n) {
				return minDist[n];
			}
			v[pos] = true;
			for (int[] next : graph[pos]) {
				int np = next[0];
				int nd = next[1];
				if (!v[np] && minDist[np] > dist + nd && !(pos == deleted[0] && np == deleted[1]) && !(pos == deleted[1] && np == deleted[0])) {
					if(deleted[0] == -1) 
						parent[np] = pos;
					minDist[np] = dist + nd;
					pq.add(new int[] { np, dist + nd,pos });
				}
			}
		}
		return -1;
	}
}
