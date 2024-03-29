import java.io.*;
import java.util.*;

public class Main{
	static int n,d,k,c,answer;
	static HashMap<Integer, Integer> hm;
    static int [] sushi;
	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        d = Integer.parseInt(tokens[1]);
        k = Integer.parseInt(tokens[2]);
        c = Integer.parseInt(tokens[3]);
        sushi = new int[n+k];
        hm = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
        	sushi[i] = Integer.parseInt(br.readLine());
        }
        hm.put(c,1);
        for(int i = 0 ; i < k ; i++) {
        	sushi[n+i] = sushi[i];
       		hm.put(sushi[i], hm.getOrDefault(sushi[i],0)+1);
        }
        answer = hm.size();
        for(int i = 0; i < n ; i++) {
        	hm.put(sushi[k+i], hm.getOrDefault(sushi[k+i],0)+1);   	
        	int cnt = hm.get(sushi[i])-1; 
        	if(cnt == 0) {
        		hm.remove(sushi[i]);
        	}else {
        		hm.put(sushi[i], cnt);
        	}
        	answer = Math.max(answer,hm.size());
        	if(answer == k+1) {
        		break;
        	}
        }
        
        System.out.println(answer);
        br.close();
	}
}
