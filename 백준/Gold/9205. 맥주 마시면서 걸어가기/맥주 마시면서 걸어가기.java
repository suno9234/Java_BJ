import java.util.*;
import java.io.*;

public class Main {
	static int test_case,n;
	static List<int []> coors;
	static List<Integer>[] graph;
	static boolean [] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); 
		test_case = Integer.parseInt(st.nextToken());
		for(int tc = 0 ; tc < test_case ; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			coors = new ArrayList<>();
			graph = new List[n+2];
			v = new boolean[n+2];
			for(int i = 0 ; i < n+2 ; i++) {
				graph[i] = new ArrayList<>();
			}
			for(int i = 0 ; i < n+2 ; i++) {
				st= new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				coors.add(new int[] {x,y});
				for(int j = 0 ; j < i; j++) {
					int [] next = coors.get(j);
					int nx = next[0];
					int ny = next[1];
					if(Math.abs(x-nx)+Math.abs(y-ny) <= 1000) {
						graph[i].add(j);
						graph[j].add(i);
					}
				}
				
			}
			if(dfs(0)) {
				sb.append("happy\n");
			}else {
				sb.append("sad\n");
			}	
		}
		System.out.println(sb);
	}	
	static boolean dfs(int x) {
		v[x] = true;
		if(x == n+1) {
			return true;
		}
		boolean answer = false;
		for(int next : graph[x]) {
			if(!v[next]) {
				answer = dfs(next);
				if(answer) {
					return answer;
				}
			}
		}
		return answer;
	}
}
