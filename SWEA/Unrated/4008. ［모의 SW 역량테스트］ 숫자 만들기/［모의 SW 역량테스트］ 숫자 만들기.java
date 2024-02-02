import java.util.*;
import java.io.*;

public class Solution{

	static int n ,_min, _max;
	static int[] nums;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine())+1;
		int [] opers = new int[4];
		for(int tc = 1 ; tc < T; tc++) {
			_max = Integer.MIN_VALUE;
			_min = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			
			String [] tokens = br.readLine().split(" ");
			for(int i = 0 ; i < 4 ; i++) {
				opers[i] = Integer.parseInt(tokens[i]);
			}
			
			nums = new int[n];
			tokens = br.readLine().split(" ");
			for(int i = 0 ; i < n ; i++) {
				nums[i] = Integer.parseInt(tokens[i]);
			}
			
			perm(0,nums[0],opers);
			sb.append("#").append(tc).append(" ").append(_max-_min).append("\n");
			
		}
		System.out.println(sb.toString());
	}
	static void perm(int cnt, int calc, int [] opers) {
		if(++cnt == n) {
			_max = Math.max(_max, calc);
			_min = Math.min(_min, calc);
			return;
		}
		if(opers[0] > 0) {
			opers[0]--;
			perm(cnt , calc + nums[cnt],opers);
			opers[0]++;
		}
		if(opers[1] > 0) {
			opers[1]--;
			perm(cnt , calc - nums[cnt],opers);
			opers[1]++;
		}
		if(opers[2] > 0) {
			opers[2]--;
			perm(cnt , calc * nums[cnt],opers);
			opers[2]++;
		}
		if(opers[3] > 0) {
			opers[3]--;
			perm(cnt , calc / nums[cnt],opers);
			opers[3]++;
		}
		cnt--;
	}

}
