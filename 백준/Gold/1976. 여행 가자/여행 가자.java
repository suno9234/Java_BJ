import java.io.*;
import java.util.*;

public class Main{
	static int n, m;
	static int[][] map;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		if(m == 1) {
			System.out.println("YES");
			return;
		}
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < n ; i++) {
			map[i][i] = 1;
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][k] * map[k][j] > 0) {
						map[i][j] = 1;
					}
				}
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		int last = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 1; i < m; i++) {
			int next = Integer.parseInt(st.nextToken()) - 1;
			if (map[last][next] == 0) {
				System.out.println("NO");
				return;
			}
			last = next;
		}

		System.out.println("YES");
		br.close();
	}
}
