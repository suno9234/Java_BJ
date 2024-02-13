import java.io.*;
import java.util.*;

public class Solution{

	static int n,answer;
	static int [][] _map;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/1249input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine())+1;
		for(int tc = 1; tc < test_case; tc++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());
			_map = new int[n][n];
			for(int i = 0 ; i < n ; i++) {
				String  tokens = br.readLine();
				for(int j = 0 ; j < n ; j++) {
					_map[i][j] = tokens.charAt(j)-'0';
				}
			}
			boolean [][] v = new boolean[n][n];
			int [] dist = new int[n*n];
			for(int i = 0 ; i < n ; i++) {
				Arrays.fill(dist, Integer.MAX_VALUE);
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				public int compare(int [] a , int [] b) {
					return a[2]-b[2];
				}
			});
			
			pq.offer(new int[] {0,0,0});
			while(!pq.isEmpty()) {
				int [] now = pq.poll();
				int x = now[0];
				int y = now[1];
				int d = now[2];
				v[x][y] = true;
				if(dist[x*n+y] <= d ) {
					continue;
				}
				int _minDist = Integer.MAX_VALUE;
				for(int i = 0 ; i < 4 ; i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					if( nx >=0 && ny >=0 && nx < n && ny < n && dist[nx*n+ny]<Integer.MAX_VALUE) {
						_minDist = Math.min(_minDist, dist[nx*n+ny]);
					}
				}
				dist[x*n+y] = Math.min(dist[x*n+y],_minDist+d); 
				dist[0] = 0;
				for(int i = 0 ; i < 4 ; i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					if( nx >=0 && ny >=0 && nx < n && ny < n && dist[x*n+y]+_map[nx][ny] < dist[nx*n+ny]) {
						pq.offer(new int[] {nx,ny,_map[nx][ny]});
					}
				}
				
			}
//			for(int i = 0 ; i < n ; i++) {
//				System.out.println(Arrays.toString(dist));
//			}
			answer = dist[n*n-1];
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
