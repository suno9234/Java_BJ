import java.util.*;
import java.io.*;

public class Solution {

	static int[] fac;
	static int answer, n;
	static int[] nums;
	static boolean[] v;
	static int _sum;
	static int[][] mem;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine()) + 1;
		fac = new int[10];
		fac[0] = 1;
		fac[1] = 1;
		for (int i = 2; i < 10; i++) {
			fac[i] = fac[i - 1] * i;
		}
		for (int tc = 1; tc < test_case; tc++) {
			n = Integer.parseInt(br.readLine());
			answer = (int) Math.pow(2, n) * fac[n];
			nums = new int[n];
			v = new boolean[n];
			_sum = 0;
			String[] tokens = br.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(tokens[i]);
				_sum += nums[i];
			}
			mem = new int[_sum + 1][1 << n];
			// perm2(0,0,0,_sum);
			answer = perm2(n, 0, 0, _sum, 0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void makeDp(int v, int num) {

	}

	static void perm(int cnt, int sum, int MaxSum) {
		if (sum < 0) {
			answer -= (int) Math.pow(2, (n - cnt)) * fac[n - cnt];
			return;
		}
		if (sum >= MaxSum - sum || cnt == n) {
			return;
		}
		for (int i = 0; i < n; i++) {
			if (v[i])
				continue;
			v[i] = true;
			perm(cnt + 1, sum + nums[i], MaxSum);
			perm(cnt + 1, sum - nums[i], MaxSum);
			v[i] = false;
		}
	}

	static void perm2(int cnt, int lSum, int rSum, int MaxSum) {
		if (lSum - rSum < 0) {
			answer -= (int) Math.pow(2, (n - cnt)) * fac[n - cnt];
			return;
		}
		if (lSum > MaxSum / 2 || cnt == n) {
			return;
		}
		for (int i = 0; i < n; i++) {
			if (v[i])
				continue;
			v[i] = true;
			perm2(cnt + 1, lSum + nums[i], rSum, MaxSum);
			perm2(cnt + 1, lSum, rSum + nums[i], MaxSum);
			v[i] = false;
		}
	}

	static int perm2(int cnt, int lSum, int rSum, int MaxSum, int v) {
		int answer = 0;
		if (lSum < rSum) {
			return 0;
		}
		if (lSum > MaxSum / 2) {
			return mem[lSum][v] = (int) Math.pow(2, cnt) * fac[cnt];
		}
		if (mem[lSum][v] != 0) {
			return mem[lSum][v];
		}
		if (cnt == 0) {
			return mem[lSum][v] = 1;
		}
		for (int i = 0; i < n; i++) {
			if ((v & (1 << i)) == 0) {
				answer += perm2(cnt - 1, lSum + nums[i], rSum, MaxSum, v | (1 << i));
				answer += perm2(cnt - 1, lSum, rSum + nums[i], MaxSum, v | (1 << i));
			}
		}
		return mem[lSum][v] = answer;
	}

}
