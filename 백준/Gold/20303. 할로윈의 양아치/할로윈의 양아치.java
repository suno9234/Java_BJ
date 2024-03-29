import java.io.*;
import java.util.*;

public class Main{
	static int n,m,k;
	static int [] parent;
    static int [] candy;
	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        k = Integer.parseInt(tokens[2]);
        candy = new int[n+1];
        parent = new int[n+1];
        Arrays.setAll(parent, x->x);
        
        tokens = br.readLine().split(" ");
        for(int i = 0 ; i < n ; i++) {
        	candy[i+1] = Integer.parseInt(tokens[i]);
        }
        for(int i = 0 ; i < m ; i++) {
        	tokens = br.readLine().split(" ");
        	int a = Integer.parseInt(tokens[0]);
        	int b = Integer.parseInt(tokens[1]);
        	union(a,b);
        }
        HashMap<Integer, int []> hm = new HashMap<>();
        for(int i = 1 ; i < n+1 ; i++) {
        	int pi = getParent(i);
        	if(hm.get(pi)== null) {
        		hm.put(pi, new int[] {1, candy[i]});
        	}else {
        		int [] now = hm.get(pi);
        		int cnt = now[0];
        		int value = now[1];
        		hm.put(pi, new int[] {cnt+1,value+candy[i]});
        	}
        }
        int idx = 0;
        int [][] zips = new int[hm.size()][2];
        for(int key:  hm.keySet()) {
        	zips[idx++] = hm.get(key);
        }
        int [][] dp = new int[hm.size()+1][k];
        for(int i = 1; i < hm.size()+1;i++) {
        	int w = zips[i-1][0];
        	int v = zips[i-1][1];
        	for(int j = 0 ; j < k ; j++) {
        		// i= 인덱스 , j = 무게
        		if(j-w >=0) {
        			dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w]+v);
        		}else {
        			dp[i][j] = dp[i-1][j];
        		}
        	}
        }
        System.out.println(dp[idx][k-1]);
        br.close();
	}
	static void union(int a , int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		if(pa < pb) {
			parent[pb] = pa;
		}else if(pb < pa) {
			parent[pa] = pb;
		}
	}
	static int getParent(int a) {
		if(parent[a] == a) {
			return a;
		}
		return parent[a] = getParent(parent[a]);
	}
}
