import java.io.*;
import java.util.*;

public class Main {
	static int v,e;
	static List<int[]>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        
        v = Integer.parseInt(tokens[0]);
        e = Integer.parseInt(tokens[1]);
        graph = new List[v+1];
        for(int i =1 ; i < v+1 ; i++) {
        	graph[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < e ; i++) {
        	tokens = br.readLine().split(" ");
        	int a = Integer.parseInt(tokens[0]);
        	int b = Integer.parseInt(tokens[1]);
        	int cost = Integer.parseInt(tokens[2]);
        	graph[a].add(new int[] {b,cost});
        	graph[b].add(new int[] {a,cost});
        }
        PriorityQueue<int [] >pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
        boolean [] visited = new boolean[v+1];
        int max = 0;
        int result = 0;
        int cnt = 0;
        pq.add(new int[] {1,0});
        while(!pq.isEmpty()) {
        	int [] now = pq.poll();
        	int pos = now[0];
        	int cost = now[1];
        	if(visited[pos])continue;
        	visited[pos] = true;
        	result+=cost;
        	max = Math.max(max, cost);
        	cnt++;
        	if(cnt == v) break;
        	for(int [] next : graph[pos]) {
        		int np = next[0];
        		int nc = next[1];
        		if(!visited[np]) {
        			pq.add(new int[] {np,nc});
        		}
        	}
        }
        System.out.println(result-max);
        br.close();
	}
}
