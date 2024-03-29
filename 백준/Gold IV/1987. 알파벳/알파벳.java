import java.util.*;
import java.io.*;

public class Main{

	static int r,c,answer;
	static char [][] _map;
	static boolean [][] v;
	static int mask;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		r = Integer.parseInt(tokens[0]);
		c = Integer.parseInt(tokens[1]);
		_map = new char[r][c];
		v = new boolean[r][c];
		mask = 0;
		for(int i = 0 ; i < r ; i++) {
			String nl = br.readLine();
			for(int j = 0 ; j < c ; j++) {
				_map[i][j] = nl.charAt(j); 
			}
		}
		dfs(0,0,1);
		System.out.println(answer);
	}
	static void dfs(int x , int y,int cnt) {
		v[x][y] = true;
		mask |= (1 << (_map[x][y]-'A'));
		answer = Math.max(answer, cnt);
		for(int i = 0 ; i < 4 ; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx >=0 && ny >=0 && nx <r && ny < c && !v[nx][ny] && ((mask >> (_map[nx][ny]-'A')) & 1) == 0) {
				dfs(nx,ny,cnt+1);
				v[nx][ny] = false;
				mask ^= (1<< (_map[nx][ny]-'A'));
			}
		}
	}

}
