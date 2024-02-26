import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int []projects = new int[1232];
		for (int i = 0; i < n; i++) {
			String[] tokens = br.readLine().split(" ");
			int sI = Integer.parseInt(tokens[0])*100+Integer.parseInt(tokens[1]);
			int eI = Integer.parseInt(tokens[2])*100+Integer.parseInt(tokens[3]);
			if (projects[sI] < eI) {
				projects[sI] = eI;
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= 301; i++) {
			int cnt = 0;
			int start = i;
			if (projects[i] == 0)
				continue;
			while (true) {
				int eI = projects[start];
				cnt++;
				if (eI > 0) {
					if (eI > 1130) {
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
}
