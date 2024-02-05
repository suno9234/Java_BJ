import java.util.*;
import java.io.*;

public class Main{
	static int n,k;
	static int [] answer;
	static ArrayList<Integer> al = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		k = Integer.parseInt(tokens[1]);
		LinkedList<Integer> ls = new LinkedList<>();
		for(int i = 0 ; i < n ; i++) {
			ls.add(i+1);
		}
		answer = new int[n];
		int idx = 0;
		for(int i = 0 ; i < n ; i++) {
			int now = ls.get((idx+k-1)%(n-i));
			sb.append(now);
			ls.remove((idx+k-1)%(n-i));
			idx = (idx+k-1)%(n-i);
			sb.append(", ");
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb.toString());
	}
}



