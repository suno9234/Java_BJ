import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int n, m, posX, posY, dir, answer;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    //              0북 1동 2남 3서
    static int[][] _room;
    public static void main(String[] args) throws IOException {
        class Tup{
            int x ,y;
            public Tup(int x, int y){
                this.x = x;
                this.y = y;
            }
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        _room = new int[n][m];
        tokens = bufferedReader.readLine().split(" ");
        posX = Integer.parseInt(tokens[0]);
        posY = Integer.parseInt(tokens[1]);
        dir = Integer.parseInt(tokens[2]);
        for(int i =  0; i<n;i++){
            tokens = bufferedReader.readLine().split(" ");
            for(int j =0 ;j < m; j++){
                _room[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        Queue<Tup> queue = new ArrayDeque<>();
        queue.add(new Tup(posX,posY));
        while(!queue.isEmpty()){
            Tup now = queue.poll();
            // 1
            if(_room[now.x][now.y] == 0){
                answer+=1;
                _room[now.x][now.y] = -1;
            }
            int flag = 0;
            for(int i = 0 ; i < 4; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(nx >=0 && ny >= 0 && nx < n && ny < m){
                    if(_room[nx][ny] == 0){
                        flag = 1;
                        break;
                    }
                }
            }
            if(flag == 0){
                // 빈 칸이 없는 경우
                int nx = now.x-dx[dir];
                int ny = now.y-dy[dir];
                if(nx >= 0 && ny >= 0 && nx < n && ny < m && _room[nx][ny]!=1){
                    // 다음 칸으로 후진이 가능하면
                    queue.add(new Tup(nx,ny));
                }else{
                    // 안되면 멈추기
                    break;
                }
            }else{
                // 빈 칸이 있는 경우
                dir-=1;
                if(dir < 0)dir = 3;
                //반시계 회전
                int nx = now.x+dx[dir];
                int ny = now.y+dy[dir];
                if(nx >=0 && ny >= 0 && nx < n && ny < m){
                    if(_room[nx][ny] == 0){
                        queue.add(new Tup(nx,ny));
                    }else{
                        queue.add(now);
                    }
                }
            }
        }
        System.out.println(answer);
    }

}
