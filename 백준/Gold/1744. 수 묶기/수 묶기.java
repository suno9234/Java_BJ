import java.util.*;
import java.io.*;

public class Main{
	static int n,answer;
	static int [] nums;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		int zeros = 0;
		for(int i = 0; i < n ;i++) {
			int now = Integer.parseInt(br.readLine());
			if(now > 0) {
				plus.add(now);
			}else if(now < 0) {
				minus.add(now);
			}else {
				zeros++;
			}
		}
		int last = 0;
		while(!plus.isEmpty()) {
			if(last == 0) {
				last = plus.poll();
			}else {
				int now = plus.poll();
				if(now == 1) {
					answer+=last+1;
				}else {
					answer+=last*now;
				}
				last = 0;
			}
		}
		if(last != 0) {
			answer+=last;
		}
		last = 0;
		while(!minus.isEmpty()) {
			if(last == 0) {
				last = minus.poll();
			}else {
				answer+=last*minus.poll();
				last = 0;
			}
		}
		if(last !=0 && zeros == 0) {
			answer+=last;
		}
		System.out.println(answer);
	}

}
