import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAX_VALUE = 9_9999_9999;
	static int answer;
	static int[][] graph;
	static boolean[] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = 2;
		for (int tc = 1; tc < test_case; tc++) {
			answer = 0;
			String [] tokens = br.readLine().split(" ");
			int n = Integer.parseInt(tokens[0]);
			int m = Integer.parseInt(tokens[1]);
			graph = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					graph[i][j] = MAX_VALUE;
				}
			}
			for (int i = 0; i < m; i++) {
				tokens = br.readLine().split(" ");
				int a = Integer.parseInt(tokens[0]) - 1;
				int b = Integer.parseInt(tokens[1]) - 1;
				graph[a][b] = 1;
				// a < b
			}
			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (graph[i][k] != MAX_VALUE && graph[k][j] != MAX_VALUE
								&& graph[i][j] > graph[i][k] + graph[k][j]) {
							graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}
			for (int i = 0; i < n; i++) {
				int cnt = 0 ;
				for(int j = 0 ; j < n ; j++) {
					if(graph[i][j] < MAX_VALUE) {
						cnt++;
					}
					if(graph[j][i] < MAX_VALUE) {
						cnt++;
					}
				}
				if(cnt == n-1) {
					answer++;
				}
			}
			sb.append(answer);
		}
		System.out.println(sb.toString());
	}
}
