import java.util.*;
import java.io.*;

public class Main {
	static int n,m;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static int [][] map;
	static boolean [][] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		v = new boolean[m][n];
		int total = n*m;
		int cnt = 0;
		ArrayDeque<int [] > q = new ArrayDeque<>();
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.add(new int[] {i,j,0});
					v[i][j] = true;
					cnt++;
				}else if(map[i][j] == -1) {
					total--;
				}
			}
		}
		int answer = 0;
		while(!q.isEmpty()) {
			int [] now = q.poll();
			int x = now[0];
			int y = now[1];
			int d = now[2];
			for(int i = 0 ; i <4 ; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>=0 && ny >=0 && nx < m && ny < n && !v[nx][ny] && map[nx][ny] == 0) {
					v[nx][ny] = true;
					answer = d+1;
					cnt++;
					q.add(new int[] {nx,ny,d+1});
				}
			}
		}
		if(cnt == total) {
			System.out.println(answer);
		}else {
			System.out.println(-1);
		}
		
	}

}
