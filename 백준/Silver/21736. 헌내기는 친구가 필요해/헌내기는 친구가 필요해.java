import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int answer = 0;
    public static void main(String [] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n,m,x,y;
        x = 0;
        y = 0;
        String [] tokens = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        int [][] campus = new int[n][m];
        boolean [][] _visited = new boolean[n][m];
        for(boolean[] b : _visited ){
            Arrays.fill(b,false);
        }
        for(int i = 0 ; i < n ; i ++){
            String s = bufferedReader.readLine();
            for(int j = 0 ; j < m ; j++){
                campus[i][j] = s.charAt(j);
                if(s.charAt(j)=='I'){
                    x = i;
                    y = j;
                }
            }
        }
        find(campus,_visited,n,m,x,y);
        if(answer > 0){
            System.out.println(answer);
        }else{
            System.out.println("TT");
        }
    }
    public static void find(int [][] arr ,boolean[][] _visited, int n, int m, int x, int y){
        int [] dx = {0,0,1,-1};
        int [] dy = {1,-1,0,0};
        if(arr[x][y] == 'P'){
            answer+=1;
        }
        _visited[x][y] = true;
        for(int i = 0 ; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx >=0 && ny >=0 && nx <n && ny <m && arr[nx][ny]!= 'X' && !_visited[nx][ny]){
                _visited[nx][ny] = true;
                find(arr,_visited,n,m,nx,ny);
            }
        }
    }
}
