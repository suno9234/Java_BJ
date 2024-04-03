import java.util.*;
import java.io.*;

public class Main {
	static int n,m,k;
	static long[] nums;
	static long[] sums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		k = Integer.parseInt(tokens[2]);
		nums = new long[n + 1];
		for (int i = 0; i < n; i++) {
			nums[i + 1] = Long.parseLong(br.readLine());
		}
		int idx = 0;
		int nCopy = n + 1;
		while (nCopy > 0) {
			idx++;
			nCopy = nCopy >> 1;
		}
		sums = new long[1 << (idx + 1)];
		init(1, 1, n);
		for (int i = 0; i < m + k; i++) {
			tokens = br.readLine().split(" ");
			int o = Integer.parseInt(tokens[0]);
			int a = Integer.parseInt(tokens[1]);
			if (o == 1) {
				long b = Long.parseLong(tokens[2]);
				long now = sum(1, 1, n, a, a);
				update(1, 1, n, a, b-now);
			} else {
				int b = Integer.parseInt(tokens[2]);
				sb.append(sum(1, 1, n, a, b)).append("\n");
			}
		}
		System.out.println(sb);
	}

	static long sum(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			return sums[node];
		}

		return sum(node * 2, start, (start + end) / 2, left, right)
				+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}

	static void update(int node, int start, int end, int idx, long diff) {
		if (idx < start || end < idx)
			return;

		sums[node] += diff;
		if (start != end) {
			update(node * 2, start, (start + end) / 2, idx, diff);
			update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
		}

	}

	static long init(int node, int start, int end) {
		if (start == end) {
			return sums[node] = nums[start];
		}

		return sums[node] = init(node * 2, start, (start + end) / 2) + init(node * 2 + 1, (start + end) / 2 + 1, end);
	}

}
