import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main{
    static int [] dice  = {1,5,6,2,4,3};
    static int n,m,x,y,k;
    static int [][] _map;
    static int [][] _score ;
    static int [] dx  = {0,1,0,-1};
    static int [] dy  = {1,0,-1,0};
    //                  동 남 서 북
    static int answer = 0;
    static int _dir = 0;
    /*
     *   3
     * 4 0 5
     *   1
     *   2
     * */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        k = Integer.parseInt(tokens[2]);
        x = 0;
        y = 0;
        StringBuilder stringBuilder = new StringBuilder();
        _map = new int[n][m];
        _score = new int[n][m];
        for (int i = 0; i < n; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                _map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                int score = 0;
                int thisValue = _map[i][j];
                if(_score[i][j] == 0){
                    Queue<Integer []> queue = new ArrayDeque<>();
                    Queue<Integer []> queue2 = new ArrayDeque<>();
                    queue.add(new Integer[]{i,j});
                    queue2.add(new Integer[]{i,j});
                    _score[i][j] = -1;
                    while(!queue.isEmpty()){
                        Integer [] now = queue.poll();
                        score+=thisValue;
                        for(int l = 0 ; l < 4 ; l++){
                            int nx = now[0]+dx[l];
                            int ny = now[1]+dy[l];
                            if(nx >= 0 && ny >=0 && nx < n && ny < m && _map[nx][ny] == thisValue && _score[nx][ny]==0 && _score[nx][ny]!=-1){
                                _score[nx][ny] = -1;
                                queue.add(new Integer[]{nx,ny});
                                queue2.add(new Integer[]{nx,ny});
                            }
                        }
                    }
                    while(!queue2.isEmpty()){
                        Integer [] now = queue2.poll();
                        _score[now[0]][now[1]] =score;
                    }

                }
            }
        }
        while(k > 0) {
            int nx = x + dx[_dir];
            int ny = y + dy[_dir];
            if ( ! (nx >= 0 && ny >= 0 && nx < n && ny < m)) {
                // 밖으로 나갈경우 반대로 돌려서 한칸
                _dir = (_dir+2)%4;
                nx = x + dx[_dir];
                ny = y + dy[_dir];
            }
            switch (_dir) {
                case 0:
                    rotateR();
                    break;
                case 2:
                    rotateL();
                    break;
                case 3:
                    rotateU();
                    break;
                case 1:
                    rotateD();
                    break;
            }
            //점수 로직 : 수정
            answer +=  _score[nx][ny];
            if(_map[nx][ny] < dice[2]){
                // A > B 바닥이 더 큰경우
                _dir = (_dir+1)%4;
            }else if ( _map[nx][ny] > dice[2]){
                // A < B 바닥이 더 작은경우
                _dir = (_dir+3)%4;
            }
            x = nx;
            y = ny;
            k-=1;
        }

        System.out.println(answer);
    }
    static void rotateU() {
        turn(3,0,1,2);
    }
    static void rotateD() {
        turn(2,1,0,3);
    }
    static void rotateR() {
        turn(0,4,2,5);
    }
    static void rotateL() {
        turn(0,5,2,4);
    }
    static void turn(int a, int b, int c, int d) {
        int temp = dice[a];
        dice[a] = dice[b];
        dice[b] = dice[c];
        dice[c] = dice[d];
        dice[d] = temp;
    }
}
