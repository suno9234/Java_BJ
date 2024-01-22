
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [] t = new int [n];
		int [] p = new int [n];
		int [] dp = new int [n+1];
		for(int i =  0; i < n ; i++) {
			String []tokens = br.readLine().split(" ");
			t[i] = Integer.parseInt(tokens[0]);
			p[i] = Integer.parseInt(tokens[1]);
		}
		for(int i = 0 ; i < n ; i++) {
			// 오늘 걸리는 일 시간 = t[i] 값 = p[i]
			int _max = 0;
			for(int j = 0 ; j <= i; j++) {
				if(_max < dp[j]) {
					_max = dp[j];
				}
			}
			if(i+t[i] < n+1) {
				dp[i+t[i]] = Math.max(dp[i+t[i]], _max+p[i]);
			}
		}
		int answer = 0;
		for(int d : dp) {
			if(d > answer) answer = d;
		}
		System.out.println(answer);
		//System.out.println(Arrays.toString(dp));
	}

}
