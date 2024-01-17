import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int answer = 0;
    static boolean [][] _visited ;
    static int [] dx = {1,-1,0,0};
    static int [] dy = {0,0,1,-1};
    static int n, m;
    static int [][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        arr = new int[n][m];
        _visited = new boolean[n][m];
        for(int i = 0 ; i < n; i++){
            tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < m; j++){
                arr[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m; j++){
                int temp = arr[i][j];
                dfs(i,j,3,temp); // ㅗ 모양은 dfs 로 검사 불가능
                fuck(i,j,3,temp);
               // System.out.println(answer);
            }
        }
        System.out.println(answer);
    }
    public static void dfs(int x , int y,int cnt , int _sum ){
        _visited[x][y]  = true;
        if (cnt == 0 ){
            if(answer < _sum) {
                answer = _sum;
            }
            return;
        }
        for(int i = 0 ; i < 4 ; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx >=0 && ny >=0 && nx < n && ny < m && !_visited[nx][ny]){
                _visited[nx][ny] = true;
                dfs(nx,ny,cnt-1,_sum+arr[nx][ny]);
                _visited[nx][ny] = false;
            }
        }
        _visited[x][y] = false;
    }
    public static void fuck(int x, int y, int cnt , int _sum){
        _visited[x][y]  = true;
        if(cnt == 2){
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            int total = 0;
            for(int i  = 0 ; i < 4 ; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx >=0 && ny >=0 && nx < n && ny < m && !_visited[nx][ny]){
                    arrayList.add(arr[nx][ny]);
                    total+=arr[nx][ny];
                }
            }
            if(arrayList.size()== 2){
                answer = Math.max(answer,_sum+total);
            }
            else if(arrayList.size()==3){
                for(int i = 0 ; i < 3 ;i++){
                    answer = Math.max(answer,_sum+total-arrayList.get(i));
                }
            }
            return;
        }
        for(int i = 0 ; i < 4 ; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx >=0 && ny >=0 && nx < n && ny < m && !_visited[nx][ny]){
                _visited[nx][ny] = true;
                fuck(nx,ny,cnt-1,_sum+arr[nx][ny]);
                _visited[nx][ny] = false;
            }
        }
        _visited[x][y] = false;
    }
}
