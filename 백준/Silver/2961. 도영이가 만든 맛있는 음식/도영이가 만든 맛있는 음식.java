import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int [] s, b , temp;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens;
		n = Integer.parseInt(br.readLine());
		s = new int[n];
		b = new int[n];
		for(int i = 0 ; i < n ; i++) {
			tokens = br.readLine().split(" ");
			s[i] = Integer.parseInt(tokens[0]);
			b[i] = Integer.parseInt(tokens[1]);
		}
		subs(0,0,1);
		System.out.println(answer);
	}
	static void subs(int cnt, int _sum, int _mul) {
		if(!( _sum == 0 && _mul == 1)) {
			answer = Math.min(answer, Math.abs(_sum-_mul));
		}
		if(cnt == n ) {
			return;
		}
		subs(cnt+1,_sum+b[cnt],_mul*s[cnt]);
		subs(cnt+1,_sum,_mul);
	}
}
