import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int [][] _map ;
    static int [] dx = {1,-1,0,0};
    static int [] dy = {0,0,1,-1};
    static int r,c,t;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = bufferedReader.readLine().split(" ");
        r = Integer.parseInt(tokens[0]);
        c = Integer.parseInt(tokens[1]);
        t = Integer.parseInt(tokens[2]);
        _map = new int[r][c];
        int headX = 0;
        int tailX = 0;
        for(int i = 0 ; i < r ; i++){
            tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < c ; j++){
                _map[i][j] = Integer.parseInt(tokens[j]);
                if(_map[i][j] == -1){
                    if(headX == 0){
                        headX = i;
                    }else{
                        tailX = i;
                    }
                }
            }
        }
        for(int time = 0 ; time < t ; time++){
            // t초간 반복
            spread();
            cleanH(headX);
            cleanT(tailX);

        }
        int answer = 0;
        for(int i = 0 ; i < r; i++){
            for(int j = 0 ; j < c ; j++){
                if(_map[i][j] > 0){
                    answer+=_map[i][j];
                }
            }
        }
        System.out.println(answer);
    }
    public static void spread(){
        int [][] _result = new int[r][c];
        for(int i = 0 ; i < r;i++){
            for(int j = 0 ; j < c ; j++){
                int amount = _map[i][j] / 5;
                for(int k = 0 ; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx >= 0 && nx < r && ny>=0 && ny < c && _map[nx][ny]!= -1 ){
                        _result[nx][ny] += amount;
                        _map[i][j] -= amount;
                    }
                }
            }
        }
        for(int i = 0 ; i < r ; i++){
            for(int j = 0 ; j <c ; j++){
                _map[i][j] += _result[i][j];
            }
        }
    }
    public static void cleanH(int x){
        // 아래
        for(int i = x-1; i >0 ; i--){
            //System.out.println("미는중");
            _map[i][0] = _map[i-1][0];
        }
        // 왼쪽
        for(int i = 0 ; i < c-1; i++){
            _map[0][i] = _map[0][i+1];
        }
        // 위로
        for(int i = 0; i < x+1; i++){
            //System.out.println("미는중");
            _map[i][c-1] = _map[i+1][c-1];
        }
        //오른쪽
        for(int i = c-1; i>1; i--){
           //System.out.println("미는중");
            _map[x][i] = _map[x][i-1];
        }
        _map[x][1] = 0;
    }
    public static void cleanT(int x){
        // 위로
        for(int i = x+1; i < r-1; i++){
            _map[i][0] = _map[i+1][0];
        }
        // 왼쪽으로
        for(int i = 0 ; i < c-1; i++){
            _map[r-1][i] = _map[r-1][i+1];
        }
        // 아래로
        for(int i = r-1; i >x ; i--){
            _map[i][c-1] = _map[i-1][c-1];
        }
        //오른쪽으로
        for(int i = c-1; i>1; i--){
            _map[x][i] = _map[x][i-1];
        }
        _map[x][1] = 0;
    }
}
