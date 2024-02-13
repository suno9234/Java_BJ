import java.util.*;
import java.io.*;
 
public class Solution {
    static int answer , n, k;
    static int [][] _map ;
    static int [] dx = {1,-1,0,0};
    static int [] dy = {0,0,1,-1};
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("C:\\Users\\신순호\\Desktop\\싸피\\PrevStudy\\src\\org\\swea\\res\\1949_input.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        String [] tokens;
 
        int test_case = Integer.parseInt(bufferedReader.readLine())+1;
        for(int tc = 1 ; tc < test_case ; tc++){
            tokens = bufferedReader.readLine().split(" ");
            n = Integer.parseInt(tokens[0]);
            // 문제 조건 :  3 <= n <= 8
            //  1 <= k <= 5
            // 가장 높은 봉우리 최대개수 = 5
            // 높이 음수 가능
            // 최악의 경우 8x8개에서 가장 높은 봉우리 5개
            // 64번(깎는 경우) x 5(k) x 5(봉우리) x dfs 시간
            k = Integer.parseInt(tokens[1]);
            _map = new int[n][n];
            answer = 0;
            int maxHeight = 0;
            ArrayList <int[]> tops = new ArrayList<>();
 
            for(int i = 0 ; i < n ; i++){
                tokens = bufferedReader.readLine().split(" ");
                for(int j = 0 ; j < n ; j++){
                    _map[i][j] = Integer.parseInt(tokens[j]);
                    if(_map[i][j] > maxHeight){
                        maxHeight = _map[i][j];
                        tops.clear();
                        tops.add(new int[]{i,j});
                    }else if(_map[i][j] == maxHeight){
                        tops.add(new int[]{i,j});
                    }
                }
            }
 
            for(int i = 0 ; i < n; i++){
                for(int j = 0 ; j < n ; j++){
                    for(int m = 0 ; m < k+1 && _map[i][j]-m >= 0 ; m++){
                        _map[i][j]-= m;
                        for(int [] top : tops){
                            int x = top[0];
                            int y = top[1];
                            if(!(i == x && j == y)) dfs(x,y,1);
                        }
                        _map[i][j]+= m;
                    }
                }
            }
 
            stringBuilder.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
    static void dfs(int x , int y , int len){
        answer = Math.max(answer,len);
        for(int i = 0 ; i < 4 ; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx >=0 && ny >=0 && nx < n && ny < n && _map[nx][ny] < _map[x][y]){
                dfs(nx,ny,len+1);
            }
        }
    }
}