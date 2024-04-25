import java.io.*;
import java.util.*;

public class Main{
	static int n,answer;
	static int [][] eggs;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        eggs = new int[n][3];
        for(int i = 0 ; i < n ; i++) {
        	st= new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	eggs[i] = new int[] {s,w};
        	// 0 = 체력 1 = 공격력
        }
        dfs(0,0);
        System.out.println(answer);
        br.close();
	}
    
    static void dfs(int num, int cnt) {
    	answer = Math.max(answer, cnt);
    	if(num == n) {
    		return;
    	}
    	int [] egg = eggs[num];
    	if(egg[0] <= 0) {
    		// 손에 든 계란이 깨진 경우 치지 않고 넘어감
    		dfs(num+1,cnt);
    		return;
    	}
    	for(int i = 0; i < n ; i++) {
    		if(eggs[i][0] > 0 && i!= num) {
    			eggs[i][0]-= egg[1];
    			egg[0]-=eggs[i][1];
    			if(egg[0]<=0) cnt++;
    			if(eggs[i][0] <= 0) {
    				dfs(num+1,cnt+1);
    			}else {
    				dfs(num+1,cnt);
    			}
    			if(egg[0]<=0) cnt--;
    			eggs[i][0]+=egg[1];
    			egg[0]+=eggs[i][1];
    		}
    	}
    }
}
