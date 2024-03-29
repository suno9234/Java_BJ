import java.io.*;
import java.util.*;

public class Main{
	static int n;
    static int [][][] dp;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int [n+1][10][(1<<10)];
        //              i길이의 j로 끝나는 지금까지 v들을 방문한 계단수의 개수
        for(int i = 1 ; i < 10 ; i++) {
        	dp[1][i][1<<i] = 1;
        }
        for(int i = 1 ; i < n ; i++) {
        	for(int j = 0 ; j < 10 ; j++) {
        		for(int v = 0 ; v < (1<<10) ; v++) {
        			// dp[i+1][j+1][v방문추가]
        			// dp[i+1][j-1][v방문추가]
        			// 현재 = dp[i][j][v]
        			if(j+1 < 10) {
        				int nv = v | (1<<(j+1));
        				dp[i+1][j+1][nv] = (dp[i+1][j+1][nv] + dp[i][j][v])%1_000_000_000;
        				//System.out.printf("dp[%d][%d][%d] : %d\n",i+1,j+1,v,dp[i+1][j+1][v | (1<<(j+1))]);
        			}
        			if(j-1 >= 0) {
        				int nv = v | (1<<(j-1));
        				dp[i+1][j-1][nv] = (dp[i+1][j-1][nv] + dp[i][j][v])%1_000_000_000;
        				//System.out.printf("dp[%d][%d][%d] : %d\n",i+1,j-1,v,dp[i+1][j-1][v | (1<<(j-1))]);
        			}
        		}
        	}
        }
        
        int answer = 0;
        for(int i = 0 ; i < 10 ; i++) {
        	answer = (answer+dp[n][i][(1<<10) -1])%1_000_000_000;
        }
        System.out.println(answer);
        br.close();
	}
}
