import java.util.*;
import java.io.*;

public class Main{

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [][] mt = new int[n][2];
		int _max = 0;
		for(int i = 0 ; i < n ; i++) {
			String [] tokens = br.readLine().split(" ");
			mt[i][0] = Integer.parseInt(tokens[0]);
			mt[i][1] = Integer.parseInt(tokens[1]);
			_max = Math.max(_max, mt[i][1]);
		}
		Arrays.sort(mt,new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1]==b[1] ? a[0]-b[0] : a[1]-b[1];
			}
		});
		int answer = 0;
		int last = 0;
		for(int [] m : mt) {
			int start = m[0];
			int end = m[1];
			if(last <=start) {
				last = end;
				answer++;
			}
		}
		System.out.println(answer);
	}

}
