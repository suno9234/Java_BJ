import java.io.*;
import java.util.*;

public class Solution{

    static int n,m,c,answer, temp,maxScore;
    static int [][] _map;
    static int [] b;
    static boolean [] v;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("C:\\Users\\신순호\\Desktop\\싸피\\PrevStudy\\src\\org\\swea\\res\\2115_input.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        String [] tokens;
        int test_case = Integer.parseInt(bufferedReader.readLine())+1;
        for(int tc = 1; tc < test_case ; tc++){
            tokens = bufferedReader.readLine().split(" ");
            n = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[1]);
            c = Integer.parseInt(tokens[2]);
            answer = 0;
            _map = new int[n][n];
            b = new int[m];

            for(int i = 0 ; i < n ; i++){
                tokens = bufferedReader.readLine().split(" ");
                for(int j = 0 ; j < n ; j++){
                    _map[i][j] = Integer.parseInt(tokens[j]);
                }
            }
            for(int i = 0 ; i < n*n ; i++){
                int x = i/n;
                int y = i%n;
                if(y+m-1 < n){
                    for(int j = i+m ; j < n*n; j++){
                        int xj = j/n;
                        int yj = j%n;
                        if(yj+m-1 < n){
                            // 1번 일꾼 _map[x][y] ~ _map[x][y+m-1]
                            // 2번 일꾼 _map[xj][yj] ~ _map[xj][yj+m-1]
                            answer = Math.max(answer, getMaxScore(i)+getMaxScore(j));

                        }
                    }
                }
            }
            stringBuilder.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    static int getMaxScore(int idx){
        int x = idx/n;
        int y = idx%n;
        maxScore = 0;
        // _map[x][y] 에서 _map[x][y+m-1] 까지 검사
        v = new boolean[m];
        subs(x,y,0);
        return maxScore;
    }
    static void subs(int x,int y, int cnt){
        int _sum = 0;
        int score = 0;
        for(int i = 0 ; i < m ; i++){
            if(v[i]){
                _sum+=_map[x][y+i];
                score+=_map[x][y+i]*_map[x][y+i];
            }
        }
        if(_sum <= c){
            maxScore = Math.max(maxScore,score);
        }
        if(cnt == m){
            return;
        }
        v[cnt] = true;
        subs(x,y,cnt+1);
        v[cnt] = false;
        subs(x,y,cnt+1);
    }
}
