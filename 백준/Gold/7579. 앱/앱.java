import java.io.*;
import java.util.*;

public class Main{
	static int n, m;
	static int[] A, c;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		A = new int[n + 1];
		c = new int[n + 1];
		int sumC = 0;
		tokens = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			A[i + 1] = Integer.parseInt(tokens[i]);
		}
		tokens = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			c[i + 1] = Integer.parseInt(tokens[i]);
			sumC += c[i + 1];
		}
		dp = new int[n + 1][sumC + 1];
		// dp[x][y] = x번째 a를 넣을때 y비용 이하인 것들의 최대 메모리 값
		// a1..a2..a3..의 합 >= m 인 것들 중
		// c1..c2..c3..의 최솟값
		// j=앱 번호 i = 비용 dp[j][i] = 메모리
		int answer = Integer.MAX_VALUE;
		for (int j = 1; j < n + 1; j++) {
			for (int i = 0; i < sumC + 1; i++) {
				if (i - c[j] >= 0) {
					//
					dp[j][i] = Math.max(dp[j - 1][i], dp[j - 1][i - c[j]] + A[j]);
					// 나를 안넣은 것과 나를 넣은 것
					if (dp[j][i] >= m) {
						answer = Math.min(i, answer);
					}
				}else {
					dp[j][i] = dp[j-1][i];
				}
			}
		}
		System.out.println(answer);
		br.close();
	}
}
