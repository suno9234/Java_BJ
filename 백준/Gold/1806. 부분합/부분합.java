import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n, s;
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		s = Integer.parseInt(tokens[1]);
		int [] dp = new int [n+1];
		tokens = br.readLine().split(" ");
		int start = 0;
		int end = 1;
		for(int i = 0 ; i < n ; i++) {
			dp[i+1] = dp[i]+Integer.parseInt(tokens[i]);
		}
		while(end<n+1) {
			if(dp[end] >= s) {
				break;
			}
			end++;
		}
		// end = 합이 s를 만족하는 가장 앞의 수열의 마지막 원소
		if(end == n+1) {
			System.out.println(0);
			return;
		}
		int answer = Integer.MAX_VALUE;
		int sum = dp[end];
		while(start < end && end < n+1) {
			//System.out.println(start+" "+end);
			sum = dp[end]-dp[start];
			if(sum >= s) {
				answer = Math.min(answer, end-start);
				start++;
			}else if(s > sum) {
				end++;
			}
		}
		System.out.println(answer);
		
	}

}
