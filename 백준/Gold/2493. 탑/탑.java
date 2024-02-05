import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int[] towers;
	static int[] answers;
	static ArrayDeque<Integer[]> s = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		towers = new int[n];
		answers = new int[n];
		String [] tokens = br.readLine().split(" ");
		for(int i = 0 ; i < n ; i++) {
			int nowHeight = Integer.parseInt(tokens[i]);
			int idx = 0;
			while(!s.isEmpty()) {
				Integer[] now = s.pollLast();
				//System.out.printf("%d %d\n",now[0],now[1]);
				if(nowHeight < now[0] ) {
					// 지금 높이가 스텍의 마지막 높이보다 작을 경우
					s.offer(now);
					answers[i] = now[1];
					break;
				}
			}
			s.offer(new Integer[] {nowHeight,i+1});
		}
	
		for(int i = 0 ; i < n ; i++) {
			sb.append(answers[i]).append(" ");
		}
		System.out.println(sb.toString());
			
	}

}
