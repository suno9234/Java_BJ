import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int [][] _map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		_map = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			String nl = br.readLine();
			for(int j = 0 ; j < n ; j++) {
				_map[i][j] = nl.charAt(j)-'0';
			}
		}
		System.out.println(quadTree(0,0,n));
		
	}
	static String quadTree(int x , int y , int n) {
		StringBuilder sb = new StringBuilder();
		int start = _map[x][y];
		int flag=  0;
		for(int i = 0 ; i < n ; i++) {
			if(flag== 1) break;
			for(int j = 0 ; j < n ; j++) {
				if(_map[x][y] != _map[x+i][y+j]) {
					flag = 1;
					break;
				}
			}
		}
		
		if(flag == 0) {
			sb.append(start);
		}else {
			sb.append("(");
			sb.append(quadTree(x,y,n/2));
			sb.append(quadTree(x,y+n/2,n/2));
			sb.append(quadTree(x+n/2,y,n/2));
			sb.append(quadTree(x+n/2,y+n/2,n/2));
			sb.append(")");
		}
		return sb.toString();
	}

}
