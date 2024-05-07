import java.io.*;
import java.util.*;

class Main{
    static int h,w;
    static boolean [][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        map = new boolean[h][w];
        for(int i = 0 ; i < w ; i++){
            int nowH = Integer.parseInt(st.nextToken());
            for(int j = 0; j < nowH; j++){
                map[h-1-j][i] = true;
            }
        }
        int answer = 0;
        for(int i = 0 ; i < h ; i++){
            int flag = 0;
            int sum = 0;
            for(int j = 0 ; j < w ; j++){
                if(map[i][j]){
                   // 칸이 있는 경우
                    if(flag == 0){
                        flag = 1;
                    }else{
                        answer+= sum;
                        sum = 0;
                    }
                }else if(flag == 1){
                    sum++;
                }
            }
        }
        System.out.println(answer);
    }
}