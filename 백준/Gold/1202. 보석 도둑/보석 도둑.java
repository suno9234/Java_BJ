import java.io.*;
import java.util.*;

public class Main{
	static class Jewel implements Comparable<Jewel>{
		int weight;
		int value;
		public Jewel(int w, int v) {
			this.weight = w;
			this.value = v;
		}
		@Override
		public int compareTo(Jewel o) {
			if(value == o.value) {
				return Integer.compare(weight, o.weight);
			}
			return Integer.compare(-value, -o.value);
		}
		
	}
	static int n,k;
	static TreeMap<Integer,Integer> c;
	static int [] result;
	static boolean [] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);	
        k = Integer.parseInt(tokens[1]);
        c = new TreeMap<>();
        result = new int[k];
        v = new boolean[k];
        PriorityQueue<Jewel> pq = new PriorityQueue<>(); 
        for(int i = 0 ; i < n ; i++) {
        	tokens = br.readLine().split(" ");
        	int m = Integer.parseInt(tokens[0]);
        	int v = Integer.parseInt(tokens[1]);
        	pq.add(new Jewel(m,v));
        }
        for(int i = 0 ; i < k ; i++) {
        	int now = Integer.parseInt(br.readLine());
        	if(c.get(now) == null) {
        		c.put(now,1);
        	}else {
        		c.put(now, c.get(now)+1);
        	}
        }
        long answer = 0;
        while(!pq.isEmpty()) {
        	Jewel now = pq.poll();
        	int value = now.value;
        	int weight =now.weight;
        	// 가장 가치가 크고
        	// 가치가 같다면 가장 가벼운놈
        	Integer bigger = c.ceilingKey(weight);
        	if(bigger != null) {
        		answer+=value;
        		int cnt = c.get(bigger);
        		if(cnt == 1) {
        			c.remove(bigger);
        		}else {
        			c.put(bigger, cnt-1);
        		}
        	}
        }
        System.out.println(answer);
        br.close();
	}
}
