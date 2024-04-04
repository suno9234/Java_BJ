import java.io.*;
import java.util.*;

public class Main{
	static int n, m, answer;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] _map;
	static int[][] result;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		_map = new int[n][m];
		result = new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			Arrays.fill(result[i], -1);
		}
		for (int i = 0; i < n; i++) {
			tokens = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		System.out.println(dfs(0,0));
//		for(int i = 0 ; i < n ; i++) {
//			System.out.println(Arrays.toString(result[i]));
//		}
		br.close();
	}

	// result[x][y] = x,y => n,m으로 가는 경우의 수
	static int dfs(int x, int y) {
		int sum = 0;
		if (x == n - 1 && y == m - 1) {
			return result[x][y] = 1;
		}
		if (result[x][y] >= 0) {
			return result[x][y];
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < n && ny >= 0 && ny < m  && _map[x][y] > _map[nx][ny] ) {
				sum += dfs(nx, ny);
			}
		}
		
		return result[x][y] = Math.max(result[x][y], sum);
	}
}
