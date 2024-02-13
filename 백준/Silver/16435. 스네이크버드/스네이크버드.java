import java.util.*;
import java.io.*;

public class Main{
	static int n,l;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		int [] h ;
		n = Integer.parseInt(tokens[0]);
		l = Integer.parseInt(tokens[1]);
		h = new int[n];
		tokens  = br.readLine().split(" ");
		for(int i = 0 ; i < n ; i++) {
			h[i] = Integer.parseInt(tokens[i]);
		}
		Arrays.sort(h);
		int idx = 0;
		while(idx < n) {
			if ( l >= h[idx++]) {
				l++;
			}else {
				break;
			}
		}
		System.out.println(l);
		
	}

}
