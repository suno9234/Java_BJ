import java.util.*;
import java.io.*;

public class Main{
	static boolean isEnd = false;
	static int r,c,answer;
	static int [][] _map;
	static int [] dx = {-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		r = Integer.parseInt(tokens[0]);
		c = Integer.parseInt(tokens[1]);
		_map = new int[r][c];
		for(int i = 0 ; i < r ; i++) {
			String nl = br.readLine();
			for(int j = 0 ; j < c ; j++) {
				char now = nl.charAt(j);
				if(now == '.') {
					_map[i][j] = 0;
				}else if(now == 'x') {
					_map[i][j] = -1;
				}
			}
		}
		for(int i = 0 ; i < r ; i++) {
			isEnd = false;
			dfs(i,0,i+1);
		}
		System.out.println(answer);
	}
	static void dfs(int x, int y,int num) {
		_map[x][y] = num;
		if(y == c-1) {
			answer++;
			isEnd = true;
			return;
		}
		for(int i = 0 ; i < 3; i++) {
			int nx = x+dx[i];
			int ny = y+1;
			if(nx >=0 && ny>=0 && nx <r && ny <c && _map[nx][ny] == 0 && !isEnd) {
				dfs(nx,ny,num);
			}
		}
	}
}
