import java.util.*;
import java.io.*;

public class Main {

	static int n,m,d,answer,temp;
	static int [][] _map;
	static int [] dx = {0,-1,0};
	static int [] dy = {-1,0,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		d = Integer.parseInt(tokens[2]);
		_map = new int[n][m];
		for(int i = 0 ; i < n; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < m ; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		for(int p = 0 ; p < m-2 ;p++) {
			for(int q = p+1 ; q < m-1; q++) {
				for(int r = q+1 ; r < m ; r++) {
					// p q r = 궁수가 있는 좌표
					temp = 0;
					int [][] _copyedMap = new int[n][m];
					for(int i = 0 ; i <n;i++) {
						_copyedMap[i] = Arrays.copyOf(_map[i], m);
					}
					for(int i = 0 ; i < n ; i++) {
						shoot(p, q, r, _copyedMap);
						move(i,_copyedMap);  // O(n*m)
					}
					answer = Math.max(answer,temp);
					
				}
			}
		}
		System.out.println(answer);
	}
	static void shoot(int p, int q, int r , int[][] _copymap) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean [][] _visited = new boolean[n][m];
		int [] kill = new int[3];
		Arrays.fill(kill,-1);
		
		queue.add((n-1)*m+p);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			int x = now / m;
			int y = now % m;
			if(_copymap[x][y] == 1) {
				kill[0] = x*m+y;
				break;
			}
			for(int i = 0 ; i < 3 ; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx >= 0 && ny >=0 && nx <n && ny <m && !_visited[nx][ny] && (Math.abs(p-ny)+Math.abs(n-nx)) <= d ){
					_visited[nx][ny] = true;
					queue.add(nx*m+ny);
				}
			}
		}
		
		queue = new ArrayDeque<>();
		_visited = new boolean[n][m];
		queue.add((n-1)*m+q);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			int x = now / m;
			int y = now % m;
			if(_copymap[x][y] == 1) {
				kill[1] = x*m+y;
				break;
			}
			for(int i = 0 ; i < 3 ; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx >= 0 && ny >=0 && nx <n && ny <m && !_visited[nx][ny] && (Math.abs(q-ny)+Math.abs(n-nx)) <= d ){
					_visited[nx][ny] = true;
					queue.add(nx*m+ny);
				}
			}
		}
		
		queue = new ArrayDeque<>();
		_visited = new boolean[n][m];
		queue.add((n-1)*m+r);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			int x = now / m;
			int y = now % m;
			if(_copymap[x][y] == 1 ) {
				kill[2] = x*m+y;
				break;
			}
			for(int i = 0 ; i < 3 ; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx >= 0 && ny >=0 && nx <n && ny <m && !_visited[nx][ny] && (Math.abs(r-ny)+Math.abs(n-nx)) <= d ){
					_visited[nx][ny] = true;
					queue.add(nx*m+ny);
				}
			}
		}
		for(int i = 0 ; i < 3 ; i++) {
			if( kill[i]>= 0 &&_copymap[kill[i]/m][kill[i]%m] == 1) {
				_copymap[kill[i]/m][kill[i]%m] = 0;
				temp++;
			}
		}
		
		
		
	}
	static void move(int cnt,int [][] _copymap) {
		
		for(int i = n-1 ; i > cnt; i--) {
			for(int j = 0 ; j < m ; j++) {
				_copymap[i][j] = _copymap[i-1][j];
			}
		}
		for(int j = 0 ; j < m ; j++) {
			_copymap[cnt][j] = 0;
		}
	}
	
}
