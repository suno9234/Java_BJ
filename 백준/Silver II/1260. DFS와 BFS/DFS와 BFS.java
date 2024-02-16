import java.util.*;
import java.io.*;

public class Main{

	static int n,m,v;
	static List<Integer> [] graph ;
	static boolean [] visited ;
	static StringBuilder sb;
	public static void main(String[] args)throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		v = Integer.parseInt(tokens[2]);
		graph = new List[n+1];
		for(int i = 1 ; i < n+1; i++) {
			graph[i] = new LinkedList<>(); 
		}
		
		for(int i = 0 ; i < m ; i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			graph[a].add(b);
			graph[b].add(a);
		}
		visited = new boolean[n+1];
		bfs(v);
		sb.append("\n");
		dfs(v);
		System.out.println(sb.toString());
	}
	static void dfs(int v) {
		boolean [] visited = new boolean[n+1];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(v);
		visited[v] = true;
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(" ");
			if(graph[now] == null) continue;
			List<Integer> nexts =  graph[now];
			Collections.sort(nexts);
			for(int _next : nexts) {
				if(visited[_next])continue;
				visited[_next] = true;
				q.add(_next);
			}
		}
	}
	static void bfs(int v) {
		visited[v] = true;
		sb.append(v).append(" ");
		if(graph[v] == null) return;
		List<Integer> nexts  = graph[v];
		Collections.sort(nexts);
		for(int _next : nexts) {
			if(visited[_next])continue;
			visited[_next] = true;
			bfs(_next);
		}
	}

}
