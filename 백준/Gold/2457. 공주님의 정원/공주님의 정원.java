import java.util.*;
import java.io.*;

public class Main{

	static int n, answer = -1;
	static int[] endDay;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		endDay = new int[1232];
		for (int i = 0; i < n; i++) {
			tokens = br.readLine().split(" ");
			int sm = Integer.parseInt(tokens[0]);
			int sd = Integer.parseInt(tokens[1]);
			int em = Integer.parseInt(tokens[2]);
			int ed = Integer.parseInt(tokens[3]);
			int s = sm * 100 + sd;
			int e = em * 100 + ed;
			if (endDay[s] < e)
				endDay[s] = e;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 101; i <= 301; i++) {
			int startDay = i;
			int cnt = 0;
			if (endDay[startDay] > 0) {
				while (true) {
					int ed = endDay[startDay];
					cnt++;
					if (ed >= 1131) {
						answer = Math.min(answer, cnt);
						break;
					} else {
						int maxDay = -1;
						int maxEndDay = 0;
						for (int j = ed; j > startDay; j--) {
							if (maxEndDay < endDay[j]) {
								maxEndDay = endDay[j];
								maxDay = j;
							}
						}
						if (maxEndDay == 0) {
							break;
						}
						startDay = maxDay;
					}

				}
			}
		}
		if (answer == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(answer);

	}

}
