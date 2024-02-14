import java.util.*;
import java.io.*;

public class Main{
	static int n,r,c;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		r = Integer.parseInt(tokens[1]);
		c = Integer.parseInt(tokens[2]);
		solve(0,0,(int)Math.pow(2, n),0);
	}

	static int solve(int x , int y, int n ,int idx) {
		int temp = idx;
		if(n == 2) {
			if(x == r && y == c) {
				System.out.println(temp);
				System.exit(0);
			}
			temp++;
			if(x == r && y+1 == c) {
				System.out.println(temp);
				System.exit(0);
			}
			temp++;
			if(x+1 == r && y == c) {
				System.out.println(temp);
				System.exit(0);
			}
			temp++;
			if(x+1 == r && y+1 == c) {
				System.out.println(temp);
				System.exit(0);
			}
			temp++;
			return idx;
		}
		
		if(r< x+ (n/2) && c < y+(n/2)) {
			return solve(x,y,n/2,idx);
		}else if(r < x+(n/2)) {
			return solve(x,y+n/2,n/2,idx+(n/2)*(n/2));
		}else if(c < y+(n/2)) {
			return solve(x+n/2,y,n/2,idx+(n/2)*(n/2)*2);
		}else {
			return solve(x+n/2,y+n/2,n/2,idx+(n/2)*(n/2)*3);
		}
		
	}
}
