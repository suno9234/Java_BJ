import java.io.*;
import java.util.*;

public class Main{

	static int n, m;
	static int[][] _map;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static int [][][] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		_map = new int[n][m];
		dist = new int[n][m][2];
		for (int i = 0; i < n; i++) {
			String nl = br.readLine();
			for (int j = 0; j < m; j++) {
				_map[i][j] = nl.charAt(j)-'0';
				for(int k  =0 ; k < 2 ; k++) {
					dist[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		ArrayDeque<int [] > queue = new ArrayDeque<>();
		queue.add(new int[] {0,0,1,0});
		dist[0][0][0] = 1;
		
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			int x = now[0];
			int y = now[1];
			int _dist = now[2];
			int cnt = now[3];
			for(int i = 0 ; i < 4 ; i++) {
				int nx = x+ dx[i];
				int ny = y +dy[i];
				if(nx>=0 && ny >=0 && nx <n && ny < m && dist[nx][ny][cnt]== Integer.MAX_VALUE) {
					if(_map[nx][ny] == 0) {
						// 다음에 갈 공간이 빈공간이면 그냥 go
						dist[nx][ny][cnt] = _dist+1;
						queue.add(new int[] {nx,ny,_dist+1,cnt});
					}else if(cnt == 0) {
						//
						// 다음에 갈 공간이 빈공간이 아니고 아직 벽을 안부쉈을때
						dist[nx][ny][1] = _dist+1;
						queue.add(new int[] {nx,ny,_dist+1,1});
					}
				}
			}
		}
		int answer = Math.min(dist[n-1][m-1][0], dist[n-1][m-1][1]);
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
	}

}
