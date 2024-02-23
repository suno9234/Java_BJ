import java.io.*;
import java.util.*;

public class Main{
	static int n,temp,answer;
	static int [] b;
	static boolean [] v;
	static int [][] works;
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        v = new boolean[9];
        b = new int[9];
        works = new int[n][9];
        for(int i = 0 ; i < n ; i++) {
        	tokens = br.readLine().split(" ");
        	for(int j = 0 ; j < 9 ; j++) {
        		works[i][j] = Integer.parseInt(tokens[j]);
        	}
        }
        perm(0);
        System.out.println(answer);
        br.close();
	}
    static int calcScore(int layer, int idx) {
    	int out = 0;
    	int [] players = new int[3];
    	while(out < 3) {
    		if(works[layer][b[idx]] == 4) {
    			temp++;
    			for(int i = 2 ; i >=0 ; i--) {
    				if(players[i] == 1) {
    					players[i] = 0;
    					temp++;
    				}
    			}
    		}else if(works[layer][b[idx]] == 3) {
    			for(int i = 2 ; i >=0 ; i--) {
    				if(players[i] == 1) {
    					temp++;
    				}
    				players[i] = 0;
    			}
    			players[2] = 1;
    		}else if(works[layer][b[idx]] == 2) {
    			for(int i=2 ; i>=0 ; i--) {
    				if(players[i] == 1) {
    					if(i+2 > 2) {
    						temp++;
    					}else {
    						players[i+2] = 1;
    					}
    				}
    				players[i] = 0;
    			}
    			players[1] = 1;
    		}else if(works[layer][b[idx]] == 1) {
    			for(int i=2 ; i>=0 ; i--) {
    				if(players[i] == 1) {
    					if(i+1 > 2) {
    						temp++;
    					}else {
    						players[i+1] = 1;
    					}
    				}
    				players[i] = 0;
    			}
    			players[0] = 1;
    		}else {
    			out++;
    		}
    		idx = ((idx+1) % 9);
    	}
    	
    	return idx;
    }
    static void perm(int cnt) {
    	if(cnt == 9) {
    		temp = 0;
    		int idx = 0;
    		for(int i = 0 ; i < n ; i++) {
    			idx = calcScore(i,idx);
    		}
    		answer = Math.max(answer, temp);
    		return;
    	}
    	if(cnt == 3) {
    		v[0] = true;
    		b[3] = 0;
    		perm(cnt+1);
    	}
    	for(int i = 1 ; i < 9 ; i++) {
    		if(v[i]) continue;
    		v[i] = true;
    		b[cnt] = i;
    		perm(cnt+1);
    		v[i] = false;
    	}
    }
}
