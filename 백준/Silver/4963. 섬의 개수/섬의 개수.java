import java.io.*;
import java.util.*;

public class Main {

	static int h,w;
	static int [] dx = {0,0,-1,1,1,1,-1,-1};
	static int [] dy = {1,-1,0,0,1,-1,1,-1};
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String [] tokens = br.readLine().split(" ");
			w = Integer.parseInt(tokens[0]);
			h = Integer.parseInt(tokens[1]);
			if(w == 0 && h == 0) {
				break;
			}
			int [][] _map = new int[h][w];
			for(int i = 0 ; i < h ; i++) {
				tokens = br.readLine().split(" ");
				for(int j = 0 ; j < w ; j++) {
					_map[i][j] = Integer.parseInt(tokens[j]);
				}
			}
			int idx = 1;
			int [][] islands = new int[h][w];
			int answer = 0;
			for(int i = 0 ; i < h ; i++) {
				for(int j = 0 ; j < w ; j++) {
					if(_map[i][j] == 1 && islands[i][j] == 0) {
						answer++;
						dfs(i,j,idx++,_map,islands);
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void dfs(int x , int y , int idx , int[][]_map, int[][] islands) {
		islands[x][y] = idx;
		
		for(int i = 0 ; i <8 ; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx >=0 && ny >=0 && nx < h && ny < w && islands[nx][ny] == 0 && _map[nx][ny] == 1) {
				dfs(nx,ny,idx,_map,islands);
			}
		}
	}
}
