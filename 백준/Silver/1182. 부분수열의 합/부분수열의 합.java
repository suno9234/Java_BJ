import java.util.*;
import java.io.*;

public class Main {
	
	static int n,s,answer;
	static int [] nums;
	static boolean [] v;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		s = Integer.parseInt(tokens[1]);
		tokens = br.readLine().split(" ");
		nums = new int [n];
		for(int i = 0 ; i < n ; i++) {
			nums[i] = Integer.parseInt(tokens[i]);
		}
		v = new boolean[n];
		subs(0);
		System.out.println(answer);
	}
	static void subs(int cnt) {
		if(cnt == n) {
			int _sum  = 0;
			int flag = 0;
			for(int i = 0 ; i < n ;i++) {
				if(v[i]) {
					_sum+=nums[i]; 
					flag = 1;
				}
			}
			if(_sum == s && flag == 1) {
				answer++;
			}
			return;
		}
		v[cnt] = true;
		subs(cnt+1);
		v[cnt] = false;
		subs(cnt+1);
	}

}
