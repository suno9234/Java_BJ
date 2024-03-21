import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static int [] nums;
    static int [][] pell ;
	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        tokens = br.readLine().split(" ");
        nums = new int[n];
        pell = new int[n][2];
        // pell[n][0] = 짝수개
        // pell[n][1] = 홀수개
        for(int i = 0 ; i < n ; i++) {
        	nums[i] = Integer.parseInt(tokens[i]);
        }
        for(int i = 0 ; i < n ; i++) {
        	//짝수
        	for(int j = 0 ; j < n ; j++) {
        		if(i-j>=0 && i+1+j < n && nums[i-j] == nums[i+1+j]) {
        			pell[i][0] = j+1;
        		}else {
        			break;
        		}
        	}
        	
        	//홀수
        	for(int j = 0 ; j < n ; j++) {
        		if(i-j>=0 && i+j < n && nums[i-j] == nums[i+j] ) {
        			pell[i][1] = j+1;
        		}else {
        			break;
        		}
        	}
        }
//        System.out.println();
//        System.out.println(Arrays.toString(nums));
//        
//        for(int i = 0 ; i < n ; i++) {
//        	System.out.printf("%d ",pell[i][0]);
//        }
//        System.out.println();
//        for(int i = 0 ; i < n ; i++) {
//        	System.out.printf("%d ",pell[i][1]);
//        }
//        System.out.println();
        int q = Integer.parseInt(br.readLine());
        for(int i = 0 ; i<q ; i++) {
        	tokens = br.readLine().split(" ");
        	int s = Integer.parseInt(tokens[0])-1;
        	int e = Integer.parseInt(tokens[1])-1;
        	int mid = (s+e)/2;
        	int diff = mid-s+1;
        	if((e-s+1) % 2 == 0) {
        		//짝수
        		sb.append(pell[mid][0] >= diff ? 1 : 0).append("\n");
        	}else {
        		sb.append(pell[mid][1] >= diff ? 1 : 0).append("\n");
        	}
        }
        System.out.println(sb);
        br.close();
	}
	
	
}
