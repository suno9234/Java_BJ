import java.io.*;
import java.util.*;

public class Main{
	static int n,m,k;
	static int [][] oper;
	static int [][] _map;
	static boolean [] v;
	static int [] order;
	static int answer ;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		k = Integer.parseInt(tokens[2]);
		v = new boolean[k];
		order = new int[k];
		oper = new int[k][3];
		_map = new int[n][m];
		answer = Integer.MAX_VALUE;
		for(int i = 0 ; i < n ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < m ; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		for(int i =0 ; i < k ; i++) {
			tokens = br.readLine().split(" ");
			int r = Integer.parseInt(tokens[0])-1;
			int c = Integer.parseInt(tokens[1])-1;
			int s = Integer.parseInt(tokens[2]);
			oper[i] = new int[] {r,c,s};
		}
		perm(0);
		System.out.println(answer);
		
	}
	static void perm(int cnt) {
		int [][] newMap = new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			newMap[i] = Arrays.copyOf(_map[i], m);
		}// newmap에 맵 복사
		if(cnt == k) {
			for(int i = 0 ; i < k ; i++) {
				newMap = rotate(newMap, oper[order[i]][0],oper[order[i]][1],oper[order[i]][2]);
			}
			check(newMap);
			return;
		}
		for(int i = 0 ; i < k; i++) {
			if(v[i])continue;
			v[i] = true;
			order[cnt] = i;
			perm(cnt+1);
			v[i] = false;
			
		}
	}
	static void check(int [][] _map) {
		for(int i = 0 ; i < _map.length; i++) {
			int temp = 0;
			for(int j = 0 ; j < _map[0].length;j++) {
				temp+=_map[i][j];
			}
			answer = Math.min(answer, temp);
		}
	}
	static int [][] rotate(int [][] _map,int r, int c, int s) {
		if(s < 1) {
			return _map;
		}
		int temp = _map[r-s][c-s];
		for(int i = 0 ; i < s*2 ; i++) { //     위
			_map[r-s+i][c-s] = _map[r-s+i+1][c-s];
		}
		for(int i = 0 ; i < s*2 ; i++) { // 왼
			_map[r+s][c-s+i] = _map[r+s][c-s+i+1];
		}
		for(int i = 0 ; i < s*2 ; i++) { // 아래
			_map[r+s-i][c+s] = _map[r+s-i-1][c+s];
		}
		for(int i = 0 ; i < s*2 ; i++) { // 오
			_map[r-s][c+s-i] = _map[r-s][c+s-i-1];
		}
		_map[r-s][c-s+1] = temp;
		_map = rotate(_map,r,c,s-1);
		return _map;
	}

}
