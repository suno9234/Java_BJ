import java.io.*;
import java.util.*;

public class Main{
	static int n,answer = Integer.MAX_VALUE;
	static boolean[] isBroken, v;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		int bN = Integer.parseInt(br.readLine());
		isBroken = new boolean[10];
		v = new boolean[1000000];
		if (bN > 0) {
			tokens = br.readLine().split(" ");
			for (int i = 0; i < bN; i++) {
				isBroken[Integer.parseInt(tokens[i])] = true;
			}
		}
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		// queue.add(new int[] {100,0});
		// v[100] = true;
		queue.add(new int[] { n, 0 });
		v[n] = true;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int value = now[0];
			int cnt = now[1]; // cnt => 리모컨으로 숫자를 만들고=> cnt번 움직여 최종 숫자
			int idx = 0;
			if (value == 100) {
				answer = Math.min(answer, cnt);
			}
			// 현재 숫자가 리모컨으로 만들 수 있는 숫자인지 검사
			if (value == 0) {
				if(!isBroken[0]) {
				// 현재 숫자가 0이고 0이 고장나지 않았으면
				answer = Math.min(answer, cnt+1);
				}
			} else {
				// 현재숫자가 0이 아니면 
				while (value>0) {
					if (isBroken[value % 10]) {
						break;
					}
					idx++;
					value /= 10;
				}
				if(value == 0) {
					answer= Math.min(answer, cnt+idx);
				}
			}
			if (now[0] + 1 < 1000000 && !v[now[0] + 1]) {
				v[now[0] + 1] = true;
				queue.add(new int[] { now[0] + 1, cnt + 1 });
			}
			if (now[0] - 1 >= 0 && !v[now[0] - 1]) {
				v[now[0] - 1] = true;
				queue.add(new int[] { now[0] - 1, cnt + 1 });
			}
		}
		System.out.println(answer);
		br.close();
	}
}
