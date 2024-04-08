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
        	put(t,x,y,i);
        	gravityG();
        	gravityB();
        	while(boomG()) {
        	}
        	while(boomB()) {	
        	}
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
    static boolean boomG() {
    	boolean result = false;
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
    			for(int j = 0 ; j < 4 ; j++) {
    				green[i][j] = 0;
    			}
    		}
    	}
    	gravityG();
    	int flag = 0;
    	for(int i = 0 ; i < 2 ; i++) {
    		for(int j = 0 ; j < 4 ; j++) {
    			if(green[i][j] != 0) {
    				flag = 1;
    				for(int k = 0 ; k < 4 ; k++) {
    					green[i+4][k] = 0;
    				}
    				break;
    			}
    		}
    	}
    	if(flag == 1)
    	gravityG();
    	return result;
    }
    static boolean boomB() {
    	boolean result = false;
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
    			for(int j = 0 ; j < 4 ; j++) {
    				blue[j][i] = 0;
    			}
    		}
    	}
    	gravityB();
    	int flag = 0;
    	for(int i = 0 ; i <2 ; i++) {
    		for(int j = 0 ; j < 4 ; j++) {
    			if(blue[j][i]!=0) {
    				flag = 1;
    				for(int k = 0 ; k < 4 ; k++) {
    					blue[k][i+4] = 0;
    				}
    				break;
    			}
    		}
    	}
    	if(flag == 1)
    	gravityB();
    	return result;
    }
    static void gravityG() {
    	int [][] nG = new int[6][4];
    	int [] gh = new int[] {5,5,5,5};
    	for(int i = 5 ; i >=0 ; i--) {
    		// 아래부터 위로 검사
    		for(int j = 0 ; j < 4 ; j++) {
    			// 왼쪽부터 오른쪽 검사
    			if(green[i][j] != 0) {
    				// 만약 해당 칸에 블록이 있으면
    				if(j+1 < 4 && green[i][j+1] == green[i][j]) {
    					// 오른쪽에 공간이 있는 경우에 나와 번호가 같으면
    					// ㅡ 모양 블록
    					if(gh[j] == gh[j+1]) {
    						// 양쪽 높이가 같으면
    						nG[gh[j]--][j] = green[i][j];
    						nG[gh[j+1]--][j+1] = green[i][j+1];
    					}else if(gh[j] > gh[j+1]) {
    						nG[gh[j+1]][j] = green[i][j];
    						nG[gh[j+1]][j+1] = green[i][j+1];
    						gh[j] = --gh[j+1];
    					}else {
    						nG[gh[j]][j] = green[i][j];
    						nG[gh[j]][j+1] = green[i][j+1];
    						gh[j+1] = --gh[j];
    					}
    					green[i][j+1] = 0;
    				}else {
    					// 나 혼자만 있는 블록이면
    					nG[gh[j]--][j] = green[i][j];
    				}
    				green[i][j] = 0;
    			}
    		}
    	}
    	green = nG;
    }
    static void gravityB() {
    	int [][] nB = new int[4][6];
    	int [] bh = new int[] {5,5,5,5};
    	for(int i = 5 ; i >=0 ; i--) {
    		// 오른쪽부터 왼쪽으로
    		for(int j = 0 ; j < 4 ; j++) {
    			//위에서 아래로
    			if(blue[j][i] != 0) {
    				// 만약 해당 칸에 블록이 있으면
    				if(j+1 < 4 && blue[j+1][i] ==blue[j][i]) {
    					// 오른쪽에 공간이 있는 경우에 나와 번호가 같으면
    					// | 모양 블록
    					if(bh[j] == bh[j+1]) {
    						// 양쪽 높이가 같으면
    						nB[j][bh[j]--] = blue[j][i];
    						nB[j+1][bh[j+1]--] = blue[j+1][i];
    					}else if(bh[j] > bh[j+1]) {
    						nB[j][bh[j+1]] = blue[j][i];
    						nB[j+1][bh[j+1]] = blue[j+1][i];
    						bh[j] = --bh[j+1];
    					}else {
    						nB[j][bh[j]] = blue[j][i];
    						nB[j+1][bh[j]] = blue[j+1][i];
    						bh[j+1] = --bh[j];
    					}
    					blue[j+1][i] = 0;
    				}else {
    					// 나 혼자만 있는 블록이면
    					nB[j][bh[j]--] = blue[j][i];
    				}
    				blue[j][i]=  0;
    			}
    		}
    	}
    	blue = nB;
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
