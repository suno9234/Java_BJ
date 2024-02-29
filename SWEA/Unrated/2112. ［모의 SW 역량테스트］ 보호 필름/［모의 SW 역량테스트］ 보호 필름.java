import java.util.*;
import java.io.*;

public class Solution{

	static int answer,d,w,k;
	static boolean isEnded;
	static boolean [] v;
	static int [][] film;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine())+1;
		for(int tc = 1; tc < test_case; tc++) {
			answer = Integer.MAX_VALUE;
			String [] tokens = br.readLine().split(" ");
			d = Integer.parseInt(tokens[0]);
			w = Integer.parseInt(tokens[1]);
			k = Integer.parseInt(tokens[2]);
			film = new int[d][w];
			for(int i = 0 ; i < d ; i++) {
				tokens = br.readLine().split(" ");
				for(int j = 0 ; j < w ; j++) {
					film[i][j] = Integer.parseInt(tokens[j]);
				}
			}
			isEnded = false;
			for(int i = 0 ; i < d ; i++) {
				comb(0,i,i);
				if(isEnded) {
					break;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
	static void comb(int start, int cnt, int idx) {
		if(isEnded) return;
		if(cnt == 0) {
			if(check(film)) {
				answer = Math.min(answer, idx);
				isEnded = true;
			}
			return;
		}
		for(int i = start; i < d ; i++) {
			int [] temp = Arrays.copyOf(film[i], w);
			Arrays.fill(film[i], 0);
			comb(i+1,cnt-1,idx);
			Arrays.fill(film[i], 1);
			comb(i+1,cnt-1,idx);
			film[i] = Arrays.copyOf(temp, w);
		}
		
	}
	
	static boolean check(int [][] film) {
		for(int i = 0 ; i <w ; i++) {
			int ac = 0;
			int bc = 0;
			int flag = 0;
			for(int j = 0 ; j < k; j++){
				if(film[j][i] == 0) {
					ac++;
				}else {
					bc++;
				}
			}
			if(ac == k || bc == k) {
				continue;
			}
			for(int j = k; j < d; j++) {
				if(film[j][i] == 0) {
					ac++;
					if(film[j-k][i] == 0 ) {
						ac--;
					}else {
						bc--;
					}
					
				}else {
					bc++;
					if(film[j-k][i] == 0) {
						ac--;
					}else {
						bc--;
					}
				}
				if(ac==k || bc == k) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				return false;
			}
		}
		return true;
	}
}
