import java.io.*;
import java.util.*;

public class Main{

	static int n,m;
	static List<Integer>[] graph;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		graph = new List[n+1];
		for(int i = 0 ; i < n+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		int [] inter = new int [n+1];
		for(int i = 0 ; i <m; i++) {
			tokens =br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			graph[a].add(b);
			inter[b]++;
		}
		int cnt = 0;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i = 1; i < n+1 ; i++) {
			if(inter[i] == 0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int now = q.poll();
			cnt++;
			sb.append(now).append(" ");
			List<Integer> nexts = graph[now];
			for(int n : nexts) {
				inter[n]--;
				if(inter[n] == 0) {
					q.add(n);
				}
			}
		}
		System.out.println(sb.toString());
	}

}
