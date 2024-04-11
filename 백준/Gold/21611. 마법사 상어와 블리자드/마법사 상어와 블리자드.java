import java.util.*;
import java.io.*;

public class Main{
    static int n,m;
    static boolean isExplode ;
    static int [] deleted ;
    static int[] dx = {0,1,0,-1}; // 우하좌상
    static int[] dy = {1,0,-1,0};
    static int [][] _map;
    static int [][] indexMap;
    static int [] d1Map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        deleted = new int[4];
        _map = new int[n][n];
        indexMap = new int[n][n];
        d1Map = new int[n*n];
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n ; j++){
                _map[i][j] = Integer.parseInt(st.nextToken());
                indexMap[i][j] = -1;
            }

        }
        dfs(0,0,0,n*n-1);
        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            //                                        우 하 좌 상
            int d = Integer.parseInt(st.nextToken())-1; // 상 하 좌 우
            int s = Integer.parseInt(st.nextToken());   // 0  1  2  3

            // 구슬 제거
            deleteMarble(d,s);
            move();
            while(true){
                isExplode = false;
                explode();
                if(!isExplode)break;
                move();
            }
            change();
        }
        int answer = 0;
        for(int i = 0 ; i < 4 ; i++){
            answer += deleted[i]*i;
        }
        System.out.println(answer);
    }
    static void change(){
        int [] newMap = new int[n*n];
        int idx = 1;
        int num = 0;
        int cnt = 0;
        for(int i = 1 ; i < n*n ; i++){
            if(d1Map[i]!= num){
                if(num!=0){
                    if(idx >= n*n) break;
                    newMap[idx] = cnt;
                    if(idx+1 >= n*n) break;
                    newMap[idx + 1] = num;
                    idx += 2;
                }
                num = d1Map[i];
                cnt = 1;
            }else{
                cnt++;
            }
        }
        d1Map = newMap;
    }
    static void explode(){
        int num = 0;
        int cnt = 0;
        int startIdx= 0;
        for(int i = 1 ; i < n*n ; i++){
            if(d1Map[i] == num){
                cnt++;
            }else {
                if(cnt >= 4){
                    for(int j = 0 ; j < cnt ; j++){
                        d1Map[startIdx+j] = 0;
                        deleted[num]++;
                    }
                    isExplode = true;
                }
                num = d1Map[i];
                startIdx = i;
                cnt = 1;
            }
        }
    }
    static void move(){
        int leftZero = 0;
        int leftNum = 0;
        for(int i = 1; i < n*n ; i++){
            if(d1Map[i] == 0){
                leftZero = i;
                break;
            }
        }
        for(int i = leftZero+1 ; i < n*n ; i++){
            if(d1Map[i] != 0){
                leftNum = i;
                break;
            }
        }
        while(leftZero < n*n && leftNum < n*n && leftZero < leftNum){

            d1Map[leftZero] = d1Map[leftNum];
            d1Map[leftNum++] = 0;
            leftZero++;
            int flag  = 0;
            for(; leftNum < n*n ; leftNum++){
                if(d1Map[leftNum] != 0){
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                break;
            }
        }

    }
    static void deleteMarble(int d , int s){
        if(d == 0){
            d = 3;
        }else if (d == 3){
            d = 0;
        }
        for(int j = 1 ; j <= s ; j++){
            d1Map[indexMap[n/2+dx[d]*j][n/2+dy[d]*j]] = 0;
        }
    }
    static void dfs(int x ,int y,int dir, int cnt){
        indexMap[x][y] = cnt;
        d1Map[cnt] = _map[x][y];
        if(cnt == 0){
            return;
        }
        int nx = x+dx[dir];
        int ny = y+dy[dir];
        if(nx >=0 && ny >=0 && nx < n && ny < n && indexMap[nx][ny] == -1){
            dfs(nx,ny,dir,cnt-1);
        }else{
            dfs(x,y,(dir+1)%4,cnt);
        }
    }
}
