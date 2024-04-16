import java.io.*;
import java.util.*;

public class Main{
	static int n,answer;
	static int [][] green, blue;
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        green = new int [6][4];
        blue = new int [4][6];
        for(int i = 0 ; i < n ; i++) {
        	st = new StringTokenizer(br.readLine());
        	int t = Integer.parseInt(st.nextToken());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	putG(t,x,y,i);
        	putB(t,x,y,i);
        	boomG();
        	boomB();
        }
        int ans2 = 0;
        for(int i = 0 ; i < 6 ; i++) {
        	for(int j = 0 ; j < 4 ; j++) {
        		if(blue[j][i] > 0) {
        			ans2++;
        		}
        		if(green[i][j] > 0) {
        			ans2++;
        		}
        	}
        }
        sb.append(answer).append("\n").append(ans2);
        System.out.println(sb.toString());
        br.close();
	}
    static void pullG(int cnt,int pos) {
    	for(int i = pos-1 ; i>= 0 ; i--) {
    		for(int j = 0 ; j < 4 ; j++) {
    			green[i+cnt][j] = green[i][j];
    			green[i][j] = 0;
    		}
    	}
    }
    static void pullB(int cnt,int pos) {
    	for(int i = pos-1 ; i>= 0 ; i--) {
    		for(int j = 0 ; j < 4 ; j++) {
    			blue[j][i+cnt] = blue[j][i];
    			blue[j][i] = 0;
    		}
    	}
    }
    static boolean boomG() {
    	boolean result = false;
    	int pos = 0;
    	int cnt = 0;
    	for(int i = 5 ; i >= 2 ; i--) {
    		int flag = 0;
    		for(int j = 0 ; j < 4 ; j++) {
    			if(green[i][j] > 0) {
    				flag++;
    			}
    		}
    		if(flag == 4) {
    			// 펑
    			result = true;
    			answer++;
    			cnt++;
    			pos = i;
    			for(int j = 0 ; j < 4 ; j++) {
    				green[i][j] = 0;
    			}
    		}
    	}
    	// 터진 아래로 cnt칸씪 밀어야됨
    	if(cnt > 0)pullG(cnt,pos);
    	cnt = 0;
    	for(int i = 0 ; i < 2 ; i++) {
    		for(int j = 0 ; j < 4 ; j++) {
    			if(green[i][j] != 0) {
    				cnt++;
    				for(int k = 0 ; k < 4 ; k++) {
    					green[i+4][k] = 0;
    				}
    				break;
    			}
    		}
    	}
    	if(cnt > 0) pullG(cnt,6-cnt);
    	return result;
    }
    static boolean boomB() {
    	boolean result = false;
    	int cnt = 0;
    	int pos = 0;
    	for(int i = 5 ; i >= 2 ; i--) {
    		int flag = 0;
    		for(int j = 0 ; j < 4 ; j++) {
    			if(blue[j][i] > 0) {
    				flag++;
    			}
    		}
    		if(flag == 4) {
    			// 펑
    			result = true;
    			answer++;
    			cnt++;
    			pos = i;
    			for(int j = 0 ; j < 4 ; j++) {
    				blue[j][i] = 0;
    			}
    		}
    	}
    	if(cnt > 0) pullB(cnt,pos);
    	cnt = 0 ;
    	for(int i = 0 ; i <2 ; i++) {
    		for(int j = 0 ; j < 4 ; j++) {
    			if(blue[j][i]!=0) {
    				cnt++;
    				for(int k = 0 ; k < 4 ; k++) {
    					blue[k][i+4] = 0;
    				}
    				break;
    			}
    		}
    	}
		if(cnt > 0) pullB(cnt,6-cnt);
    	return result;
    }
    static void putG(int t , int x , int y , int i) {
    	int h = 5;
    	if(t == 1) {
    		for(int j = 1 ; j < 6 ; j++) {
    			if(green[j][y] != 0) {
    				h = j-1;
    				break;
    			}
    		}
    		green[h][y] = i+1;
    	}else if(t == 2) {
    		// ㅡ모양 
    		for(int j = 1 ; j < 6 ; j++) {
    			if(green[j][y]!=0 || green[j][y+1]!=0) {
    				h = j-1;
    				break;
    			}
    		}
    		green[h][y] = i+1;
    		green[h][y+1] = i+1;
    	}else {
    		// l 모양
    		for(int j = 1 ; j < 6 ; j++) {
    			if(green[j][y]!=0) {
    				h = j-1;
    				break;
    			}
    		}
    		green[h-1][y] = i+1;
    		green[h][y] = i+1;
    	}
    }
    static void putB(int t , int x , int y , int i) {
    	int h = 5;
    	if(t == 1) {
    		for(int j = 1 ; j < 6 ; j++) {
    			if(blue[x][j] != 0) {
    				h = j-1;
    				break;
    			}
    		}
    		blue[x][h] = i+1;
    	}else if(t == 2) {
    		// ㅡ모양
    		for(int j = 1 ; j < 6 ; j++) {
    			if(blue[x][j]!=0) {
    				h = j-1;
    				break;
    			}
    		}
    		blue[x][h-1] = i+1;
    		blue[x][h] = i+1;
    	}else {
    		// ㅣ모양
    		for(int j = 1 ; j < 6 ; j++) {
    			if(blue[x][j]!=0 || blue[x+1][j]!=0) {
    				h = j-1;
    				break;
    			}
    		}
    		blue[x][h] = i+1;
    		blue[x+1][h] = i+1;
    	}
    }
    static void put(int t,int x,int y,int i) {
    	if(t == 1) {
    		green[0][y] = i+1;
    		blue[x][0] = i+1;
    	}else if(t == 2) {
    		green[1][y] = i+1;
    		green[1][y+1] = i+1;
    		blue[x][0] = i+1;
    		blue[x][1] = i+1;
    	}else {
    		green[0][y] = i+1;
    		green[1][y] = i+1;
    		blue[x][1] = i+1;
    		blue[x+1][1] = i+1;
    	}
    }
    
   
}
