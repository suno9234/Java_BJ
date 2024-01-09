import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        int m = Integer.parseInt(tokens[0]);
        int n = Integer.parseInt(tokens[1]);
        int h = Integer.parseInt(tokens[2]);
        int [][][] tomatoes = new int[n][m][h];
        Queue <Coor> queue = new LinkedList<Coor>();
        for(int k = 0 ; k <h; k++) {
            for (int i = 0; i < n; i++) {
                tokens = bufferedReader.readLine().split(" ");
                for(int j = 0 ; j < m ;j++){
                    tomatoes[i][j][k] = Integer.parseInt(tokens[j]);
                    if(tokens[j].equals("1")){
                        queue.add(new Coor(i,j,k));
                    }
                }
            }
        }
        int [] dx = {1,-1,0, 0 ,0,0 };
        int [] dy = {0, 0,1,-1, 0, 0};
        int [] dz = {0, 0,0, 0, 1,-1};
        int answer = 0;
        while(!queue.isEmpty()){
            Queue<Coor> temp = new LinkedList<Coor>();
            while(!queue.isEmpty()){
                Coor now = queue.poll();
                //System.out.printf("%d %d %d || ",now.x,now.y,now.z);
                for(int i = 0 ; i < 6 ; i++){
                    int nx = now.x+dx[i];
                    int ny = now.y+dy[i];
                    int nz = now.z+dz[i];
                    if(nx >=0 && ny >=0 && nz >=0 && nx<n && ny < m && nz < h &&tomatoes[nx][ny][nz]== 0){
                        tomatoes[nx][ny][nz] = 1;
                        temp.add(new Coor(nx,ny,nz));
                    }
                }
            }
          
            answer+=1;
            queue = temp;
        }
        int _sum = n*m*h;
        for(int k = 0 ; k < h; k++){
            for(int i = 0; i< n; i++){
                for(int j = 0; j < m; j++){
                    if(tomatoes[i][j][k] == 0){
                        _sum--;
                    }
                }
            }
        }
        if(_sum == n*m*h) {
            System.out.println(answer-1);
        }else{
            System.out.println(-1);
        }
    }
    public static class Coor{
        int x,y,z;
        Coor(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
