import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		int n = Integer.parseInt(tokens[0]);
		int k = Integer.parseInt(tokens[1]);
		int [] weight = new int[n+1];
		int [] value = new int[n+1];
		int [][] dp = new int[n+1][k+1];
		for(int i = 1 ; i < n+1 ; i++) {
			tokens = br.readLine().split(" ");
			int w = Integer.parseInt(tokens[0]);
			int v = Integer.parseInt(tokens[1]);
			weight[i] = w;
			value[i] = v;
		}
		for(int i = 1 ; i < n+1; i++) {
			for(int j = 1; j < k+1;j++ ) {
				if(weight[i]> j) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]]+value[i]);
				}
					
			}
		}
		System.out.println(dp[n][k]);
	}

}
