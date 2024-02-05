import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int [][] _map;
	static int _min = Integer.MAX_VALUE;
	static int _max = 0;
	static int answer = 0 , temp;
	static int [] dx = {0,0,-1,1};
	static int [] dy = {1,-1,0,0};
	static boolean [][] _visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		_map = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			String[] tokens = br.readLine().split(" ");
			for(int j = 0 ; j < n ; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
				_min = Math.min(_min, _map[i][j]-1);
				_max = Math.max(_max, _map[i][j]);
			}
		}
		for(int i = _min ; i <_max; i++) {
			temp = 0;
			_visited = new boolean[n][n];
			for(int j = 0 ; j < n ; j++) {
				for(int k = 0 ; k < n ; k++) {
					if(_map[j][k] - i > 0 && !_visited[j][k]) {
						_visited[j][k] = true;
						temp+=1;
						bfs(j,k,i);
					}
				}
			}
			answer = Math.max(answer, temp);
		}
		System.out.println(answer);
		
	}
	static void bfs(int x, int y,int water) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(x*n+y);
		while(!q.isEmpty()) {
			int now = q.poll();
			x = now/n;
			y = now%n;
			for(int i = 0 ; i < 4 ; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx >=0 && ny >= 0 && nx < n && ny < n && !_visited[nx][ny] && _map[nx][ny]>water) {
					_visited[nx][ny] = true;
					q.add(nx*n+ny);
				}
			}
		}
	}

}
