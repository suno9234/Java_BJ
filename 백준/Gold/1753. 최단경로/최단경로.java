import java.io.*;
import java.util.*;

public class Main{
	static int v, e;
	static List<int[]>[] graph;
	static boolean [] visited;
	static int [] minDist;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		tokens = br.readLine().split(" ");
		v = Integer.parseInt(tokens[0]);
		e = Integer.parseInt(tokens[1]);
		graph = new List[v + 1];
		minDist = new int[v+1];
		visited = new boolean[v+1];
		for(int i = 0 ; i < v+1; i++) {
			graph[i] = new ArrayList<>();
			minDist[i] = Integer.MAX_VALUE;
		}
		int start = Integer.parseInt(br.readLine());
		for (int i = 0; i < e; i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			int w = Integer.parseInt(tokens[2]);
			graph[a].add(new int[] { b, w });
		}
		
		PriorityQueue <int [] > pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		pq.add(new int[] {start,0});
		minDist[start] = 0;
		int cnt = 0 ;
		while(!pq.isEmpty()) {
			int [] now = pq.poll();
			int num = now[0];
			int dist = now[1];
			if(visited[num]) continue;
			visited[num] = true;
			cnt++;
			if(cnt == v) {
				break;
			}
			List<int []> nexts = graph[num];
			for(int [] next : nexts) {
				int b = next[0];
				int w = next[1];
				if(!visited[b] && minDist[b] > dist+w) {
					minDist[b] = dist+w;
					pq.add(new int[] {b,dist+w});
				}
			}
			
		}
		for(int i = 1 ; i < v+1;i++) {
			if(minDist[i]!= Integer.MAX_VALUE) {
			sb.append(minDist[i]).append("\n");
			}else {
				sb.append("INF\n");
			}
		}
		System.out.println(sb);
		br.close();
	}
}
