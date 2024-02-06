import java.util.*;
import java.io.*;

public class Main{
	static int n,m,r;
	static int [][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		r = Integer.parseInt(tokens[2]);
		arr = new int[n][m];
		int _min = Math.min(n, m)/2;
		for(int i = 0 ; i < n ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < m ; j++) {
				arr[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		for(int i = 0 ; i < _min ; i++ ) {
			turn(i,r%(2*(m+n)-8*i-4), n-i*2,m-i*2);
		}
		for(int i = 0 ; i < n;i++) {
			for(int j = 0 ; j <m ; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static void turn(int startPos,int round,int h, int v) {
		for(int r = 0 ; r < round; r++) {
			int temp = arr[startPos][startPos];
			for(int i = 0 ; i < v-1 ; i++) {
				arr[startPos][startPos+i] = arr[startPos][startPos+i+1];
			}
			for(int i = 0 ; i < h-1 ; i++) {
				arr[startPos+i][startPos+v-1] = arr[startPos+i+1][startPos+v-1]; 
			}
			for(int i = v-1 ; i > 0 ; i--) {
				arr[startPos+h-1][startPos+i] = arr[startPos+h-1][startPos+i-1];
			}
			for(int i = h-1 ; i > 0 ; i--) {
				arr[startPos+i][startPos] = arr[startPos+i-1][startPos];
			}
			arr[startPos+1][startPos] = temp;
		}
	}
}
