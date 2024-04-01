import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static int [] in,cost;
	static List<Integer> [] graph;
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        cost = new int[n+1];
        in = new int[n+1];
        graph = new List[n+1];
        for(int i = 1 ; i < n+1; i++) {
        	graph[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < n; i++) {
        	tokens = br.readLine().split(" ");
        	cost[i+1] = Integer.parseInt(tokens[0]);
        	for(int j = 1 ; j < tokens.length-1; j++) {
        		int now = Integer.parseInt(tokens[j]);
        		in[i+1]++;
        		graph[now].add(i+1);
        	}
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
        for(int i = 1 ; i < n+1 ; i++) {
        	if(in[i] == 0) {
        		pq.add(new int[] {i,cost[i]});
        	}
        }
        int [] answer = new int[n+1];
        while(!pq.isEmpty()) {
        	int [] now = pq.poll();
        	int num = now[0];
        	int cos = now[1];
        	answer[num] = cos;
        	for(int next : graph[num]) {
        		in[next]--;
        		if(in[next] == 0) {
        			pq.add(new int[] {next,cost[next]+cos});
        		}
        	}
        }
        for(int i = 1 ; i < n+1; i++)  sb.append(answer[i]).append("\n");
        System.out.println(sb);
        br.close();
	}
}
