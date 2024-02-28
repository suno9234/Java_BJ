import java.util.*;
import java.io.*;

public class Solution {

	static int answer;
	static int[] price = new int[4];
	static int[] plan = new int[13];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine()) + 1;
		for (int tc = 1; tc < test_case; tc++) {
			answer = Integer.MAX_VALUE;
			String[] tokens = br.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(tokens[i]);
			}
			tokens = br.readLine().split(" ");
			for (int i = 0; i < 12; i++) {
				plan[i+1] = Integer.parseInt(tokens[i]);
			}
			dfs(1, 0);

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void dfs(int month, int priceSum) {
		if (month > 12) {
			answer = Math.min(answer, priceSum);
			return;
		}
		if (plan[month] == 0) {
			dfs(month + 1, priceSum);
		} else {
			if (month + 1 <= 13) {
				dfs(month + 1, priceSum + plan[month] * price[0]); // 일일권
				dfs(month + 1, priceSum + price[1]); // 한달권
			}
			if (month + 3 <= 15)
				dfs(month + 3, priceSum + price[2]); // 세달권
			if (month + 12 <= 13)
				dfs(month + 12, priceSum + price[3]); // 일년권
		}
	}

}
