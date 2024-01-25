import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int [] dice ;
    static int n,m,x,y,k;
    static int [][] _map;
    static int [] dx  = {0,0,-1,1};
    static int [] dy  = {1,-1,0,0};
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
        x = Integer.parseInt(tokens[2]);
        y = Integer.parseInt(tokens[3]);
        k = Integer.parseInt(tokens[4]);
        StringBuilder stringBuilder = new StringBuilder();
        _map = new int[n][m];
        for (int i = 0; i < n; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                _map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        int[] oper = new int[k];
        dice = new int[6];
        tokens = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            oper[i] = Integer.parseInt(tokens[i]);
        }
        for (int op : oper) {
            int nx = x + dx[op - 1];
            int ny = y + dy[op - 1];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                switch (op) {
                    case 1:
                        rotateR();
                        break;
                    case 2:
                        rotateL();
                        break;
                    case 3:
                        rotateU();
                        break;
                    case 4:
                        rotateD();
                        break;
                }
                if(_map[nx][ny] ==0 ){
                    // 다음 칸의 값이 0이면
                    _map[nx][ny] = dice[2];
                }else{
                    dice[2] = _map[nx][ny];
                    _map[nx][ny] = 0;
                }
                stringBuilder.append(dice[0]).append("\n");
                x = nx;
                y = ny;
            }
        }
        System.out.println(stringBuilder.toString());
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
