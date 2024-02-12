import java.io.*;
import java.util.*;

public class Main{
    static int n,l,r; // map의 크기 n 인구 차이 l이상 r이하
    static int [][] arr ; // map의 정보를 담는 이차원 배열
    static int [][] country; // country의 정보를 담는 이차원 배열
    static int answer = 0;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    
    static int cnt , _sum;

    static int [] _avgs ; // country를 만들고 나서 인구를 분배할때 사용하는 도시번호-평균인구 배열
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
        n = Integer.parseInt(stringTokenizer.nextToken());
        l = Integer.parseInt(stringTokenizer.nextToken());
        r = Integer.parseInt(stringTokenizer.nextToken());

        arr = new int[n][n]; 

        for(int i = 0 ; i < n ; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
            for(int j = 0 ; j < n ; j++){
                arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
       
        // 입력
        
        while(true){
            int idx = 1; // country 번호
            country = new int[n][n];
            _avgs = new int[2501];
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(country[i][j] == 0){ // 아직 몇번 country인지 정해지지 않은 곳이면
                        //openBoundary(i,j,idx++); // 경계를 열고 country번호 기입
                      
                         _sum = 0;
                         cnt = 0;
                         openBoundary2(i,j,idx++);
                         
                    }
                }
            }
            int flag = 0;
            for(int i = 0 ; i < n ; i++){
                if(flag == 1) break;
                for(int j = 0 ; j < n ; j++){
                    if(arr[i][j] != _avgs[country[i][j]]){ // 지금 인구가 바꿔야 하는 인구와 한번이라도 다르면 인구 이동이 필요하므로 종료x
                        flag = 1;
                        break;
                    }
                }
            }
            if(flag == 0){
                break; // 바꿔야 하는 인구가 하나도 없으면 break로 탈출
            }
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    arr[i][j] = _avgs[country[i][j]]; // 인구 분배
                }
            }
            answer++;
        }
        System.out.println(answer);
    }
    static void openBoundary(int x, int y,int idx){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int _sum = 0;
        int cnt = 0;
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            x = now[0];
            y = now[1];
            country[x][y] = idx;
            _sum += arr[x][y];
            cnt++;
            for(int i = 0 ; i < 4 ; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx >=0 && ny >=0 && nx < n && ny < n && country[nx][ny]==0 && Math.abs(arr[nx][ny]-arr[x][y]) >= l && Math.abs(arr[nx][ny]-arr[x][y])<=r){
                    country[nx][ny] = idx;
                    queue.add(new int[]{nx,ny});
                }
            }
        }
        _avgs[idx] = _sum/cnt;
    }
    static void openBoundary2(int x, int y, int idx) {
    	cnt++;
    	_sum+=arr[x][y];
    	country[x][y] = idx;
    	for(int i = 0 ; i < 4 ; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx >=0 && ny >=0 && nx < n && ny < n && country[nx][ny]==0 && Math.abs(arr[nx][ny]-arr[x][y]) >= l && Math.abs(arr[nx][ny]-arr[x][y])<=r){
                country[nx][ny] = idx;
                openBoundary2(nx,ny,idx);
            }
        }
    	_avgs[idx] = _sum/cnt;
    }
    
}
