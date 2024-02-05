import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int [][] _map;
	static int [] dx = {0,0,-1,1};
	static int [] dy = {1,-1,0,0};
	static boolean [][] _visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		_map = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			String tokens = br.readLine();
			for(int j = 0 ; j < n ; j++) {
				_map[i][j] = (tokens.charAt(j))-'0';
			}
		}
		ArrayList<Integer> answer = new ArrayList<>();
		_visited = new boolean[n][n];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if(_map[i][j] == 1 && !_visited[i][j]) {
					_visited[i][j] = true;
					answer.add(bfs(i,j));
				}
			}
		}
		Collections.sort(answer);
		StringBuilder sb = new StringBuilder();
		sb.append(answer.size()).append("\n");
		for(Integer n : answer) {
			sb.append(n).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	static int bfs(int x, int y) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(x*n+y);
		int _size = 0;
		while(!q.isEmpty()) {
			int now = q.poll();
			x = now/n;
			y = now%n;
			_size++;
			for(int i = 0 ; i < 4 ; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx >=0 && ny >= 0 && nx < n && ny < n && !_visited[nx][ny] && _map[nx][ny] > 0 ) {
					_visited[nx][ny] = true;
					q.add(nx*n+ny);
				}
			}
		}
		return _size;
	}

}
