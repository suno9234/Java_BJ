import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        String [] tokens = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        int [][] arr=  new int[n][m];
        boolean [][] _visited = new boolean[n][m];
        int [][] distance = new int[n][m];
        for(int [] i : distance){
            Arrays.fill(i , -1);
        }
        for(boolean[] v : _visited){
            Arrays.fill(v,false);
        }
        int x = 0;
        int y = 0;
        for(int i = 0 ; i < n ; i++){
            tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < m;j++){
                arr[i][j] = Integer.parseInt(tokens[j]);
                if(Integer.parseInt(tokens[j]) == 2){
                    x = i;
                    y = j;
                }
            }
        }

        Queue<Coor> queue = new LinkedList<Coor>();
        distance[x][y] = 0;
        _visited[x][y] = true;
        queue.add(new Coor(x,y));

        int [] dx = {1,-1,0,0};
        int [] dy = {0,0,1,-1};

        while(!queue.isEmpty()){
            Coor now = queue.poll();
            for(int i = 0 ; i < 4; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(nx >=0 && ny >=0 && nx < n && ny < m && arr[nx][ny] > 0 && !_visited[nx][ny]){
                    _visited[nx][ny] = true;
                    distance[nx][ny] = distance[now.x][now.y] + 1 ;
                    queue.add(new Coor(nx,ny));
                }
            }
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m ; j++){
                if(arr[i][j] == 0){
                    stringBuilder.append("0");
                }else {
                    stringBuilder.append(distance[i][j]);
                }
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
    static class Coor{
        int x,y;
        public Coor(int x ,int y){
            this.x = x;
            this.y = y;
        }
    }

}
