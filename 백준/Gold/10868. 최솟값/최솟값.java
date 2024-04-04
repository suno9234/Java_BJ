import java.util.*;
import java.io.*;

public class Main{

	static int[] nums;
	static int[] mins;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] tokens = br.readLine().split(" ");
		int n, m;
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		nums = new int[n + 1];
		for (int i = 0; i < n; i++) {
			nums[i + 1] = Integer.parseInt(br.readLine());
		}
		int idx = 1;
		int nCopy = n;
		while (nCopy > 0) {
			idx++;
			nCopy = nCopy >> 1;
		}
		mins = new int[(1 << idx) + 1];
		initMin(1, 1, n);
		for (int i = 0; i < m; i++) {
			tokens = br.readLine().split(" ");
			int l = Integer.parseInt(tokens[0]);
			int r = Integer.parseInt(tokens[1]);
//					System.out.println("MAX"+l+" "+r+ " : "+getMax(1,1,n,l,r));
//					System.out.println("MIN"+l+" "+r+ " : "+getMin(1,1,n,l,r));
			sb
			.append(getMin(1, 1, n, l, r)).append("\n");
		}
		System.out.println(sb.toString());
	}


	static int getMin(int node, int start, int end, int left, int right) {
		if (start > right || end < left) {
			return Integer.MAX_VALUE;
		}
		if (left <= start && end <= right) {
			return mins[node];
		}
		return Math.min(getMin(node * 2, start, (start + end) / 2, left, right),
				getMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
	}


	static int initMin(int node, int start, int end) {
		if (start == end) {
			return mins[node] = nums[start];
		}
		return mins[node] = Math.min(initMin(node * 2, start, (start + end) / 2),
				initMin(node * 2 + 1, (start + end) / 2 + 1, end));
	}

}
