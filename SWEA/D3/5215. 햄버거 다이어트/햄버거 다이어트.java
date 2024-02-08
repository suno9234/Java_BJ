import java.util.*;
import java.io.*;


public class Solution{

	static int n, l;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens ;
		int test_case = Integer.parseInt(br.readLine())+1;
		for(int tc = 1 ; tc < test_case; tc++) {
			tokens = br.readLine().split(" ");
			int n = Integer.parseInt(tokens[0]);
			int l = Integer.parseInt(tokens[1]);
			int [][] dp = new int[n+1][l+1];
			for(int i = 1 ; i < n+1 ; i++) {
				tokens = br.readLine().split(" ");
				int t = Integer.parseInt(tokens[0]);
				int k = Integer.parseInt(tokens[1]);
				for(int j = 1 ; j < l+1 ; j++) {
					if(j < k) {
						dp[i][j] = dp[i-1][j];
					}else {
						dp[i][j] = Math.max(dp[i-1][j-k]+t, dp[i-1][j]);
					}
				}
			}
			// i = 현재 음식 번호 j = 현재 총 칼로리
			// dp[i][j] = i번째 음식을 넣을 때 총 칼로리로 만들수 있는 최대의 value의 합
			// i-1번째까지 음식을 넣은 것 중에 현재 총 칼로리-내 칼로리(내가 넣을 수 있는 것)의 value + value[i]
			// dp[i][j] = i-1번째 음식을 넣을때 j-kal[i]로 만들수 있는 최대의 taste + taste[i]
			sb.append("#").append(tc).append(" ").append(dp[n][l]).append("\n");
			
		}
		System.out.println(sb.toString());
		
	}

}
