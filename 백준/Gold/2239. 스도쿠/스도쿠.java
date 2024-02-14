import java.io.*;
import java.util.*;

public class Main{
	static boolean isEneded = false;
	static int [][] _map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		_map = new int[9][9];
		for(int i = 0 ; i < 9 ; i++) {
			String nl = br.readLine();
			for(int j = 0 ; j < 9 ; j++) {
				_map[i][j] = nl.charAt(j)-'0';
			}
		}
		solve(0);
		System.out.println(sb.toString());
	}
	
	static void solve(int start) {
		if(start == 81) {
			for(int i = 0 ; i < 9 ; i++) {
				for(int j = 0 ; j < 9 ; j++) {
					sb.append(_map[i][j]);
				}
				sb.append("\n");
			}
			isEneded = true;
			return;
		}
		if(_map[start/9][start%9] == 0) {
			for(int j = 1 ; j <= 9 ; j++) {
				_map[start/9][start%9] = j;
				if(check(start/9,start%9) && !isEneded ) {
					solve(start+1);
				}
				_map[start/9][start%9] = 0;
				
			}
		}else {
			solve(start+1);
		}
		
		
	}
	
	
	static boolean check(int x, int y) {
		boolean [] v = new boolean[10]; 
		for(int i = 0 ; i < 9 ; i++) {
			if(_map[x][i]>0) {
				if(v[_map[x][i]]) {
					return false;
				}else {
					v[_map[x][i]] = true;
				}
			}
		}
		v = new boolean[10];
		for(int i = 0 ; i < 9 ; i++) {
			if(_map[i][y]>0) {
				if(v[_map[i][y]]) {
					return false;
				}else {
					v[_map[i][y]] = true;
				}
			}
		}
		v = new boolean[10];
		int posX = (x/3)*3;
		int posY = (y/3)*3;
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				if(_map[posX+i][posY+j]>0) {
					if(v[_map[posX+i][posY+j]]) {
						return false;
					}else {
						v[_map[posX+i][posY+j]] = true;
					}
				}
			}
		}
		return true;
	}

}
