import java.io.*;
import java.util.*;

public class Main {

	static int answer;
	static int[] projects;
	static boolean[] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		v = new boolean[366];
		int startDay = toIdx(3, 1);
		int endDay = toIdx(11, 30);
		// n < 100000
		projects = new int[366];
		for (int i = 0; i < n; i++) {
			String[] tokens = br.readLine().split(" ");
			int sI = toIdx(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			int eI = toIdx(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
			if (projects[sI] < eI) {
				projects[sI] = eI;
			}
		}
		// 2월 26일 - 5월 25일
		// => 2월26일~ 5월24일

		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= toIdx(3, 1); i++) {
			int cnt = 0;
			int start = i;
			if (projects[i] == 0)
				continue;
			while (true) {
				int eI = projects[start];
				// start = 출발
				cnt++;
				// eI = start시간에서 갈 수 있는 최대값
				if (eI > 0) {
					if (eI > toIdx(11, 30)) {
						if (eI == toIdx(11, 30))
							cnt++;
						answer = Math.min(answer, cnt);
						break;
					} else {
						int flag = 0;
						int maxIndex = -1;
						int maxValue = 0;
						for (int j = eI; j > start; j--) {
							if (projects[j] > 0) {
								if (maxValue < projects[j]) {
									maxValue = projects[j];
									maxIndex = j;
								}
								flag = 1;
							}
						}
						if (flag == 0) {
							break;
						}
						start = maxIndex;
					}
				} else {
					break;
				}

			}

		}
		if (answer == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}

	static int toIdx(int m, int d) {
		while (m > 1) {
			switch (m) {
			case 2 :
				d += 31;
				break;
			case 4 :
				d += 31;
				break;
			case 6 :
				d += 31;
				break;
			case 8 :
				d += 31;
				break;
			case 9 :
				d += 31;
				break;
			case 11 :
				d += 31;
				break;
			case 3:
				d += 28;
				break;
			case 5:
				d += 30;
				break;
			case 7:
				d += 30;
				break;
			case 10:
				d += 30;
				break;
			case 12:
				d += 30;
				break;
		
			}
			
			m--;
		}
		return d;
	}

}
