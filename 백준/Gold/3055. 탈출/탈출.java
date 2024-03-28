import java.util.*;
import java.io.*;

public class Main {

	static int answer;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = 2;
		for(int tc = 1; tc < test_case; tc++) {
			answer = -1;
			String [] tokens =br.readLine().split(" ");
			int n = Integer.parseInt(tokens[0]);
			int m = Integer.parseInt(tokens[1]);
			boolean [][][] v = new boolean[n][m][2];
			ArrayDeque<int []> dq = new ArrayDeque<>();
			ArrayDeque<int []> sq = new ArrayDeque<>();
			int pX=0,pY=0;
			char [][] _map = new char[n][m];
			for(int i = 0 ; i < n ; i++) {
				String s = br.readLine();
				for(int j = 0 ; j < m ; j++) {
					_map[i][j] = s.charAt(j);
					if(_map[i][j] == 'S') {
						sq.add(new int[] {i,j});
						v[i][j][0] = true;
					}else if(_map[i][j] == '*') {
						dq.add(new int[] {i,j});
						v[i][j][0] = true;
						v[i][j][1] = true;
					}else if(_map[i][j] == 'D') {
						pX = i;
						pY = j;
					}
				}
			}
			int time = 0;
			while(true) {
				ArrayDeque<int []> nDq = new ArrayDeque<>();
				ArrayDeque<int []> nSq = new ArrayDeque<>();
				while(!dq.isEmpty()) {
					int [] now = dq.poll();
					int x = now[0];
					int y = now[1];
					for(int i = 0 ; i < 4 ; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						if(nx >=0 && ny>=0 && nx < n && ny < m && !v[nx][ny][1] && _map[nx][ny]!='D' && _map[nx][ny]!= 'X') {
							v[nx][ny][0] = true;
							v[nx][ny][1] = true;
							nDq.add(new int[] {nx,ny});
						}
					}
				}
				while(!sq.isEmpty()) {
					int [] now = sq.poll();
					int x = now[0];
					int y = now[1];
					if(x == pX && y == pY) {
						answer = time;
						break;
					}
					for(int i = 0 ; i < 4 ; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						if(nx >=0 && ny>=0 && nx < n && ny < m && !v[nx][ny][0] && !v[nx][ny][1] && _map[nx][ny]!= 'X') {
							v[nx][ny][0] = true;
							nSq.add(new int[] {nx,ny});
						}
					}
				}
				if(answer > 0) {
					break;
				}
				if(nSq.isEmpty()) {
					break;
				}
				dq = nDq;
				sq = nSq;
				time++;
			}
			if(answer == -1) {
                System.out.println("KAKTUS");
			}else {
			    System.out.println(answer);
			}
		}
	}

}
