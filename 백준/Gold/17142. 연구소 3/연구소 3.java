import java.io.*;
import java.util.*;

public class Main {

	static int answer = Integer.MAX_VALUE;
	static int n,m;
	static int [][] _map ;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};

	static int [] virus ;
	static int [] virusInfo;
	static int totalVirus = 0;
	static int time;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		_map = new int[n][n];
		virus = new int[10];
		
		for(int i = 0 ; i< n ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0; j < n ; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
				if(_map[i][j] == 2) {
					virus[totalVirus++] = i*n+j;
				}
			}
		}
		virusInfo = new int[m];
		comb(0,0);
		if(answer == Integer.MAX_VALUE ) {
			System.out.println(-1);
		}else{
			System.out.println(answer); 
		}
		
	}
	public static void comb(int start,int count) {
		if(count == m) {
			bfs();
			return ;
		}
		for(int i = start; i< totalVirus; i++) {
			virusInfo[count] = virus[i];
			comb(i+1,count+1);
			
		}
	}
	public static void bfs() {
		int flag = 0;
		for(int i = 0 ; i < n ; i++) {
			if (flag == 1) {
				break;
			}
			for(int j = 0 ; j < n ; j++) {
				if(_map[i][j] == 0) {
					flag = 1;
					break;
				}
			}
		}
		if(flag == 0) {
			answer = 0;
			return;
		}
		boolean [][] _visited = new boolean[n][n] ;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int v : virusInfo) {
			q.add(v);
			_visited[v/n][v%n] = true;
		}
		time = 0;
		while(!q.isEmpty()) {
			// 다음 큐에 돌릴게 없으면 아웃
			ArrayDeque<Integer> nextQ = new ArrayDeque<>();
			while(!q.isEmpty()) {
				// 현재 들어잇는 놈들로 다음 큐 구성
				int now = q.poll();
				_visited[now/n][now%n] = true;
				for(int i = 0 ; i < 4 ; i++) {
					int nx = now/n+dx[i];
					int ny = now%n+dy[i];
					if(nx >=0 && ny >=0 && nx <n && ny <n && _map[nx][ny]!=1 && !_visited[nx][ny] ) {
						_visited[nx][ny] = true;
						nextQ.add(nx*n+ny);
					}
				}
			}
			time++;
			q = nextQ;
			flag = 0;
			for(int i = 0 ; i < n ; i++) {
				if(flag == 1) {
					break;
				}
				for(int j = 0 ; j < n ; j++) {
					if(!_visited[i][j] && _map[i][j] == 0) {
						flag = 1;
						break;
					}
				}
			}
			if(flag == 0) {
				answer = Math.min(answer, time);
				break;
			}
			
		}
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if( !_visited[i][j] && _map[i][j] == 0) {
					// (i,j)에 대하여 아직 방문하지 않았고, (i,j)가 빈공간이라면
					return;
				}
			}
		}
		// 꽉 찬 경우만
		answer = Math.min(time, answer);
	}
}
