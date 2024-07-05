import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    static int n,m;
    static int [] dx = {0,0,1,-1};
    static int [] dy = {1,-1,0,0};
    static boolean[][] v;
    static int [][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String [] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        map = new int[n][m];
        for(int i=0;i<n;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        int day = 1;
        while(true){
            int [][] melt = new int[n][m];
            int cnt = 0;
            int x = -1, y = -1;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(map[i][j] == 0){
                        if(i-1 >= 0) melt[i-1][j] -=1;
                        if(i+1 < n) melt[i+1][j] -=1;
                        if(j-1 >= 0) melt[i][j-1] -=1;
                        if(j+1 < m) melt[i][j+1] -=1;
                    }
                }
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    map[i][j] = Math.max(0,map[i][j]+ melt[i][j]);
                    if(map[i][j] == 0){
                        cnt++;
                    }else{
                        x = i;
                        y = j;
                    }
                }
            }
            if(x < 0 ){
                System.out.println(0);
                return;
            }
            v = new boolean[n][m];
            if (cnt+dfs(x,y) != n*m) {
                System.out.println(day);
                return;
            }

            day++;
        }

    }
    static int dfs(int x , int y ){
        v[x][y] = true;
        int answer = 1;
        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && ny >= 0 && nx < n && ny < m && !v[nx][ny] && map[nx][ny] != 0){
                answer +=dfs(nx,ny);
            }
        }
        return answer;
    }

}
