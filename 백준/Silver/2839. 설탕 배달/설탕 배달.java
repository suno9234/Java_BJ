import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int answer = -1;
		int idx = n/5;
		while(idx >= 0) {
			if((n-(idx*5))%3 == 0) {
				answer = idx+ (n-(idx*5))/3;
				break;
			}
			idx--;
		}
		
		System.out.println(answer);
		
	}

}
