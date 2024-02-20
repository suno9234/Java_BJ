import java.io.*;
import java.util.*;

public class Main{

	static int n,m;
	static ArrayDeque<Integer>[] graph;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		graph = new ArrayDeque[n+1];
		for(int i = 0 ; i < n+1; i++) {
			graph[i] = new ArrayDeque<Integer>();
		}
		int [] inter = new int [n+1];
		for(int i = 0 ; i <m; i++) {
			tokens =br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			graph[a].add(b);
			inter[b]++;
		}
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i = 1; i < n+1 ; i++) {
			if(inter[i] == 0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(" ");
			ArrayDeque<Integer> nexts = graph[now];
			while(!nexts.isEmpty()) {
				int n = nexts.poll();
				inter[n]--;
				if(inter[n] == 0) {
					q.add(n);
				}
			}
		}
		System.out.println(sb.toString());
	}

}
