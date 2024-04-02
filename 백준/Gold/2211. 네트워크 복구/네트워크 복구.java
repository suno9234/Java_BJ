import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int [] minDist,minDist2;
	static boolean [] v;
	static List<int[]> [] graph;
	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		graph = new List[n+1];
		v = new boolean[n+1];
		minDist = new int[n+1];
		minDist2 = new int[n+1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		Arrays.fill(minDist2, Integer.MAX_VALUE);
		for(int i = 0 ; i < n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < m ; i++) {
			tokens = br.readLine().split(" ");
			int a  = Integer.parseInt(tokens[0]);
			int b  = Integer.parseInt(tokens[1]);
			int c  = Integer.parseInt(tokens[2]);
			graph[a].add(new int[] {b,c});
			graph[b].add(new int[] {a,c});
		}
		
		PriorityQueue<int []> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[2], o2[2]));
		pq.add(new int[] {1,1,0});
		int cnt = 0;
		while(!pq.isEmpty()) {
			int [] now = pq.poll();
			int start = now[0];
			int pos = now[1];
			int dist = now[2];
			if(v[pos])continue;
			if(start!= pos) sb.append(start).append(" ").append(pos).append("\n");
			minDist[pos] = dist;
			v[pos] = true;
			if(++cnt == n) break;
			for(int [] next: graph[pos]) {
				int np = next[0];
				int nd = next[1];
				if(!v[np] && minDist[np] > dist+nd) {
					minDist[np] = dist+nd;
					pq.add(new int[] {pos,np,dist+nd});
				}
			}
		}
		
		System.out.println(n-1);
		System.out.println(sb);
		br.close();
	}
}
