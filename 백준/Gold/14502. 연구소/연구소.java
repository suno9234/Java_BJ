import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n, m ;
    static int [][] _map ;
    static int[] pos ;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static Tup [] viruses ;
    static int answer = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        _map = new int[n][m];
        pos = new int[3];
        viruses = new Tup[10];
        int idx = 0;
        for(int i = 0 ; i < n ; i++){
            tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < m ; j++){
                _map[i][j] = Integer.parseInt(tokens[j]);
                if(_map[i][j] == 2){
                    viruses[idx++] = new Tup(i,j);
                }
            }
        }
        // 입력
        // 최대 64C3  얼마안됨=> 백트래킹으로 전부 확인
        back(0,3);
        System.out.println(answer);
    }
    public static void back(int start ,int cnt){
        if(cnt == 0){
            int[][] _mapcopy = new int[n][m];
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < m ; j++){
                    _mapcopy[i][j] = _map[i][j];
                }
            }

            // 바이러스 확산
            if(_map[pos[0]/m][pos[0]%m] == 0 &&
                    _map[pos[1]/m][pos[1]%m] == 0 &&
                    _map[pos[2]/m][pos[2]%m] == 0 ){
                // 세 위치에 모두 기둥을 세울 수 있는
                //System.out.println(Arrays.toString(pos));
                _mapcopy[pos[0]/m][pos[0]%m] = 1;
                _mapcopy[pos[1]/m][pos[1]%m] = 1;
                _mapcopy[pos[2]/m][pos[2]%m] = 1;
                //기둥 세우기
                Queue<Tup> queue = new LinkedList<>();
                for(Tup tup : viruses){
                    if(tup != null){
                        queue.add(tup);
                    }
                }
                while(!queue.isEmpty()){
                    Tup now = queue.poll();
                    int x = now.x;
                    int y = now.y;
                    for(int i = 0 ; i < 4 ; i++){
                        int nx = x+dx[i];
                        int ny = y+dy[i];
                        if(nx >= 0 && ny>=0 && nx < n && ny < m && _mapcopy[nx][ny] == 0){
                            _mapcopy[nx][ny] = 2;
                            queue.add(new Tup(nx,ny));
                        }
                    }
                }
                int temp = 0;
                for(int i = 0 ; i < n ; i++){
                    for(int j = 0 ; j < m ; j++){
                        if(_mapcopy[i][j] == 0 ){
                            temp+=1;
                        }
                    }
                }
                answer = (int)Math.max(answer,temp);

            }
            return;
        }
        for(int i = start ; i < n*m; i++){
            pos[cnt-1] = i;
            back(i+1,cnt-1);
        }

    }
}
class Tup{
    int x,y;
    public Tup(int x, int y){
        this.x = x;
        this.y = y;
    }
}