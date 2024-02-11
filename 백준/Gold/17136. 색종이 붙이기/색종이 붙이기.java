import java.io.*;
import java.util.*;

public class Main {
    static int [][] _board;
    static int answer = 30;
    static int [] dx = {1,-1,0,0};
    static int [] dy = {0,0,1,-1};
    static ArrayList<int[]> ones = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        _board = new int[10][10];
        int [] papers = {0,5,5,5,5,5};
        for(int i = 0 ; i < 10; i++){
            String [] tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j <10;j++){
                _board[i][j] = Integer.parseInt(tokens[j]);
                if(_board[i][j] == 1){
                    ones.add(new int[]{i,j});
                }
            }
        }
        perm(ones,0,papers);
        if(answer == 30){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }
    }
    static void perm(List<int[]> ones, int cnt,int [] papers){
        // i = start / 10 부터 j = start%10 부터
        if(cnt >= answer){
            return;
        }
        for(int [] now : ones){
            int x = now[0];
            int y = now[1];
            int flag = 0;
            if(_board[x][y] == 1) {
                for (int j = 5; j > 0; j--) {
                    if (papers[j] > 0 && isPossible(x, y, j)) {
                        papers[j]--;
                        if (papers[j] >= 0) {
                            for (int k = 0; k < j; k++) {
                                for (int l = 0; l < j; l++) {
                                    _board[x + k][y + l] = 0;
                                }
                            }
                            perm(ones, cnt + 1, papers);
                            for (int k = 0; k < j; k++) {
                                for (int l = 0; l < j; l++) {
                                    _board[x + k][y + l] = 1;
                                }
                            }
                        }
                        papers[j]++;
                        flag = 1;
                    }
                }
                return;
            }
        }
        answer = Math.min(answer,cnt);
    }
    static boolean isPossible(int x, int y , int n){
        if(x+n <= 10 && y+n<=10){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(_board[x+i][y+j] == 0){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
