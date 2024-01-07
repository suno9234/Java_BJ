import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        int [][] arr = new int[n][m];
        boolean [][] _visited = new boolean[n][m];
        for(boolean []v : _visited){
            Arrays.fill(v,false);
        }
        for(int i = 0 ; i < n; i++){
            String nl = bufferedReader.readLine();
            for(int j = 0 ; j < m; j++){
                arr[i][j] = Integer.parseInt(nl.substring(j,j+1));
            }
        }
        Queue<Coor> queue = new LinkedList<Coor>();
        queue.add(new Coor(0,0));
        int[] dx = {1,-1,0,0};
        int [] dy = {0,0,1,-1};
        _visited[0][0] = true;
        int answer = 1;
        while(!queue.isEmpty()){
            Coor now = queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(nx >=0 && ny >=0 && nx <n && ny <m && !_visited[nx][ny] && arr[nx][ny] != 0){
                    arr[nx][ny] = arr[now.x][now.y]+1;
                    if(!queue.contains(new Coor(nx,ny))) {
                        _visited[nx][ny] = true;
                        queue.add(new Coor(nx, ny));
                    }
                }
            }
        }
        System.out.println(arr[n-1][m-1]);
    }
    
}
class Coor{
    int x;
    int y;
    public Coor(int x, int y){
        this.x = x;
        this.y = y;
    }
}