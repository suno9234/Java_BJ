import java.io.*;
import java.util.*;

public class Main{
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		parent = new int[n];
		Arrays.setAll(parent, x -> x);
		for (int i = 0; i < m; i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			if (!union(a, b)) {
				System.out.println(i + 1);
				return;
			}
		}
		System.out.println(0);
		br.close();
	}

	static int getParent(int a) {
		if (parent[a] == a) {
			return a;
		}
		return parent[a] = getParent(parent[a]);
	}

	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		if (pa == pb) {
			return false;
		}
		if (pa > pb) {
			parent[pa] = pb;
		} else {
			// a < b
			parent[pb] = pa;
		}
		return true;
	}
}
