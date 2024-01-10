
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static boolean [][] _visited;
    static char [][] arr;
    static char [][] arr1;
    static int [] dx = {0,0,1,-1};
    static int [] dy = {1,-1,0,0};
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        int answer0 = 0;
        int answer1 =0;
        arr = new char[n][n];
        arr1  = new char[n][n];
        _visited = new boolean[n][n];

        for(int i = 0; i<n; i++){
            String nl = bufferedReader.readLine();
            for(int j = 0 ; j<n; j++){
                arr[i][j] = nl.charAt(j);
                arr1[i][j] = nl.charAt(j);
                if(nl.charAt(j) == 'G'){
                    arr1[i][j] = 'R';
                }
            }
        }

        for(int i = 0 ; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if (!_visited[i][j]) {
                    //System.out.printf("%d %d\n",i,j);
                    bfs(i,j,arr[i][j]);
                    answer0+=1;
                }
            }
        }
        _visited = new boolean[n][n];
        for(int i = 0 ; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if (!_visited[i][j]) {
                    //System.out.printf("%d %d\n",i,j);
                    bfs2(i,j,arr1[i][j]);
                    answer1+=1;
                }
            }
        }
        System.out.printf("%d %d",answer0,answer1);

    }
    public static void bfs(int x, int y, char type){
        _visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];
                if(nx >=0 && ny >=0 && nx < n && ny < n && arr[nx][ny] == type && !_visited[nx][ny] ){
                    _visited[nx][ny] = true;
                    queue.add(new int[]{nx,ny});
                }
            }
        }
    }
    public static void bfs2(int x, int y, char type){
        _visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];
                if(type == 'B') {
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && arr1[nx][ny] == type && !_visited[nx][ny]) {
                        _visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }else{
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && arr1[nx][ny] != 'B' && !_visited[nx][ny]) {
                        _visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
