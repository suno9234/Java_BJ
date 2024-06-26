import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static int [] in;
	static int [] nexts;
	static long [] info;
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        in = new int[n+1];
        nexts = new int[n+1];
        info = new long[n+1];
        for(int i = 0 ; i < n-1 ; i++) {
        	st = new StringTokenizer(br.readLine());
        	String type = st.nextToken();
        	int count = Integer.parseInt(st.nextToken());
        	int next = Integer.parseInt(st.nextToken());
        	in[next]++;
        	if(type.equals("S")) {
        		info[i+2] = count;
        	}else {
        		info[i+2] = -count; 
        	}
        	nexts[i+2] = next;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i =2 ; i < n+1 ; i++) {
        	if(in[i] == 0) {
        		queue.add(i);
        	}
        }
        while(!queue.isEmpty()) {
        	int now = queue.poll();
        	// now = {count , next}
        	long count = info[now];
        	int next = nexts[now];
        	in[next]--;
        	if(count > 0) {
        		// 양일 경우만 진행
        		info[next]+=count;
        	}
        	if(in[next] == 0) {
        		queue.add(next);
        	}
        }
        System.out.println(info[1]);
        br.close();
	}
}
