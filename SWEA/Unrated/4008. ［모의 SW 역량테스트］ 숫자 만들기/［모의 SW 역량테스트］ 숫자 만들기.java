import java.util.*;
import java.io.*;

public class Solution{
	static int n ,_min, _max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine())+1;
		int [] opers = new int[4];
		for(int tc = 1 ; tc < T; tc++) {
			_max = Integer.MIN_VALUE;
			_min = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			int [] nums = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 4 ; i++) opers[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < n ; i++) nums[i] = Integer.parseInt(st.nextToken());
			perm(0,nums[0],opers,nums);
			sb.append("#").append(tc).append(" ").append(_max-_min).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void perm(int cnt, int calc, int [] opers , int[] nums) {
		if(++cnt == n) {
			_max = Math.max(_max, calc);
			_min = Math.min(_min, calc);
			return;
		}
		if(opers[0] > 0) {
			opers[0]--;
			perm(cnt , calc + nums[cnt],opers , nums);
			opers[0]++;
		}
		if(opers[1] > 0) {
			opers[1]--;
			perm(cnt , calc - nums[cnt],opers, nums);
			opers[1]++;
		}
		if(opers[2] > 0) {
			opers[2]--;
			perm(cnt , calc * nums[cnt],opers, nums);
			opers[2]++;
		}
		if(opers[3] > 0) {
			opers[3]--;
			perm(cnt , calc / nums[cnt],opers, nums);
			opers[3]++;
		}
		cnt--;
	}

}
