import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int[][] dp;
	static int[][] _map;
	static final int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		_map = new int[n][n];
		dp = new int[n][(1 << n)];
		for (int i = 0; i < n; i++) {
			String[] tokens = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		System.out.println(tsp(0, 1));

	}

	static int tsp(int idx, int v) {
		// 현재 idx에 있으면서 v만큼 방문한 최소거리
		if (v == (1 << n) - 1) {
			if (_map[idx][0] == 0)
				return INF;
			return _map[idx][0];
		}
		if (dp[idx][v] != 0) {
			return dp[idx][v];
		}
		dp[idx][v] = INF;
		for (int i = 0; i < n; i++) {
			if ((_map[idx][i] != 0) && (v & (1 << i)) == 0) {
				// idx -> i 의 경로가 있고 i번쨰 정점을 방문하지 않았으면
				dp[idx][v] = Math.min(dp[idx][v], tsp(i, v | (1 << i)) + _map[idx][i]);
				// i에 있으면서 i까지 방문한것에 i까지의 거리를 더한것
			}
		}
		return dp[idx][v];
	}

}
