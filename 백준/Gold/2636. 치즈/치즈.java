import java.util.*;
import java.io.*;
public class Main{
	
	static int n,m;
	static int [][] _map;
	static boolean [][] v;
	static int last,total;
	static int start;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static ArrayDeque<int[]> toDelete;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		_map = new int[n][m];
		v = new boolean[n][m];
		last = n*m;
		for(int i = 0 ; i < n ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < m ; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
				if(_map[i][j] == 1) {
					total++;
				}
			}
		}
		
		// start = (0,0)-> 가장자리가 항상 비어있음
		int time = 0;
		while(total> 0) {
			last = 0;
			toDelete = new ArrayDeque<>();
			v = new boolean[n][m];
			dfs(0,0);
			for(int [] coor : toDelete) {
				_map[coor[0]][coor[1]] = 0;
				total--;
				last++;
			}
			time++;
		}
		sb.append(time).append("\n").append(last);
		System.out.println(sb.toString());
	}
	static void dfs(int x , int y) {
		v[x][y] = true;
		
		for(int i = 0 ; i < 4 ; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=0 && ny >= 0 && nx < n && ny < m && !v[nx][ny]) {
				if(_map[nx][ny] == 1) {
					v[nx][ny] = true;
					toDelete.add(new int[] {nx,ny});
				}else {
					dfs(nx,ny);
				}
			}
		}
	}
}
