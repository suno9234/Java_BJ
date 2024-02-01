import java.util.*;
import java.io.*;

public class Solution {
	static int test_case,n,m;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static boolean [][] _visited;
	static int [][] _map;
	static int answer= 0 ;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		test_case = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc<=test_case ; tc++ ) {
			// 50 * 20 * 20 * 20 * 20
			answer = 0;
			String [] tokens = br.readLine().split(" ");
			n = Integer.parseInt(tokens[0]);
			m = Integer.parseInt(tokens[1]);
			_map = new int[n*3][n*3];
			for(int i = 0 ; i < n ; i++) {
				tokens = br.readLine().split(" ");
				for(int j = 0 ; j < n  ;j++) {
					_map[i+n][j+n] = Integer.parseInt(tokens[j]);
				}
			}
			for(int k = 1 ; k <= n+2 ; k++ ) {
				for(int i = 0 ; i < n ; i++) {
					for(int j = 0 ; j < n ; j++) {
						_visited = new boolean[n*3][n*3];
						int posX = i+n;
						int posY = j+n;
						//System.out.printf("%d %d\n",posX,posY);
						getPrice(posX,posY,k-1); // k는 가장 긴 날개의 길이
						// k-1 == n-1
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void getPrice(int x, int y,int k) {
		int cost = (k+1)*(k+1)+k*k;
		int money = 0;
		int count = 0;
		Queue <Integer> queue = new ArrayDeque<>();
		queue.add(x*(n*3)+y);
		_visited[x][y] = true;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			int posX = now/(n*3);
			int posY = now%(n*3);
		
			if(_map[posX][posY] == 1) {
				count+=1;
				money+=m;
			}
			for(int i = 0 ; i < 4 ; i++) {
				int nx = posX+dx[i];
				int ny = posY+dy[i];
				if(nx >=n && ny >= n && nx <n*2 && ny <n*2 && !_visited[nx][ny] && (Math.abs(x-nx)+Math.abs(y-ny))<= k) {
					_visited[nx][ny] = true;
					queue.add(nx*(n*3)+ny);
				}
			}
		}
		//System.out.printf("%d %d\n",count, result);
		if(money-cost >= 0 && count >answer) {
			answer = count;
		}
		
	}

}
