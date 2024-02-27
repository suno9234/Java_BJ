import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static long[][] coors;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		coors = new long[n][2];
		int minIdx = 0;
		long minX = Long.MAX_VALUE;
		long minY = Long.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			if (y < minY) {
				minIdx = i;
				minX = x;
				minY = y;
			} else if (y == minY) {
				if (x < minX) {
					minIdx = i;
					minX = x;
					minY = y;
				}
			}
			coors[i] = new long[] { x, y };
		}
		PriorityQueue<long[]> tops = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
		int flag = 0;
		long last = 0;
		for (int i = 0 + minIdx; i < n + minIdx; i++) {
			long[] now = coors[i % n];
			long[] next = coors[(i + 1) % n];
			long x = now[0];
			long y = now[1];
			long nx = next[0];
			long ny = next[1];
			if (x == nx && y * ny < 0) {
				if (flag == 0) {
					flag = 1;
					last = x;
				} else if (flag == 1) {
					if (last < x) {
						tops.add(new long[] { last, 1 });
						tops.add(new long[] { x, -1 });
					} else {
						tops.add(new long[] { last, -1 });
						tops.add(new long[] { x, 1 });
					}
					flag = 0;
				}
			}
		}
		int ans1 = 0;
		int ans2 = 0;
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		last = 0;
		while (!tops.isEmpty()) {
			long[] pillar = tops.poll();
			flag = (int)pillar[1];
			if (flag == 1) {
				// 기둥이 위로 가는경우 = push
				if (stack.isEmpty()) {
					ans1++;
				}
				stack.push(1);
			} else {
				if (last == 1) {
					ans2++;
				}
				stack.pop();
			}
			last = flag;
		}
		System.out.println(ans1 + " " + ans2);

	}
}
