import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,l,r;
    static int [][] arr ;
    static int [][] country;
    static int answer = 0;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static int [] _avgs ;
    public static void main(String[] args) throws IOException {
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
        int iter = 5;
        while(iter > 0){
            int idx = 1;
            country = new int[n][n];
            _avgs = new int[2501];
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    //System.out.println(i+" "+j);
                    if(country[i][j] == 0){
                        openBoundary(i,j,idx++);
                    }
                }
            }
            int flag = 0;
            for(int i = 0 ; i < n ; i++){
                if(flag == 1) break;
                for(int j = 0 ; j < n ; j++){
                    if(arr[i][j] != _avgs[country[i][j]]){
                        flag = 1;
                        break;
                    }
                }
            }
            if(flag == 0){
                break;
            }
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    arr[i][j] = _avgs[country[i][j]];
                }
            }
            answer+=1;
        }
        System.out.println(answer);
    }
    public static void openBoundary(int x, int y,int idx){
        Queue<Integer[]> queue = new LinkedList<>();
        int _sum = 0;
        int cnt = 0;
        queue.add(new Integer[]{x,y});
        while(!queue.isEmpty()){
            Integer [] now = queue.poll();
            //System.out.println(now[0]+" "+now[1]);
            country[now[0]][now[1]] = idx;
            _sum += arr[now[0]][now[1]];
            cnt+=1;
            for(int i = 0 ; i < 4 ; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];
                if(nx >=0 && ny >=0 && nx < n && ny < n && country[nx][ny]==0 && Math.abs(arr[nx][ny]-arr[now[0]][now[1]]) >= l && Math.abs(arr[nx][ny]-arr[now[0]][now[1]])<=r){
                    country[nx][ny] = idx;
                    queue.add(new Integer[]{nx,ny});
                }
            }
        }
        _avgs[idx] = _sum/cnt;
    }
}
