import java.io.*;
import java.util.*;

public class Main{
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(br.readLine());
			TreeMap<Integer,Integer> mmPq = new TreeMap<>();
			for (int j = 0; j < k; j++) {
				tokens = br.readLine().split(" ");
				char oper = tokens[0].charAt(0);
				int num = Integer.parseInt(tokens[1]);
				if (oper == 'D') {
					if (num == 1) {
						if(!mmPq.isEmpty()) {
							int key = mmPq.lastKey();
							int cnt = mmPq.get(key);
							if(cnt == 1) {
								mmPq.pollLastEntry();
							}else {
								mmPq.put(key, cnt-1);
							}
						}
					} else {
						if(!mmPq.isEmpty()) {
							int key = mmPq.firstKey();
							int cnt = mmPq.get(key);
							if(cnt == 1) {
								mmPq.pollFirstEntry();
							}else {
								mmPq.put(key, cnt-1);
							}
						}
					}

				} else {
					if(mmPq.get(num) == null) {
						mmPq.put(num,1);
					}else {
						mmPq.put(num, mmPq.get(num)+1);
					}
				}
			}

			if(mmPq.size() > 0) {
				int fk = mmPq.firstKey();
				int lk = mmPq.lastKey();
				sb.append(lk).append(" ").append(fk).append("\n");
			}else {
				sb.append("EMPTY\n");
			}

		}
		System.out.println(sb);
		br.close();
	}
}
