import java.io.*;
import java.util.*;

public class Main {
	static int t;
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        
        t = Integer.parseInt(br.readLine());
        for(int tc = 0 ; tc < t ; tc++) {
        	tokens = br.readLine().split(" ");
        	int n = Integer.parseInt(tokens[0]);
        	int k = Integer.parseInt(tokens[1]);
        	int [] cost = new int[n+1];
        	int [] inD  = new int[n+1];
        	List<Integer>[] graph = new List[n+1];
        	
        	tokens = br.readLine().split(" ");
        	for(int i = 0 ; i < n ; i++) {
        		graph[i+1] = new ArrayList<>();
        		cost[i+1] = Integer.parseInt(tokens[i]);
        	}
        	for(int i = 0 ; i < k; i++) {
        		tokens = br.readLine().split(" ");
        		int a = Integer.parseInt(tokens[0]);
        		int b = Integer.parseInt(tokens[1]);
        		graph[a].add(b);
        		inD[b]++;
        	}
        	int win = Integer.parseInt(br.readLine());
        	PriorityQueue <int [] > pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
        	for(int i = 1 ; i < n+1; i++) {
        		if(inD[i] == 0) {
        			pq.add(new int[] { i, cost[i]});
        		}
        	}
        	int time = 0;
        	int answer = 0;
        	while(!pq.isEmpty()) {
        		int [] now = pq.poll();
        		int num = now[0];
        		int _cost = now[1];
        		answer+=_cost-time;
        		if(num == win) {
        			break;
        		}
        		time = _cost;
        		for(int next : graph[num]) {
        			inD[next]--;
        			if(inD[next]==0) {
        				pq.add(new int[] {next,cost[next]+time});
        			}
        		}
        	}
        	sb.append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
	}
}
