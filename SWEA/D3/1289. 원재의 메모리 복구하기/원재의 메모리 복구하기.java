import java.util.*;
import java.io.*;
public class Solution{

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_d1_2072.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T; tc++) {
			String nl = br.readLine();
			int answer = 1;
			// 작성하세요  Integer.parseInt(st.nextToken);
			int i = 0;
			if(nl.equals("0")) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}
			while(nl.charAt(i)=='0') {
				i++;
			}
			char last = '1';
			for( ; i<nl.length();i++) {
				if(nl.charAt(i)!= last) {
					last = nl.charAt(i);
					answer+=1;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		
		System.out.println(sb.toString());
		br.close();

	}

}