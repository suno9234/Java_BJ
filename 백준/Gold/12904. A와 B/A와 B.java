import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder s = new StringBuilder(br.readLine());
        StringBuilder t = new StringBuilder(br.readLine()); 
        int sl = s.length();
        int tl = t.length();
        // s=> t 가능?
        // 뒤에 a 추가
        // 뒤집어서 b추가
        int answer = 0;
        while(sl < tl) {
        	char c = t.charAt(tl-1);
        	t.deleteCharAt(tl-1);
        	if(c=='B') {
        		t = t.reverse();
        	}
        	if(s.compareTo(t)==0) {
        		answer = 1;
        		break;
        	}
        	tl--;
        }
        System.out.println(answer);
        br.close();
	}
}
