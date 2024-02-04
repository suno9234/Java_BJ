import java.io.*;
import java.util.*;

public class Main {
    static int [][] board;
    static int g = 2;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,-1,0,1};
    static ArrayList<Integer> history ;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        board = new int[102][102];
        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0 ; i < n ; i++){
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
            history = new ArrayList<>();
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            g = Integer.parseInt(stringTokenizer.nextToken());
            dragon(x,y,d,0);
        }

        for(int i = 0 ; i < 101;i++){
            for(int j = 0 ; j < 101; j++){
                if(board[i][j] == 1 && board[i][j+1] == 1 && board[i+1][j] == 1&& board[i+1][j+1] == 1){
                    answer+=1;
                }
            }
        }
        System.out.println(answer);
    }
    public static void  dragon( int x,int y,int d ,int generation){
        board[y][x] = 1;
        int nx=x;
        int ny=y;
        if(generation == 0){
            nx += dx[d];
            ny += dy[d];
            board[ny][nx] = 1;
            history.add(d);
        }else{
            int _pow = (int)Math.pow(2,generation);
            for(int i = 0 ; i < _pow/2; i++) {
                int nowD = (history.get(_pow / 2 - i - 1) + 3) % 4;
                if((_pow/2 -i-1 )%2 == 1){
                    nx+= dx[nowD];
                    ny+= dy[nowD];
                }else {
                    nx -= dx[nowD];
                    ny -= dy[nowD];
                }
                board[ny][nx] = 1;
                history.add(nowD);
            }
        }
        if(generation == g){
            return;
        }
        dragon(nx,ny,(d+3)%4,generation+1);

    }
}
