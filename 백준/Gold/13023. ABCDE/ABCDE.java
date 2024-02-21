import java.io.*;
import java.util.*;

public class Main{
	static int n,m,answer;
	static boolean [] v;
    static List<Integer>[]_map;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String [] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        _map = new List[n];
        for(int i = 0 ; i < n ; i++) {
        	_map[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < m ; i++) {
        	tokens = br.readLine().split(" ");
        	int a = Integer.parseInt(tokens[0]);
        	int b = Integer.parseInt(tokens[1]);
        	_map[a].add(b);
        	_map[b].add(a);
        }
        for(int i = 0 ; i < n ; i++) {
        	if(answer == 1) {
        		break;
        	}
        	v = new boolean[n];
        	dfs(i,1);
        }
        System.out.println(answer);
        br.close();
	}
	static void dfs(int x, int cnt) {
		v[x] = true;
		if(cnt == 5) {
			answer = 1;
			return;
		}
		List <Integer> nexts = _map[x];
		for(int next : nexts) {
			if(v[next]) continue;
			dfs(next,cnt+1);
		}
		v[x] = false;
		
	}
}
