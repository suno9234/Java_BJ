import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static int [] rRe,rGe,rBe, gRe,gGe,gBe,bRe,bGe,bBe;
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        rRe = new int[n];
        rGe = new int[n];
        rBe = new int[n];
        bRe = new int[n];
        bGe = new int[n];
        bBe = new int[n];
        gRe = new int[n];
        gGe = new int[n];
        gBe = new int[n];
        for(int i = 0 ; i < n ; i++) {
        	tokens = br.readLine().split(" ");
        	int r = Integer.parseInt(tokens[0]);
        	int g = Integer.parseInt(tokens[1]);
        	int b = Integer.parseInt(tokens[2]);
        	if(i == 0) {
        		rRe[i] = r;
        		rGe[i] = Integer.MAX_VALUE;
        		rBe[i] = Integer.MAX_VALUE;
        		gRe[i] = Integer.MAX_VALUE;
        		gGe[i] = g;
        		gBe[i] = Integer.MAX_VALUE;
        		bRe[i] = Integer.MAX_VALUE;
        		bGe[i] = Integer.MAX_VALUE;
        		bBe[i] = b;
        	}else if(i == 1){
        		rRe[i] = Integer.MAX_VALUE;
        		rGe[i] = rRe[0]+g;
        		rBe[i] = rRe[0]+b;
        		gRe[i] = gGe[0]+r;
        		gGe[i] = Integer.MAX_VALUE;
        		gBe[i] = gGe[0]+b;
        		bRe[i] = bBe[0]+r;
        		bGe[i] = bBe[0]+g;
        		bBe[i] = Integer.MAX_VALUE;
        	}else {
        		rRe[i] = Math.min(rGe[i-1], rBe[i-1])+r;
        		rGe[i] = Math.min(rBe[i-1], rRe[i-1])+g;
        		rBe[i] = Math.min(rRe[i-1], rGe[i-1])+b;
        		
        		gRe[i] = Math.min(gGe[i-1], gBe[i-1])+r;
        		gGe[i] = Math.min(gBe[i-1], gRe[i-1])+g;
        		gBe[i] = Math.min(gRe[i-1], gGe[i-1])+b;
        		
        		bRe[i] = Math.min(bGe[i-1], bBe[i-1])+r;
        		bGe[i] = Math.min(bBe[i-1], bRe[i-1])+g;
        		bBe[i] = Math.min(bRe[i-1], bGe[i-1])+b;
        	}
        }

        int answer = Math.min(rGe[n-1], rBe[n-1]);
        answer = Math.min(answer, gRe[n-1]);
        answer = Math.min(answer, gBe[n-1]);
        answer = Math.min(answer, bRe[n-1]);
        answer = Math.min(answer, bGe[n-1]);
        System.out.println(answer);
        br.close();
	}
}
