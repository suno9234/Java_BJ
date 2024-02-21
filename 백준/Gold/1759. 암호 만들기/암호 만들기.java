import java.io.*;
import java.util.*;

public class Main{
	static int l,c;
	static char [] alphas;
	static char [] b; 
	static StringBuilder sb;
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        sb = new StringBuilder();
        l = Integer.parseInt(tokens[0]);
        c = Integer.parseInt(tokens[1]);
        b = new char[l];
        tokens = br.readLine().split(" ");
        alphas = new char[c];
        for(int i = 0 ; i < c ; i++) {
        	alphas[i] = tokens[i].charAt(0);
        }
        Arrays.sort(alphas);
        comb(0,0,0,0);
        System.out.println(sb);
        br.close();
	}
    static void comb(int start, int cnt,int child, int mother) {
    	if(cnt == l){
    		if( mother > 0 && child > 1) {
	    		for(int i = 0 ; i < l ; i++) {
	    			sb.append(b[i]);
	    		}
	    		sb.append("\n");
    		}
    		return;
    	}
    	for(int i = start ; i< c ; i++) {
    		b[cnt] = alphas[i];
    		if(b[cnt] == 'a' || b[cnt]=='e'||b[cnt]=='i'||b[cnt]=='o'||b[cnt]=='u') comb(i+1,cnt+1,child,mother+1);
    		else comb(i+1,cnt+1,child+1,mother);
    	}
    }
}
