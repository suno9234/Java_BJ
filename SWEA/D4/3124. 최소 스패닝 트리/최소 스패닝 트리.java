import java.util.*;
import java.io.*;

public class Solution {

	static int v, e;
	static long answer;
	static PriorityQueue<int[]> edges;
	static int [] parent;
	static List<int[]>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine()) + 1;
		for (int tc = 1; tc < test_case; tc++) {
			answer = 0;
			String[] tokens = br.readLine().split(" ");
			v = Integer.parseInt(tokens[0]);
			e = Integer.parseInt(tokens[1]);
			edges = new PriorityQueue<>(new Comparator<int[]>() {
				public int compare(int [] a , int [] b) {
					return a[2]-b[2];
				}
			});
			graph = new List[v + 1];
			parent = new int[e+1];
			Arrays.setAll(parent, x->x);
			for (int i = 1; i < v + 1; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < e; i++) {
				tokens = br.readLine().split(" ");
				int a = Integer.parseInt(tokens[0]);
				int b = Integer.parseInt(tokens[1]);
				int cost = Integer.parseInt(tokens[2]);
				edges.add(new int[] { a, b, cost });
				graph[a].add(new int[] { b, cost });
				graph[b].add(new int[] { a, cost });
			}
			if (e > v * (v - 1) / 2) {
				answer = prim();
			} else {
				answer = kruskal();
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	static long kruskal() {
		int cnt = 0;
		long weight = 0;
		while(cnt < v-1) {
			int [] now = edges.poll();
			int a = now[0];
			int b = now[1];
			int cost = now[2];
			if(find(a)!=find(b)) {
				union(a,b);
				cnt++;
				weight+=cost;
			}
		}
		return weight;
	}

	static long prim() {
		long weight = 0;
		boolean [] visited = new boolean[v+1];
		PriorityQueue<int []>pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int [] a , int [] b) {
				return a[1]-b[1];
			}
		});
		pq.add(new int[] {1,0});
		while(!pq.isEmpty()) {
			int [] now = pq.poll();
			int num = now[0];
			int cost = now[1];
			if(!visited[num]) {
				visited[num] = true;
				weight+=cost;
				for(int [] next : graph[num]) {
					if(!visited[next[0]])pq.add(next);
				}
			}
			
		}
		return weight;
	}
	
	static void union(int a , int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return;
		if(pa > pb) parent[pa] = pb;
		else parent[pb] = pa;
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
}
