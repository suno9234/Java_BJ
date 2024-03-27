import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static int[] c;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		c = new int[n + 1];
		tokens = br.readLine().split(" ");
		int maxLength = 0;
		for (int i = 0; i < n; i++) {
			int now = Integer.parseInt(tokens[i]);
			int start = 0;
			int end = maxLength;
			int result = 0;
			// 0~ now 번째 까지
			while (start <= end) {
				int mid = (start + end) / 2;
				if (c[mid] <= now) {
					// 현재 넣으려는 값이 해당 위치의 최장수열의 마지막 값보다 크면
					start = mid + 1;
					result = mid;
				} else {
					end = mid - 1;
				}
			}
			// result = c보다 작거나 같은 것중 최대 값의 index
			if (result == maxLength) {
				if (c[maxLength] < now) {
					c[++maxLength] = now;
				}else if(c[maxLength-1] < now){
					c[maxLength] = now;
				}
			} else if(c[result]<now){
				c[result+1] = now;
			}
		}
		System.out.println(maxLength);
		br.close();
	}
}
