import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean [][] v;
	static int[][] _map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		_map = new int[n][n];
		for (int i = 0; i < n; i++) {
			tokens = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		int start = 0;
		int end = 200;
		int answer = 0;
		while (start <= end) {
			int mid = (start + end) / 2;
			boolean isPossible = false;
			for(int i = 0 ; i <= mid ; i++) {
				v = new boolean[n][n];
				if(dfs(0,0,_map[0][0]-i,_map[0][0]+mid-i)) {
					isPossible = true;
					break;
				}
			}
			if(isPossible) {
				// 갈 수 있으면 줄이기
				answer = mid;
				end = mid-1;
			}else {
				start = mid+1;
			}
		}
		System.out.println(answer);
	}

	static boolean dfs(int x,int y,int min,int max) {
		v[x][y] = true;
		int now = _map[x][y];
		if(max < now || min > now)  {
			return false;
		}
		if(x == n-1 && y == n-1) {
			return true;
		}
		for(int i = 0 ; i < 4 ; i++) {
			int nx = x+ dx[i];
			int ny = y+dy[i];
			if(nx >=0 && ny >=0 && nx < n && ny <n && !v[nx][ny]) {
				if (dfs(nx,ny,min,max)) {
					return true;
				}
			}
		}
		return false;
	}

}
