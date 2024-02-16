import java.io.*;
import java.util.*;

public class Main{

	static int [][] dp;
	static String s1, s2;
	static ArrayDeque<Character> s;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		s1 = br.readLine();
		s2 = br.readLine();
		s = new ArrayDeque<>();
		dp = new int[s1.length()+1][s2.length()+1];
		for(int i = 1 ; i < s1.length()+1; i++) {
			for(int j = 1 ; j < s2.length()+1;j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		getAnswer(s1.length(),s2.length());
		while(!s.isEmpty()) {
			sb.append(s.pop());
		}
		System.out.println(sb.toString().length());
		System.out.println(sb.toString());
		
	}
	static void getAnswer(int x , int y) {
		if(x == 0 && y == 0) return;
		if(x > 0 && dp[x-1][y] == dp[x][y]) {
			getAnswer(x-1,y);
		}else if(y > 0 && dp[x][y-1] == dp[x][y]) {
			getAnswer(x,y-1);
		}else if(x >0 && y > 0){
			s.push(s2.charAt(y-1));
			getAnswer(x-1,y-1);
		}
	}
	

}
